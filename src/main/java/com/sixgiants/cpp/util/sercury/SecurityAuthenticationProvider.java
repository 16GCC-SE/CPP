package com.sixgiants.cpp.util.sercury;

import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SecurityService securityService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=(String)authentication.getCredentials();
        String MDPassword = MD5Util.md5(password);
        User user = (User)securityService.loadUserByUsername(username);
        if (user==null){
            throw new BadCredentialsException("用户名不存在");
        }else if (!user.getPassword().equals(MDPassword)){
            throw new BadCredentialsException("密码不正确");
        }
        Collection<?extends GrantedAuthority> grantedAuthorities=user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user,password,grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
