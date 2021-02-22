package com.chenjj.base.string;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * https://www.sohu.com/a/286558310_115128
 * 
 * UTF-8 最大的一个特点，就是它是一种变长的编码方式。它可以使用1~4个字节表示一个符号，根据不同的符号而变化字节长度。
 * UTF-16（字符用两个字节或四个字节表示）和 UTF-32（字符用固定的四个字节表示）。
 * <p>
 * UTF-8 是基于 8 位的码元的，因此它并不需要关心字节顺序（因为字节就是 8 位的呀，
 * 其它 UTF-16 和 UTF-32 在不同的机器编译环境下需要考虑字节的顺序问题）。
 * <p>
 * java的字节码文件（.class）文件采用的是UTF-8编码，但是在java 运行时会使用UTF-16编码。
 * 在转码的时候会在前面加上表示字节顺序的字符，这个字符称为”零宽度非换行空格”（ZERO WIDTH NO-BREAK SPACE），
 * 用FEFF表示。FEFF占用两个字节。所以字母a用UTF-16编码表示的时候本来用两个字节，再加上FEFF
 * 就变成了四个字节。𡥠用UTF-16编码表示的时候要用四个字节，再加上FEFF就变成了六个字节。
 */
public class CharToByte {
    public static void main(String[] args) throws UnsupportedEncodingException {
        char c1 = 'a';
        char c2 = '陈';
        // char c3 = '\uD846\uDD60'; // char只能表示两个字节的字符，超过之后无法表示
        System.out.println(charToByte(c1).length); // 2
        System.out.println(charToByte(c2).length); // 2

        String s1 = String.valueOf(c1);
        String s2 = String.valueOf(c2);
        String s3 = "\uD846\uDD60"; // 𡥠是一个在UTF-8编码下占用4个字节的字
        System.out.println(s3); // 𡥠
        char[] c3 = s3.toCharArray();
        System.out.println(c3[0]); // ?
        System.out.println(c3[1]); // ?
        // Java用一对char来表示那些需要4字节的字符
        System.out.println(c3.length);// 2

        System.out.println(s1 + "在不同编码下的字节数:");
        System.out.println(s1.getBytes("UTF-8").length); // 1
        System.out.println(s1.getBytes("UTF-16").length); // 4
        System.out.println(s1.getBytes("GBK").length); // 1
        System.out.println(s2 + "在不同编码下的字节数:");
        System.out.println(s2.getBytes("UTF-8").length); // 3
        System.out.println(s2.getBytes("UTF-16").length); // 4
        System.out.println(s2.getBytes("GBK").length); // 2
        System.out.println(s3 + "在不同编码下的字节数:");
        System.out.println(s3.getBytes("UTF-8").length); // 4
        System.out.println(s3.getBytes("UTF-16").length); // 6
        System.out.println(s3.getBytes("GBK").length); // 1
    }

    /**
     * char[] 数组转为byte[] 数组
     *
     * @param chars
     * @return
     */
    public static byte[] getBytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

    /**
     * byte[] 数组转为数组 char[]
     *
     * @param bytes
     * @return
     */
    public static char[] getChars(byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    /**
     * char 转 byte[] 数组
     *
     * @param c
     * @return
     */
    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    /**
     * byte[] 数组转 char
     *
     * @param b
     * @return
     */
    public static char byteToChar(byte[] b) {
        char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
        return c;
    }
}
