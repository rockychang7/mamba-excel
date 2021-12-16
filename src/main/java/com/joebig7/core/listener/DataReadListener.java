package com.joebig7.core.listener;

import com.mamba.core.collection.CollectionsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @Author JoeBig7
 * @date 2021/11/23 10:58:54
 * @description 处理excel读取出来的数据
 */
public class DataReadListener<T> implements ReadListener<T> {

    private List<T> contentDataList;
    private final Consumer<List<T>> consumer;

    public DataReadListener(Consumer<List<T>> consumer) {
        this.contentDataList = new ArrayList<>();
        this.consumer = consumer;
    }

    /**
     * invoke 监听全局内容
     *
     * @param data
     */
    @Override
    public void invoke(T data) {
        if (!Objects.isNull(data)) {
            contentDataList.add(data);
        }
    }

    /**
     * 监听后置操作，进行定制化操作
     */
    @Override
    public void doAfterRead() {
        if (!CollectionsUtils.isEmpty(contentDataList)) {
            consumer.accept(contentDataList);
        }
    }
}
