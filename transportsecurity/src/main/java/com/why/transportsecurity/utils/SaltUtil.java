package com.why.transportsecurity.utils;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @Description TODO 加盐
 * @Author why
 * @Date 2021/6/15 13:56
 * Version 1.0
 **/
public class SaltUtil {

    /**
     * 加盐加密
     * @param psd
     * @return
     */
    public static String merge(String psd,String salt) {
        //加盐
        String str = com.why.transportsecurity_finally.utils.Sm3Util.addSalt(psd, salt);
        //Sm4加密
        byte[] key = com.why.transportsecurity_finally.utils.Sm4Util.getBytes("whylovewyy", 16);
        try {
            byte[] bytes = com.why.transportsecurity_finally.utils.Sm4Util.encryptEcbPkcs5Padding(str.getBytes(),key);
            //hash
            String pwdNew = com.why.transportsecurity_finally.utils.Sm3Util.hashStr(new String(bytes));
            return pwdNew;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
