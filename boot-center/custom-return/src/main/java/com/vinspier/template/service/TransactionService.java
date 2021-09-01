package com.vinspier.template.service;

/**
 * 
 * @author  vinspier
 * @date    2021/9/1 4:46 下午
 * @version 1.0
 * @menu    
*/
public interface TransactionService {

    /**
     * 事物内部抛出 非运行异常(检查异常)
     */
    void doWithinCheckedEpThrow() throws Exception;

    /**
     * 事物内部抛出 运行异常(免检异常)
     */
    void doWithinUnCheckedEpThrow();

}
