package com.joebig7;

import com.joebig7.core.GenericReader;
import com.joebig7.core.GenericWriter;
import com.joebig7.core.component.ExcelProperty;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.listener.DataReadListener;

import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/12/16 16:09:49
 * @description 读取和写出的工厂类
 */
public class MambaExcelFactory extends ExcelProperty {

    private GenericReader reader;
    private GenericWriter writer;

    private MambaExcelFactory() {
    }

    private static class MambaExcelInstance {
        private static final MambaExcelFactory instance = new MambaExcelFactory();
    }

    public static MambaExcelFactory build() {
        return MambaExcelInstance.instance;
    }

    /**
     * 获取读取对象
     *
     * @param path
     * @param type
     * @param headerDataList
     * @return
     */
    public MambaExcelFactory readInstance(String path, Class type, List<HeaderData> headerDataList) {
        GenericReader genericReader = new GenericReader(path, type);
        genericReader.setHeaderDataList(headerDataList);
        this.reader = genericReader;
        return this;
    }

    /**
     * 获取读取对象 用户excel指定sheetName
     *
     * @param path
     * @param type
     * @param headerDataList
     * @param sheetName
     * @return
     */
    public MambaExcelFactory readInstance(String path, Class type, List<HeaderData> headerDataList, String sheetName) {
        GenericReader genericReader = new GenericReader(path, type, sheetName);
        genericReader.setHeaderDataList(headerDataList);
        this.reader = genericReader;
        return this;
    }

    /**
     * 触发读取动作
     *
     * @param listener
     */
    public void read(DataReadListener listener) {
        reader.read(listener);
    }


    /**
     * 获取写对象
     *
     * @param path
     * @param headerDataList
     * @param contentDataList
     * @return
     */
    public MambaExcelFactory writeInstance(String path, List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        GenericWriter genericWriter = new GenericWriter(path);
        genericWriter.setHeaderDataList(headerDataList);
        genericWriter.setContentDataList(contentDataList);
        this.writer = genericWriter;
        return this;
    }


    /**
     * 触发写动作
     */
    public void write() {
        writer.write();
    }
}
