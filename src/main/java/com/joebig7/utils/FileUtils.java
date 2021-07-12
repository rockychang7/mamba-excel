package com.joebig7.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
}
