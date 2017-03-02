package net.htjs.sendsys.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

/**
 * Description: AES对称加密算法
 * author  dyenigma
 * date 2016/9/14 11:09
 */
public class AESUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);

    private AESUtil() {
    }

    /**
     * 获取当天的密钥
     */
    public static String setKey() {
        //加密的口令，从配置文件中获取
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        return bundle.getString("aes.key." + DateUtil.getMonth());
    }


    /**
     * 加密
     */
    public static byte[] encrypt(String content) throws UnsupportedEncodingException,
            InvalidAlgorithmParameterException {
        try {
            //密钥长度不够用0补齐。
            SecretKeySpec key = new SecretKeySpec(ZeroPadding(setKey().getBytes(), 16), "AES");
            //定义加密算法AES 算法模式CBC(还有个模式是ECB)、补码方式PKCS5Padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //CBC模式模式下初始向量 不足16位用0补齐
            IvParameterSpec iv = new IvParameterSpec(ZeroPadding("1234567890123456".getBytes(), 16));
            byte[] byteContent = content.getBytes("UTF-8");
            //初始化加密
            //ECB模式下是cipher.init(Cipher.ENCRYPT_MODE, key);
            //CBC模式：
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return cipher.doFinal(byteContent);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.info("NoSuchAlgorithmException:", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.info("NoSuchPaddingException:", e);
        } catch (InvalidKeyException e) {
            LOGGER.info("InvalidKeyException:", e);
        } catch (IllegalBlockSizeException e) {
            LOGGER.info("IllegalBlockSizeException:", e);
        } catch (BadPaddingException e) {
            LOGGER.info("BadPaddingException:", e);
        }
        return null;
    }

    /**
     * 解密
     */
    public static byte[] decrypt(byte[] content) throws InvalidAlgorithmParameterException {
        try {
            //密钥长度不够用0补齐。
            SecretKeySpec key = new SecretKeySpec(ZeroPadding(setKey().getBytes(), 16), "AES");

            //定义加密算法AES 算法模式CBC(还有个模式是ECB)、补码方式PKCS5Padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //CBC模式模式下初始向量 不足16位用0补齐
            IvParameterSpec iv = new IvParameterSpec(ZeroPadding("1234567890123456".getBytes(), 16));
            // 初始化解密
            //ECB
            //cipher.init(Cipher.DECRYPT_MODE, key);
            //CBC
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            return cipher.doFinal(content);

        } catch (NoSuchAlgorithmException e) {
            LOGGER.info("NoSuchAlgorithmException:", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.info("NoSuchPaddingException:", e);
        } catch (InvalidKeyException e) {
            LOGGER.info("InvalidKeyException:", e);
        } catch (IllegalBlockSizeException e) {
            LOGGER.info("IllegalBlockSizeException:", e);
        } catch (BadPaddingException e) {
            LOGGER.info("BadPaddingException:", e);
        }
        return null;
    }


    /**
     * 将二进制转换成16进制
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 补齐位数
     */
    public static byte[] ZeroPadding(byte[] in, Integer blockSize) {
        Integer copyLen = in.length;
        if (copyLen > blockSize) {
            copyLen = blockSize;
        }
        byte[] out = new byte[blockSize];
        System.arraycopy(in, 0, out, 0, copyLen);
        return out;
    }
}
