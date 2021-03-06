package com.vinspier.rabbit.constant;

/**
 * 消息常量
 *
 */
public interface MsgConstant {

    /**
     * 交换机
     * */
    String VINSPIER_EXCHANGE = "vinspier_exchange";

    /**
     * 延时交换机
     * */
    String VINSPIER_DELAY_EXCHANGE = "vinspier_delay_exchange";

    /**
     * 私信交换机
     * */
    String VINSPIER_DEAD_EXCHANGE = "vinspier_dead_exchange";

    /**
     * 队列
     * */
    String DEV_QUEUE = "dev_queue";

    String TEST_QUEUE = "test_queue";

    String DELAY_QUEUE = "delay_queue";

    String DEAD_QUEUE = "dead_queue";

    String ANNOTATION_QUEUE = "annotation_queue";

    /**
     * 路由
     * */
    String NORMAL_EVENT = "normal_event";

    String DELAY_EVENT = "delay_event";

    String DEAD_EVENT = "dead_event";

    String ANNOTATION_EVENT = "annotation_event";

}
