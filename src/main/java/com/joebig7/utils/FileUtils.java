package com.joebig7.utils;

import java.io.*;
import java.util.Objects;

/**
 * @Author JoeBig7
 * @date 2021/7/12 17:42:45
 * @description 文件工具
 */
public class FileUtils {
    public static FileOutputStream getFileOutputStream(String path) {
        try {
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            return fileOutputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FileInputStream getFileInputStream(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            return fileInputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeInputStream(InputStream inputStream) throws IOException {
        if (Objects.isNull(inputStream)) {
            inputStream.close();
        }
    }

    public static void closeOutputStream(OutputStream outputStream) throws IOException {
        if (Objects.isNull(outputStream)) {
            outputStream.close();
        }
    }
}
