package com.omni.aurora.core.populator;

public interface Populator <S, T>{
    public T populate(S source, T target);
}