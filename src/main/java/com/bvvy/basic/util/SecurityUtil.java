package com.bvvy.basic.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bvvy on 2017/5/18.
 */
public class SecurityUtil {
    public static String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        return new BigInteger(1,md.digest()).toString(16);
    }

    public static String md5(String phone,String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(phone.getBytes());
        md.update(password.getBytes());
        return new BigInteger(1,md.digest()).toString(16);
    }
}