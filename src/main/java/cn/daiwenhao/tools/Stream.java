package cn.daiwenhao.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 一个流操作类
 * @author whdai
 */
public class Stream {

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * 链接输入流与输出流
     * @param in 输入流
     * @param out 输出流
     * @param bufferSize 单次读取大小
     * @throws IOException IOException
     */
    public static void link(InputStream in, OutputStream out, final int bufferSize) throws IOException {
        int n;
        byte[] buffer = new byte[bufferSize];
        while ((n = in.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        out.flush();
    }

    /**
     * 链接输入流与输出流
     * @param in 输入流
     * @param out 输出流
     * @throws IOException IOException
     */
    public static void link(InputStream in, OutputStream out) throws IOException {
        link(in, out, DEFAULT_BUFFER_SIZE);
    }

}
