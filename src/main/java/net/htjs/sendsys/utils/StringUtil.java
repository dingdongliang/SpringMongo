package net.htjs.sendsys.utils;

/**
 * Description:
 * author  dyenigma
 * date 2016/9/19 10:25
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * Description: 字节组转换成字符串，和下面的hexStr2ByteArr互为可逆
     * methodName:byteArr2HexStr
     * Time:2016/9/23 10:20
     * param:[arrB]
     * return:java.lang.String
     */
    public static String byteArr2HexStr(byte[] arrB) {

        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }


    /**
     * Description: 将表示16进制值的字符串转换为byte数组， 和byteArr2HexStr互为可逆的转换过程
     * methodName:hexStr2ByteArr
     * Time:2016/9/23 10:20
     * param:[strIn]
     * return:byte[]
     */
    public static byte[] hexStr2ByteArr(String strIn) {

        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }


}
