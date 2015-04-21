package com.razborka.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Admin on 09.04.2015.
 */
public class MD5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        String result = (String) charSequence;
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        result = md5PasswordEncoder.encodePassword(result, "");
        return result;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String hash = md5PasswordEncoder.encodePassword((String)charSequence, "");
        return hash.equals(s);
    }
}