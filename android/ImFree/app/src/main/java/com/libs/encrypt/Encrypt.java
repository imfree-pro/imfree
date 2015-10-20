package com.libs.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 종열 on 2015-06-14.
 */
public class Encrypt {
    /*
    public String SHA512(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        StringBuffer sb = new StringBuffer();
        md.update(input.getBytes());
        byte[] msgb = md.digest();

        for(int i = 0; i < msgb.length; i ++)
        {
            byte temp = msgb[i];
            String str = Integer.toHexString(temp & 0xFF);
            while (str.length() < 2)
            {
                str = "0" + str;
            }
            str = str.substring(str.length()-2);
            sb.append(str);
        }
        return sb.toString();
    }
    */
    public static String SHA256(String input) {
        String retValue = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes("UTF-8"));
            byte byteData[] = md.digest();
            retValue = String.format("%064x", new java.math.BigInteger(1, byteData));
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return retValue;
    }

}
