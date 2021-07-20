package com.joebig7.core.component;

/**
 * common component interface
 */
public interface ExcelComponent<T, P> {
    T create(P p);
}
