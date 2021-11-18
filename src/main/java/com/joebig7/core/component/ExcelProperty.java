package com.joebig7.core.component;

import com.joebig7.core.data.HeaderData;
import com.joebig7.enums.FileTypeEnum;

import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/11/18 19:07:57
 */
public class ExcelProperty {

    protected List<HeaderData> headerDataList;

    protected String path;

    protected FileTypeEnum fileTypeEnum;

    public List<HeaderData> getHeaderDataList() {
        return headerDataList;
    }

    public void setHeaderDataList(List<HeaderData> headerDataList) {
        this.headerDataList = headerDataList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileTypeEnum getFileTypeEnum() {
        return fileTypeEnum;
    }

    public void setFileTypeEnum(FileTypeEnum fileTypeEnum) {
        this.fileTypeEnum = fileTypeEnum;
    }
}
