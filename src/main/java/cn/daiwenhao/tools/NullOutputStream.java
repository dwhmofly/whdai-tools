package cn.daiwenhao.tools;

import java.io.OutputStream;

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
