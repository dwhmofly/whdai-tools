package cn.daiwenhao.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 命令执行类
 * @author whdai
 */
public class Commander {

    /**
     * 执行命令
     * @param cmd 需要执行的命令
     * @param in 命令需要的额外的输入
     * @param out 执行结果或错误信息
     * @return the exit value of the process represented by this
     *         {@code Process} object.  By convention, the value
     *         {@code 0} indicates normal termination.
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     */
    public static int exec(String cmd, InputStream in, OutputStream out) throws IOException, InterruptedException {
        return exec(cmd, in, out, null, null);
    }

    /**
     * 执行命令
     * @param cmd 需要执行的命令
     * @param in 命令需要的额外的输入
     * @param out 执行结果或错误信息
     * @param header header字节数组会先于执行结果写入out输出流
     * @param append append字节数组会晚于执行结果写入out输出流
     * @return the exit value of the process represented by this
     *         {@code Process} object.  By convention, the value
     *         {@code 0} indicates normal termination.
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     */
    public static int exec(String cmd, InputStream in, OutputStream out, byte[] header, byte[] append) throws IOException, InterruptedException {
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
        if (header != null) {
            out.write(header);
        }
        Stream.link(result, out);
        result.close();
        Stream.link(error, out);
        error.close();
        if (append != null) {
            out.write(append);
        }
        return process.waitFor();
    }

    /**
     * 执行命令忽略结果
     * @param cmd 需要执行的命令
     * @param in 命令需要的额外的输入
     * @return the exit value of the process represented by this
     *         {@code Process} object.  By convention, the value
     *         {@code 0} indicates normal termination.
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     */
    public static int exec(String cmd, InputStream in) throws IOException, InterruptedException {
        return exec(cmd, in, null);
    }

    /**
     * 执行命令忽略额外输入和结果
     * @param cmd 需要执行的命令
     * @return the exit value of the process represented by this
     *         {@code Process} object.  By convention, the value
     *         {@code 0} indicates normal termination.
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     */
    public static int exec(String cmd) throws IOException, InterruptedException {
        return exec(cmd, null, null);
    }
}
