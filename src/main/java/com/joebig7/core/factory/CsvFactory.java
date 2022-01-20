package com.joebig7.core.factory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

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
    public static CSVPrinter writeInstance(String path) {
        return writeInstance(path, CSVFormat.DEFAULT);
    }

    /**
     * 获取CSVPrinter实例  可以指定format
     *
     * @param path
     * @param csvFormat
     * @return
     */
    public static CSVPrinter writeInstance(String path, CSVFormat csvFormat) {
        CSVPrinter printer = null;
        try {
            printer = new CSVPrinter(new FileWriter(path), CSVFormat.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return printer;
    }


    /**
     * 获取csv读取实例
     *
     * @param reader
     * @return
     */
    public static CSVParser readInstance(Reader reader, String[] headers) {
        CSVParser parse = null;
        try {
            parse = CSVParser.parse(reader, CSVFormat.Builder.create().setHeader(headers).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parse;
    }

}
