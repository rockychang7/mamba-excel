package com.joebig7.utils;

import com.mamba.core.string.StringUtils;

import java.io.*;
import java.util.Objects;

/**
 * @Author JoeBig7
 * @date 2021/7/12 17:42:45
 * @description 文件工具
 */
public class CommonFileUtils {

    /**
     * 获取文件输出流 FileOutputStream
     *
     * @param path
     * @return
     */
    public static FileOutputStream getFileOutputStream(String path) {
        if (checkPath(path)) {
            throw new IllegalArgumentException(String.format("file path %s is not legal", path));
        }

        try {
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            return fileOutputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件输入流 FileInputStream
     *
     * @param path
     * @return
     */
    public static InputStream getFileInputStream(String path) {
        if (checkPath(path)) {
            throw new IllegalArgumentException(String.format("file path %s is not legal", path));
        }
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(path);
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }


    /**
     * 获取Reader对象
     *
     * @param path
     * @return
     */
    public static Reader getFileReader(String path) {
        if (checkPath(path)) {
            throw new IllegalArgumentException(String.format("file path %s is not legal", path));
        }
        Reader reader = null;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return reader;
    }

    /**
     * 关闭 InputStream
     *
     * @param inputStream
     */
    public static void closeInputStream(InputStream inputStream) {
        if (!Objects.isNull(inputStream)) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 关闭 OutputStream
     *
     * @param outputStream
     */
    public static void closeOutputStream(OutputStream outputStream) {
        if (!Objects.isNull(outputStream)) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭 Reader
     *
     * @param Reader
     */
    public static void closeReader(Reader reader) {
        if (!Objects.isNull(reader)) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static boolean checkPath(String path) {
        return StringUtils.isEmpty(path);
    }
}
