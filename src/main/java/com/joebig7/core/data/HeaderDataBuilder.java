package com.joebig7.core.data;

import com.joebig7.enums.FieldTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/11/25 14:39:13
 * @description excel/csv 标题字段构建器
 */
public class HeaderDataBuilder {

    private static List<HeaderData> headerContent;

    private HeaderDataBuilder() {
    }

    public static HeaderDataBuilder instance() {
        headerContent = new ArrayList<>();
        HeaderDataBuilder builder = new HeaderDataBuilder();
        return builder;
    }

    /**
     * 设置标题字段名称和字段类型
     *
     * @param name         字段名称
     * @param fileTypeEnum 字段类型
     * @return
     */
    public HeaderDataBuilder fill(String name, FieldTypeEnum fileTypeEnum) {
        HeaderData headerData = new HeaderData(name, fileTypeEnum);
        headerContent.add(headerData);
        return this;
    }


    /**
     * 设置标题字段名称,字段类型默认String
     *
     * @param name 字段名称
     * @return
     */
    public HeaderDataBuilder fill(String name) {
        HeaderData headerData = new HeaderData(name, FieldTypeEnum.STRING);
        headerContent.add(headerData);
        return this;
    }


    public List<HeaderData> build() {
        return headerContent;
    }
}
