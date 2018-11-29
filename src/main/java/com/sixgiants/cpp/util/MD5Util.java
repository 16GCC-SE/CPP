package com.sixgiants.cpp.util;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String md5(String s){
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte b[] = md.digest(s.getBytes());
            BASE64Encoder base64 = new BASE64Encoder();
            return base64.encode(b);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String base64decode(String s){
        BASE64Decoder base64 = new BASE64Decoder();
        try {
            return new String(base64.decodeBuffer(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
