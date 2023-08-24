package org.hafunstar.utils;

import sun.plugin2.message.Message;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:加密算法工具类
 */
public class DigestUtil {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final String SALT_2 = "(e#;lo";
    private static final String SALT_1 = "ds&hs3";
    private static final int ITERATIONS = 1086;

    private static final String accountSalt = "j03yyka";

    public static String getAdminString(String sha){
        return DigestSHAToHex(sha);
    }

    public static String getAcccountString(String sha){
        return DigestSHAToHex(sha + accountSalt);
    }

    private static String DigestSHAToHex(String sha){

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(sha.getBytes(StandardCharsets.UTF_8));
            byte[] result = md.digest();
            return new BigInteger(1,result).toString(16);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

    /**
     *先把密码用sha256加密 32位16进制字符
     * @param plain密码
     * @param salt
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String encodeHash(String plain,String salt) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // 反复调用update输入数据:
        md.update(plain.getBytes("UTF-8"));
        md.update(salt.getBytes("UTF-8"));
        byte[] result = md.digest();
        return new BigInteger(1, result).toString(16);
    }

    /**
     *用PBKDF2把sha256加密的密文再加密
     * @param plain用sha256加密后的密文
     * @param salt
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String encodePBE(String plain) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(encodeHash(plain,SALT_1).toCharArray(),SALT_2.getBytes("UTF-8"),ITERATIONS,256);
        SecretKeyFactory f = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return bytesToHexString(f.generateSecret(spec).getEncoded());

    }

    /**
     *
     * @param attemptedPassword待验证密码
     * @param encryptedPassword密文
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static boolean authenticate(String attemptedPassword, String encryptedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        String encryptedAttemptedPassword = encodePBE(attemptedPassword);
        return encryptedAttemptedPassword.equals(encryptedPassword);

    }
    public static byte[] Hex2Bytes(String hexString){
        byte[] arrB = hexString.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];
        String strTmp = null;
        for (int i = 0; i < iLen; i += 2)
        {
            strTmp = new String(arrB, i, 2);
            arrOut[(i / 2)] = ((byte)Integer.parseInt(strTmp, 16));
        }
        return arrOut;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 对id加密
     * @param id
     * @return
     */
    public static String idEncrypt(int id){

        StringBuilder newId = new StringBuilder();
        newId.append(rStr());
        newId.append(id);
        newId.append(rStr());
        return newId.toString();
    }

    /**
     * id解密
     * @param id
     * @return
     */
    public static int idDecode(String id){

        String s = id.substring(3);
        return Integer.parseInt(s.substring(0,s.length()-3));
    }

    /**
     * 产生一个3位数的随机数
     * @return
     */
    public static String rStr(){
        Random r = new Random();
        StringBuilder a = new StringBuilder();
        for(int i =0;i<3;i++){
            a.append(r.nextInt(9));
        }
        return a.toString();
    }

    /**
     * 对注册用户的用户名MD5加密，去其中1 3 5 7位的字符组成伪随机数
     * @return 用户激活的url中的r
     */
    public static String userNameRandom(String name) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest messageDigest=MessageDigest.getInstance("MD5");
        messageDigest.update(name.getBytes("UTF-8"));
        byte[] result = messageDigest.digest();
        String  res = new BigInteger(1, result).toString(16);
        StringBuilder ran = new StringBuilder();
        for(int i =0;i<res.length();i++){
            if(i%2 == 1){
                ran.append(res.substring(i,i+1));

            }
            if(ran.length() == 6){
                break;
            }
        }
        return ran.toString();
    }


}
