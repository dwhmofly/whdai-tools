package cn.daiwenhao.tools;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 一个特殊的输出流，向此输出流写入数据时，
 * 会自动检测出字节数组的编码并转换为需要的编码
 * @author whdai
 */
public class EncodedOutputStream extends OutputStream {

    private static final String DEFAULT_TARGET_CHARSET = "UTF-8";
    private final OutputStream out;
    private String targetCharset;

    public EncodedOutputStream(OutputStream out) {
        this.out = out;
        this.targetCharset = DEFAULT_TARGET_CHARSET;
    }
    public EncodedOutputStream(OutputStream out, String targetCharset) {
        this.out = out;
        this.targetCharset = targetCharset;
        if (targetCharset == null || targetCharset.trim().isEmpty()){
            this.targetCharset = DEFAULT_TARGET_CHARSET;
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        String charset = getEncoding(b);
        if (!charset.equals(targetCharset)){
            b = new String(b, charset).getBytes(targetCharset);
        }
        out.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if ((off < 0) || (off > b.length) || (len < 0) ||
                ((off + len) > b.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }
        byte[] bytes = new byte[len];
        System.arraycopy(b, off, bytes, 0, len);
        String charset = getEncoding(bytes);
        if (!charset.equals(targetCharset)){
            bytes = new String(bytes, charset).getBytes(targetCharset);
        }
        for (byte aByte : bytes) {
            out.write(aByte);
        }
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

    private String getEncoding(byte[] bytes) {
        UniversalDetector detector = new UniversalDetector(null);
        detector.handleData(bytes, 0, bytes.length);
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();
        if (encoding == null) {
            encoding = DEFAULT_TARGET_CHARSET;
        }
        return encoding;
    }
}
