package com.joebig7.core;

import com.joebig7.core.component.ExcelProperty;
import com.joebig7.core.listener.ReadListener;
import com.joebig7.enums.FileTypeEnum;
import com.mamba.core.file.FileUtils;

/**
 * @Author JoeBig7
 * @date 2021/11/18 19:06:53
 */
public abstract class AbstractReader<T> extends ExcelProperty {
    ReadListener<T> readListener;
    Class type;

    public AbstractReader(String path, Class type, String sheetName) {
        this.path = path;
        this.sheetName = sheetName;
        this.type = type;
    }

    public void read(ReadListener<T> readListener) {
        initListener(readListener);
        String suffix = FileUtils.suffix(path);
        if (FileTypeEnum.CSV.getFileType().equalsIgnoreCase(suffix)) {
            doCsvRead();
        } else {
            doExcelRead();
        }
    }

    /**
     * 读取excel方法，可以覆盖进行定制化操作
     */
    abstract protected void doExcelRead();

    /**
     * 读取csv方法，可以覆盖进行定制化操作
     */
    abstract protected void doCsvRead();


    /**
     * 初始化读取excels上下文
     *
     * @param readListener
     */
    private void initListener(ReadListener<T> readListener) {
        this.readListener = readListener;
    }

}
