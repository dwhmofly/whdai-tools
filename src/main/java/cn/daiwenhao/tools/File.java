package cn.daiwenhao.tools;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 一个文件操作类
 * @author whdai
 */
public class File {

    /**
     * 将文件转换为字节数组
     * @param fileName fileName
     * @return 字节数组
     * @throws IOException IOException
     */
    public static byte[] toByteArray(String fileName) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        toOutputStream(fileName, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 复制文件
     * @param sourceFileName sourceFileName
     * @param targetFileName targetFileName
     * @param append 是否追加
     * @throws IOException IOException
     */
    public static void copy(String sourceFileName, String targetFileName, boolean append) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(targetFileName, append);
        toOutputStream(sourceFileName, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /**
     * 复制文件 覆盖已有文件
     * @param sourceFileName sourceFileName
     * @param targetFileName targetFileName
     * @throws IOException IOException
     */
    public static void copy(String sourceFileName, String targetFileName) throws IOException {
        copy(sourceFileName, targetFileName, false);
    }

    /**
     * 将文件转换为输出流
     * @param fileName fileName
     * @param outputStream outputStream
     * @throws IOException IOException
     */
    public static void toOutputStream(String fileName, OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Stream.link(fileInputStream, outputStream);
        fileInputStream.close();
    }

    /**
     * 将文件批量压缩zip
     * @param sourceFileNames 需要压缩的文件路径
     * @param targetFileName 压缩后文件路径
     * @throws IOException IOException
     */
    public static void toZip(List<String> sourceFileNames, String targetFileName) throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetFileName));
        for (String sourceFileName : sourceFileNames){
            FileInputStream in = new FileInputStream(sourceFileName);
            out.putNextEntry(new ZipEntry(sourceFileName.substring(sourceFileName.lastIndexOf(System.getProperty("file.separator"))+1)));
            Stream.link(in, out);
            in.close();
        }
        out.flush();
        out.closeEntry();
        out.close();
    }


}
