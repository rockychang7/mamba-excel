package com.joebig7.core.component;

public interface ExcelComponent<T,P> {
    T create(P p);
}
