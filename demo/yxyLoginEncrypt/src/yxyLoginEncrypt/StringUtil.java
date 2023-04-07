// jadx - package cn.ulearning.yxy.util;
package yxyLoginEncrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

/* loaded from: classes.dex */
public class StringUtil {
    private static final String CIPHER = "AES/ECB/PKCS5Padding";

    private static String a1() {
        return Constant.DEFAULT_PWD;
    }

    private static String a3() {
        return "331";
    }
    
    private static String a2() {
        return "2021" + a3();
    }
    
    public static String getLoginString(String str, String str2) {
        try {
            String valueOf = String.valueOf(System.currentTimeMillis());
            String md5Encrypt = EncryptUtils.md5Encrypt(str);
            String md5Encrypt2 = EncryptUtils.md5Encrypt(valueOf);
            String md5Encrypt3 = EncryptUtils.md5Encrypt("**Ulearning__Login##by$$project&&team@@");
            String lowerCase = str2.toLowerCase();
            String md5Encrypt4 = EncryptUtils.md5Encrypt(md5Encrypt + lowerCase + md5Encrypt2 + md5Encrypt3);
            String md5Encrypt5 = EncryptUtils.md5Encrypt(valueOf);
            String substring = md5Encrypt5.substring(0, 18);

            System.out.println("\n 登录页ut参数加密 ");
            System.out.println(" - 拼接时间戳:\t"+valueOf);
            System.out.println(" - 密码小写:\t"+lowerCase);
            System.out.println(" - 时间戳md5:\t"+md5Encrypt2);
            System.out.println(" - 手机号md5:\t"+md5Encrypt);
            System.out.println(" - 固定值md5:\t"+md5Encrypt3);
            System.out.println(" ? 拼接:\t把手机号的md5和密码小写和日期的md5和固定值的md5拼接在一起");
            System.out.println(" - 拼接后md5:\t"+md5Encrypt4);
            System.out.println(" - 时间戳md5截取:\t"+substring);
            System.out.println(" ? 最终md5:\t把 `时间戳md5截取` 和  `拼接后md5` 和 `日期md5`的前18位 拼在一起");
            return substring + md5Encrypt4 + md5Encrypt5.substring(18);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || str.equalsIgnoreCase("null");
    }
    
    public static String getCStr(String str) {
        try {
            return getCString(Base64.encodeToString(encrypt(str, a1() + a2()), 0));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static byte[] encrypt(String str, String str2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(1, secretKeySpec);
        return cipher.doFinal(str.getBytes("UTF-8"));
    }
    
    private static String getCString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (sb.length() < 10) {
                sb.append((char) (((int) (Math.random() * 26.0d)) + 97));
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
    
}