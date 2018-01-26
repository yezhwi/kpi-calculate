package com.grg.kpi.calculate.function;

/**
 * @Author:czq
 * @Description:
 * @Date: 17:28 2018/1/19
 * @Modified By:
 */
@FunctionalInterface
public interface TantileFunction<A, T, TV, R> {
    //A 数组
    //T 总分为
    //TV 分位值
    R execute(A a, T t, TV tv);
}
