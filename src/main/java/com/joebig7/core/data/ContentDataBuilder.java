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
     * 实例化构建器  并且指定生成文件的header
     *
     * @param headerDataList
     * @return
     */
    public static ContentDataBuilder instance(List<HeaderData> headerDataList) {
        return new ContentDataBuilder(headerDataList);
    }


    /**
     * 填充内容,默认List看作多行，对象默认不转换
     *
     * @param content 内容集合
     * @return
     */
    public ContentDataBuilder fill(List<Object> content) {
        return fill(content, false, true);
    }

    /**
     * 填充内容,对象转换
     *
     * @param content 内容集合
     * @return
     */
    public ContentDataBuilder fillWithTransform(List<Object> content) {
        return fill(content, true);
    }

    /**
     * 填充内容,当做单行处理
     *
     * @param content 内容集合
     * @return
     */
    public ContentDataBuilder fillWithOneLine(List<Object> content) {
        return fill(content, false, false);
    }


    /**
     * 填充内容
     *
     * @param content     内容集合
     * @param isTransform 是否要将每一行的内容转化为具体对象
     * @return
     */
    private ContentDataBuilder fill(List<Object> content, boolean isTransform) {
        return fill(content, isTransform, true);
    }

    /**
     * 填充内容
     *
     * @param content     内容集合
     * @param isTransform 是否要将每一行的内容转化为具体对象
     * @param content     是否将List中每个对象作为一行
     * @return
     */
    private ContentDataBuilder fill(List<Object> content, boolean isTransform, boolean isMultiLine) {
        if (CollectionUtils.isEmpty(content)) {
            throw new IllegalArgumentException("content is null");
        }

        if (isTransform) {
            List<T> transformedContent = content.stream().map(c -> (T) c).collect(Collectors.toList());
            fillObjects(transformedContent);
        } else {
            if (CollectionUtils.isEmpty(contentDataList)) {
                contentDataList = new ArrayList<>();
            }

            //如果isMultiLine为true表示传入的内容每一个元素作为一行，否则整个List做为一行
            if (isMultiLine) {
                content.stream().forEach(c -> {
                    List<Object> row = new ArrayList<>();
                    row.add(c);
                    contentDataList.add(row);
                });
            } else {
                contentDataList.add(content);
            }
        }
        return this;
    }

    /**
     * 填充单个具体的对象
     *
     * @param t
     * @return
     */
    public ContentDataBuilder fillObject(T t) {

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
     * 填充具体对象的List集合
     *
     * @param list
     * @return
     */
    public ContentDataBuilder fillObjects(List<T> list) {
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
