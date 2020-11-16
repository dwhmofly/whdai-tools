package cn.daiwenhao.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Commander {

    public static int exec(String cmd, InputStream in, OutputStream out) throws IOException, InterruptedException {
        if (out == null){
            out = new NullOutputStream();
        }
        Process process = Runtime.getRuntime().exec(cmd);
        if (in != null){
            OutputStream param = process.getOutputStream();
            Stream.link(in, param);
        }
        InputStream result = process.getInputStream();
        InputStream error = process.getErrorStream();
        Stream.link(result, out);
        result.close();
        Stream.link(error, out);
        error.close();
        return process.waitFor();
    }

    public static int exec(String cmd, InputStream in) throws IOException, InterruptedException {
        return exec(cmd, in, null);
    }

    public static int exec(String cmd) throws IOException, InterruptedException {
        return exec(cmd, null, null);
    }
}
