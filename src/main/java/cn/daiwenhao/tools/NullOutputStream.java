package cn.daiwenhao.tools;

import java.io.OutputStream;

/**
 * 一个特殊的输出流，向此输出流写入的数据都会被丢弃
 * @author whdai
 */
public class NullOutputStream extends OutputStream {

    @Override
    public void write(byte[] b) {
    }

    @Override
    public void write(byte[] b, int off, int len) {
    }

    @Override
    public void write(int b) {
    }
}
