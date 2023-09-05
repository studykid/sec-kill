package com.mall.util;

import com.mall.common.MallString;
import org.apache.commons.codec.digest.DigestUtils;


/**
 * @author wy
 */
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToFormPass(String inputPass) {
        String str = ""+ MallString.md5_salt.charAt(0)+MallString.md5_salt.charAt(2) + inputPass +MallString.md5_salt.charAt(5) + MallString.md5_salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }


}
