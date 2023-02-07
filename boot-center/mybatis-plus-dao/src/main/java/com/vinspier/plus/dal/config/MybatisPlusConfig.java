package com.vinspier.plus.dal.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * mybatis-plus plugins config
 *
 * @author  xiaobiao.fan
 * @date    2023/2/7 1:47 下午
*/
@Configuration
public class MybatisPlusConfig {

    private static final String CREATED_TIME_FIELD = "createdAt";

    private static final String UPDATE_TIME_FIELD = "updatedAt";


    /**
     * interceptors
     * - pagination interceptor
     * - version optimistic lock interceptor
     * */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    /**
     * metaObject handler
     * - specific field's default value,such as time
     * */
    @Bean
    public MetaObjectHandler customizeMetaObjectHandler() {
        return new MetaObjectHandler() {

            // insert operation
            @Override
            public void insertFill(MetaObject metaObject) {
                Class<?> originalTimeClassType = getFieldType(metaObject.getOriginalObject(),CREATED_TIME_FIELD);
                if (null == originalTimeClassType) {
                    return;
                }
                if (originalTimeClassType.isAssignableFrom(LocalDateTime.class)) {
                    // 起始版本 3.3.3(推荐)
                    this.strictInsertFill(metaObject, CREATED_TIME_FIELD, LocalDateTime::now, LocalDateTime.class);
                    this.strictInsertFill(metaObject, UPDATE_TIME_FIELD, LocalDateTime::now, LocalDateTime.class);
                }
                if (originalTimeClassType.isAssignableFrom(Date.class)) {
                    // 起始版本 3.3.3(推荐)
                    this.strictInsertFill(metaObject, CREATED_TIME_FIELD, Date::new, Date.class);
                    this.strictInsertFill(metaObject, UPDATE_TIME_FIELD, Date::new, Date.class);
                }
            }

            // update operation
            @Override
            public void updateFill(MetaObject metaObject) {
                Class<?> originalTimeClassType = getFieldType(metaObject.getOriginalObject(),UPDATE_TIME_FIELD);
                if (null == originalTimeClassType) {
                    return;
                }
                if (originalTimeClassType.isAssignableFrom(LocalDateTime.class)) {
                    // 起始版本 3.3.3(推荐)
                    this.strictUpdateFill(metaObject, UPDATE_TIME_FIELD, LocalDateTime::now, LocalDateTime.class);
                }
                if (originalTimeClassType.isAssignableFrom(Date.class)) {
                    // 起始版本 3.3.3(推荐)
                    this.strictUpdateFill(metaObject, UPDATE_TIME_FIELD, Date::new, Date.class);
                }
            }

            private Class<?> getFieldType(Object target,String column) {
                try {
                    Field field = target.getClass().getDeclaredField(column);
                    field.setAccessible(true);
                    return field.getType();
                } catch (NoSuchFieldException e) {
                    return null;
                }
            }

        };
    }

}
