package cn.daiwenhao.tools;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class File {

    public static byte[] toByteArray(String fileName) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        toOutputStream(fileName, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void copy(String sourceFileName, String targetFileName, boolean append) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(targetFileName, append);
        toOutputStream(sourceFileName, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static void copy(String sourceFileName, String targetFileName) throws IOException {
        copy(sourceFileName, targetFileName, false);
    }

    public static void toOutputStream(String fileName, OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Stream.link(fileInputStream, outputStream);
        fileInputStream.close();
    }

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
