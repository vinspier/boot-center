package com.vinspier.plus.dal.support;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 抽象查询 通用定义
 * O -> 出口持久化对象 PO
 * I -> 入口查询对象 param
 *
 * @author  xiaobiao.fan
 * @date    2021/11/20 6:21 下午
*/
public interface BaseDao<O,I extends BaseParam<O>> extends IService<O> {

    String LIMIT_ONE_CONDITION = " limit 1";

    String ID_COLUMN = "id";
    String STATUS_COLUMN = "status";
    String CREATED_AT_COLUMN = "create_at";
    String UPDATED_AT_COLUMN = "update_at";
    String VERSION_COLUMN = "version";

    // ----------------- unique data read extension module -----------------
    /**
     * 单个查询
     * @param keyFunction: unique key which shall ensure that there's only one data existing in db
     * @param keyValue: value of query key field
     * */
    default <R> O getOne(SFunction<O,R> keyFunction, Object keyValue) {
        QueryWrapper<O> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(keyFunction,keyValue);
        wrapper.last(LIMIT_ONE_CONDITION);
        return getOne(wrapper);
    }

    /**
     * 单个查询
     * @param : multiply query conditions make sure that there's only one data existing in db
     * */
    default O getOne(I param) {
        QueryWrapper<O> wrapper = buildQueryWrapper(param);
        applyOrderCondition(wrapper,param);
        wrapper.last(LIMIT_ONE_CONDITION);
        return getOne(wrapper);
    }

    // ----------------- list data read module -----------------
    /**
     * 列表查询
     * */
    default List<O> queryList(I param) {
        QueryWrapper<O> wrapper = buildQueryWrapper(param);
        applyOrderCondition(wrapper,param);
        return list(wrapper);
    }

    /**
     * 列表查询 转map
     * */
    default <R> Map<R,O> queryListToMap(I param, Function<? super O, ? extends R> keyFunction) {
        List<O> dataList = queryList(param);
        return dataList.stream()
                .collect(Collectors.toMap(keyFunction,Function.identity()));
    }

    /**
     * 列表查询 转map
     * */
    default <R> Map<R,List<O>> queryListToGroup(I param, Function<? super O, ? extends R> keyFunction) {
        List<O> dataList = queryList(param);
        return dataList.stream()
                .collect(Collectors.groupingBy(keyFunction));
    }

    /**
     * 列表查询
     * 聚合某一字段
     * */
    default <R> Set<R> queryListAggregateField(I param, Function<? super O, ? extends R> fieldFunction) {
        List<O> dataList = queryList(param);
        return dataList.stream()
                .map(fieldFunction)
                .collect(Collectors.toSet());
    }

    /**
     * 关联键 列表查询
     * */
    default <R> List<O> getList(SFunction<O,R> keyFunction, Object keyValue) {
        QueryWrapper<O> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(keyFunction,keyValue);
        return list(wrapper);
    }

    /**
     * 列表查询 转map
     * */
    default <R,F> Map<F,O> getListToMap(SFunction<O,R> keyFunction, Object keyValue, Function<? super O, ? extends F> fieldFunction) {
        List<O> dataList = getList(keyFunction,keyValue);
        return dataList.stream()
                .collect(Collectors.toMap(fieldFunction,Function.identity()));
    }

    /**
     * 关联键 列表查询
     * R 返回聚合的字段
     * F 查询的关键字段
     * */
    default <R,F> Set<R> getListAggregateField(SFunction<O,F> keyFunction, Object keyValue,Function<? super O, ? extends R> fieldFunction) {
        List<O> dataList = getList(keyFunction,keyValue);
        return dataList.stream()
                .map(fieldFunction)
                .collect(Collectors.toSet());
    }

    // ----------------- page data read module -----------------
    /**
     * 分页查询
     * */
    default Page<O> queryPage(I param,int pageIndex,int pageSize) {
        QueryWrapper<O> wrapper = buildQueryWrapper(param);
        applyOrderCondition(wrapper,param);
        return this.queryPage(pageIndex,pageSize,wrapper);
    }

    /**
     * 分页查询
     * */
    default Page<O> queryPage( int pageIndex,int pageSize, QueryWrapper<O> wrapper) {
        Page<O> page = Page.of(pageIndex,pageSize);
        return page(page,wrapper);
    }

