package ut;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;

/**
 * Created by AUser on 2015-12-18 018.
 */
public class EncrayptLincens {
    public static void main(String[] args) throws UnsupportedEncodingException, DecoderException {
        byte[] k = "钥匙".getBytes("utf-8");
        byte[] s = ("用户单位全名\n" +
                "组织机构代码证号码\n" +
                "单位类型\n" +
                "联系方式\n" +
                "购买类别\n" +
                "购买日期\n" +
                "启用日期\n" +
                "终止日期\n" +
                "缴费者").getBytes("utf-8");

        int index = 0;
        for (int i = 0; i < s.length; i++, index++) {
            s[i] ^= k[index % k.length];
        }
        String chars = Hex.encodeHexString(s);
        System.out.println(Hex.encodeHexString(k)+ chars);
        byte[] bytes = Hex.decodeHex(chars.toCharArray());

        index = 0;
        for (int i = 0; i < s.length; i++, index++) {
            s[i] ^= k[index % k.length];
        }

        System.out.println(new String(s));

    }
}
