package com.joebig7.core;

import com.joebig7.enums.FileTypeEnum;

import static com.joebig7.enums.FileTypeEnum.XLSX;

/**
 * @Author JoeBig7
 * @date 2021/7/12 17:37:40
 * @description 定义操作execel的模板
 */
public abstract class AbstractExcelWriter {

    public void write(String path) {
        write(path, XLSX);
    }

    public void write(String path, FileTypeEnum fileTypeEnum) {

    }


}
