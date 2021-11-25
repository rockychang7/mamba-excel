package com.joebig7.utils;

import com.mamba.core.string.StringUtils;

import java.io.*;
import java.util.Objects;

/**
 * @Author JoeBig7
 * @date 2021/7/12 17:42:45
 * @description 文件工具
 */
public class FileUtils {
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

    public static FileInputStream getFileInputStream(String path) {
        if (checkPath(path)) {
            throw new IllegalArgumentException(String.format("file path %s is not legal", path));
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            return fileInputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeInputStream(InputStream inputStream) {
        if (!Objects.isNull(inputStream)) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeOutputStream(OutputStream outputStream){
        if (!Objects.isNull(outputStream)) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static boolean checkPath(String path) {
        return StringUtils.isEmpty(path);
    }
}
