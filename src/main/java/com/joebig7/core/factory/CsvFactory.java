package com.joebig7.core.factory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author JoeBig7
 * @date 2021/12/14 17:52:52
 * @descriptio csv对象工厂
 */
public class CsvFactory {
    /**
     * 获取CSVPrinter实例  默认format
     *
     * @param path
     * @return
     */
    public static CSVPrinter instance(String path) {
        return instance(path, CSVFormat.DEFAULT);
    }

    /**
     * 获取CSVPrinter实例  可以指定format
     *
     * @param path
     * @param csvFormat
     * @return
     */
    public static CSVPrinter instance(String path, CSVFormat csvFormat) {
        CSVPrinter printer = null;
        try {
            printer = new CSVPrinter(new FileWriter(path), CSVFormat.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return printer;
    }
}
