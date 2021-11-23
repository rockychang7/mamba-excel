package com.joebig7.core.listener;

import java.util.EventListener;

public interface ReadListener<T> extends EventListener {
    void invoke(T data);

    void doAfterRead();
}
