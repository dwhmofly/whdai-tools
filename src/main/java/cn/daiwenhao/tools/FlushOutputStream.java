package cn.daiwenhao.tools;

import java.io.IOException;
import java.io.OutputStream;

public class FlushOutputStream extends OutputStream {

    protected OutputStream out;

    public FlushOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(byte[] b) throws IOException {
        out.write(b);
        flush();
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
        flush();
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }
}
