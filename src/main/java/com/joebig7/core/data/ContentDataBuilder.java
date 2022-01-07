package com.joebig7.core.data;

import com.joebig7.core.component.ExcelProperty;
import com.mamba.core.clazz.ClassUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author JoeBig7
 * @date 2021/12/16 17:23:05
 * @description 内容构建器
 */
public class ContentDataBuilder<T> extends ExcelProperty {
    private ContentDataBuilder() {
        if (CollectionUtils.isEmpty(this.contentDataList)) {
            this.contentDataList = new ArrayList<>();
        }
    }

    private ContentDataBuilder(List<HeaderData> headerDataList) {
        if (CollectionUtils.isEmpty(this.contentDataList)) {
            this.contentDataList = new ArrayList<>();
        }

        this.headerDataList = headerDataList;
    }

    /**
     * 实例化构建器
     *
     * @return
     */
    public static ContentDataBuilder instance() {
        return new ContentDataBuilder();
    }

    /**
     * 实例化构建起  指定headerDataList
     *
     * @param headerDataList
     * @return
     */
    public static ContentDataBuilder instance(List<HeaderData> headerDataList) {
        return new ContentDataBuilder(headerDataList);
    }

    /**
     * 填充内容 content默认作为多行
     * @param content
     * @param isTransform
     * @return
     */
    public ContentDataBuilder fill(List<Object> content, boolean isTransform) {
        return fill(content,isTransform,true);
    }

    /**
     * 填充内容
     *
     * @param content
     * @param isTransform 是否解析参数
     * @param content 是否作为多行解析
     * @return
     */
    public ContentDataBuilder fill(List<Object> content, boolean isTransform,boolean isMultiLine) {
        if (CollectionUtils.isEmpty(content)) {
            throw new IllegalArgumentException("content is null");
        }

        if (isTransform) {
            List<T> transformedContent = content.stream().map(c -> (T) c).collect(Collectors.toList());
            fill(transformedContent);
        } else {
            if (CollectionUtils.isEmpty(contentDataList)) {
                contentDataList = new ArrayList<>();
            }

            if(isMultiLine){
                content.parallelStream().forEach(c->{
                    List<Object> row = new ArrayList<>();
                    row.add(c);
                    contentDataList.add(row);
                });
            }else {
                contentDataList.add(content);
            }
        }
        return this;
    }

    /**
     * 填充内容 指定对象
     *
     * @param t
     * @return
     */
    public ContentDataBuilder fill(T t) {

        if (Objects.isNull(t)) {
            throw new IllegalArgumentException("content is null");
        }

        List<Object> content = new ArrayList<>();
        if (CollectionUtils.isEmpty(headerDataList)) {
            throw new IllegalArgumentException("headerDataList is null");
        }

        for (HeaderData headerData : headerDataList) {
            String fieldName = headerData.getFieldName();
            Object val = ClassUtils.getField(t, fieldName);
            content.add(val);
        }
        contentDataList.add(content);
        return this;
    }


    /**
     * 填充内容 指定对象集合
     *
     * @param list
     * @return
     */
    public ContentDataBuilder fill(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("content is null");
        }

        if (CollectionUtils.isEmpty(headerDataList)) {
            throw new IllegalArgumentException("headerDataList is null");
        }

        for (T t : list) {
            List<Object> content = new ArrayList<>();
            for (HeaderData headerData : headerDataList) {
                String fieldName = headerData.getFieldName();
                Object val = ClassUtils.getField(t, fieldName);
                content.add(val);
            }
            contentDataList.add(content);
        }
        return this;
    }


    public List<List<Object>> build() {
        return contentDataList;
    }
}