    /**
     * 分页查询
     * */
    default Page<O> queryIdPage( int pageIndex,int pageSize) {
        QueryWrapper<O> wrapper = new QueryWrapper<>();
        wrapper.select(ID_COLUMN);
        Page<O> page = Page.of(pageIndex,pageSize);
        return page(page,wrapper);
    }

    // ----------------- counting data read module -----------------
    /**
     * 统计数量
     * */
    default long count(I param) {
        QueryWrapper<O> wrapper = buildQueryWrapper(param);
        applyOrderCondition(wrapper,param);
        return count(wrapper);
    }

    // ----------------- data write module -----------------
    /**
     * 条件更新
     * todo 整合成一次批量
     * */
    default boolean updateBatch(List<I> paramList){
        paramList.forEach(this::update);
        return true;
    }

    /**
     * 条件更新
     * */
    default boolean update(I param){
        if (!updateLimitCondition(param)) {
            throw new RuntimeException(String.format("updates data is forbidden while missing where limit condition, param body = [%s]",JSONObject.toJSONString(param)));
        }
        UpdateWrapper<O> wrapper = buildUpdateWrapper(param);

        return update(wrapper);
    }

    /**
     * 条件更新
     * */
    default boolean update(List<I> params){
        params.forEach(this::update);
        return true;
    }

    /**
     * 条件保存/更新
     * */
    default boolean saveOrUpdate(O po,I param){
        UpdateWrapper<O> wrapper = buildUpdateWrapper(param);
        return saveOrUpdate(po,wrapper);
    }

    /**
     * 多条件删除
     * */
    default boolean remove(I param){
        QueryWrapper<O> wrapper = buildQueryWrapper(param);
        return remove(wrapper);
    }

    /**
     * 单字段条件删除
     * */
    default <R> boolean remove(SFunction<O,R> keyFunction, Object keyValue) {
        QueryWrapper<O> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(keyFunction,keyValue);
        return remove(wrapper);
    }

    /**
     * 构建公用查询wrapper
     */
    default QueryWrapper<O> buildBasicQueryWrapper(I param) {
        QueryWrapper<O> wrapper = new QueryWrapper<>();
        wrapper.eq(Objects.nonNull(param.getId()),ID_COLUMN,param.getId());
        wrapper.in(CollectionUtils.isNotEmpty(param.getIdSet()),ID_COLUMN,param.getIdSet());
        wrapper.ge(Objects.nonNull(param.getBeginId()),ID_COLUMN,param.getId());
        wrapper.le(Objects.nonNull(param.getEndId()),ID_COLUMN,param.getId());
        wrapper.eq(Objects.nonNull(param.getStatus()),STATUS_COLUMN,param.getStatus());
        wrapper.eq(Objects.nonNull(param.getVersion()),VERSION_COLUMN,param.getVersion());
        wrapper.ge(Objects.nonNull(param.getCreatedAtBegin()),CREATED_AT_COLUMN,param.getCreatedAtBegin());
        wrapper.le(Objects.nonNull(param.getCreatedAtSuspend()),CREATED_AT_COLUMN,param.getCreatedAtSuspend());
        wrapper.ge(Objects.nonNull(param.getCreatedAtStart()),CREATED_AT_COLUMN,param.getCreatedAtStart());
        wrapper.le(Objects.nonNull(param.getCreatedAtEnd()),CREATED_AT_COLUMN,param.getCreatedAtEnd());
        return wrapper;
    }

    /**
     * 构建查询wrapper
     */
    default QueryWrapper<O> buildQueryWrapper(I param) {

        return buildBasicQueryWrapper(param);
    }

    /**
     * 构建公用查询wrapper
     */
    default UpdateWrapper<O> buildUpdateWrapper(I param) {

        return new UpdateWrapper<>();
    }

    /**
     * 判断更新限定条件
     * */
    default boolean updateLimitCondition(I param) {
        return Objects.nonNull(param.getId()) || CollectionUtils.isNotEmpty(param.getIdSet());
    }

    /**
     * 添加排序规则
     * 默认 先升序条件 后降序条件
     * */
    default void applyOrderCondition(QueryWrapper<O> wrapper,I param) {
        wrapper.lambda()
                .orderByAsc(CollectionUtils.isNotEmpty(param.getAscOrders()),param.getAscOrders())
                .orderByAsc(CollectionUtils.isNotEmpty(param.getDescOrders()),param.getDescOrders());
    }

}
