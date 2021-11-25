package com.joebig7.core.data;

import com.joebig7.enums.FieldTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/11/25 14:39:13
 * @description excel标题字段构建器
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

    public HeaderDataBuilder setHead(String name, FieldTypeEnum fileTypeEnum) {
        HeaderData headerData = new HeaderData(name, fileTypeEnum);
        headerContent.add(headerData);
        return this;
    }

    public List<HeaderData> build() {
        return headerContent;
    }
}
