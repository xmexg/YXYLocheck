// jadx - package cn.ulearning.yxy.util;
package yxyLoginEncrypt;

import java.security.MessageDigest;

/* loaded from: classes.dex */
public class EncryptUtils {

    public static String md5Encrypt(String str) throws Exception {
        if (StringUtil.isEmpty(str)) {
            throw new Exception("null value");
        }
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(str.getBytes());
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}