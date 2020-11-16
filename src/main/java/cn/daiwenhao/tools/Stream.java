package cn.daiwenhao.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Stream {

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    public static void link(InputStream in, OutputStream out, final int bufferSize) throws IOException {
        int n;
        byte[] buffer = new byte[bufferSize];
        while ((n = in.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        out.flush();
    }

    public static void link(InputStream inputStream, OutputStream outputStream) throws IOException {
        link(inputStream, outputStream, DEFAULT_BUFFER_SIZE);
    }

}
