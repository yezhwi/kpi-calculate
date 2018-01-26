package com.grg.kpi.calculate.function;

/**
 * @Author:czq
 * @Description:
 * @Date: 18:22 2018/1/9
 * @Modified By:
 */
@FunctionalInterface
public interface KpiFunction<T, R> {
    R execute(T t);
}
