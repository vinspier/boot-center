package com.vinspier.mybatis.generate;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.vinspier.mybatis.config.DatasourceConfig;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
/**
 * 代码生策启动类
 *
 * @author  xiaobiao.fan
 * @date    2021/11/19 3:17 下午
*/
public class MyBatisPlusGenerator {

    private static String parterPackage="com.vinspier.mybatis.";//自定义包名
    private static String xmlMapper = "mappers";//生成resource目录下存放mapperxml的文件夹名

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        AutoGenerator mpg = new AutoGenerator();

//        String[] generateTables = new String[]{"goods","goods_attr_value","good_size_chart","website_goods_status","country_good_price","country_sku_price","product_languages"};
        String[] generateTables = new String[]{"good_onoffline_log"};

        String Author = "xiaobiao.fan";//开发者
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        projectPath += "/mybatis-generate/";

        gc.setFileOverride(true);
        gc.setSwagger2(true);
        gc.setActiveRecord(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 输出文件路径
        gc.setOutputDir(projectPath);
        gc.setAuthor(Author);
        gc.setServiceName("%sDao");

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = DatasourceConfig.getDscTest();
        mpg.setDataSource(dsc);

        for (String table : generateTables){
            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
//        strategy.setTablePrefix("qrtz_");//移除表前缀
            strategy.setEntityLombokModel(true);
            strategy.setRestControllerStyle(true);
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);

            //只生成包含qrtz_的表
//        strategy.setExclude("^((?!goods).)*$");

            //移除包含qrtz_的表
//        strategy.setInclude("^((?!good).)*$");

            //setExclude setInclude 属性需要设置为false
            strategy.setEnableSqlFilter(false);
//            strategy.setExclude("^((?!" + table +").)*$");
//            strategy.setExclude("^" + table +"$");
            strategy.setInclude("^" + table +"$");
            mpg.setStrategy(strategy);

            // 包配置
            PackageConfig pc = new PackageConfig();
            mpg.setPackageInfo(pc);
            // 执行生成

            customPackagePath(pc,mpg);
            mpg.execute();
        }
    }


    /**
     * 自定义包路径，文件生成路径，这边配置更灵活
     * 虽然也可以使用InjectionConfig设置FileOutConfig的方式设置路径
     * 这里直接使用Map方式注入ConfigBuilder配置对象更加直观
     * @param pc
     * @param mpg
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void customPackagePath(PackageConfig pc,AutoGenerator mpg) throws NoSuchFieldException, IllegalAccessException {

        String projectPath = System.getProperty("user.dir");
        projectPath += "/mybatis-generate";
        String mavenPath = "/src/main/java/";
        String srcPath = projectPath + mavenPath;

        /**
         * packageInfo配置controller、service、serviceImpl、entity、mapper等文件的包路径
         * 这里包路径可以根据实际情况灵活配置
         */
        Map<String,String> packageInfo = new HashMap<>();
        packageInfo.put(ConstVal.CONTROLLER, parterPackage+"controller");
        packageInfo.put(ConstVal.SERVICE, parterPackage+"services");
        packageInfo.put(ConstVal.SERVICE_IMPL, parterPackage+"services.service.impl");
        packageInfo.put(ConstVal.ENTITY, parterPackage+"entity");
        packageInfo.put(ConstVal.MAPPER, parterPackage+"mapper");

        /**
         * pathInfo配置controller、service、serviceImpl、entity、mapper、mapper.xml等文件的生成路径
         * srcPath也可以更具实际情况灵活配置
         * 后面部分的路径是和上面packageInfo包路径对应的源码文件夹路径
         * 这里你可以选择注释其中某些路径，可忽略生成该类型的文件，例如:注释掉下面pathInfo中Controller的路径，就不会生成Controller文件
         */
        Map pathInfo = new HashMap<>();
        pathInfo.put(ConstVal.CONTROLLER_PATH, srcPath + packageInfo.get(ConstVal.CONTROLLER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.SERVICE_PATH, srcPath + packageInfo.get(ConstVal.SERVICE).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, srcPath + packageInfo.get(ConstVal.SERVICE_IMPL).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.ENTITY_PATH, srcPath + packageInfo.get(ConstVal.ENTITY).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.MAPPER_PATH, srcPath + packageInfo.get(ConstVal.MAPPER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.XML_PATH, projectPath + "/src/main/resources/" + xmlMapper +"");
        pc.setPathInfo(pathInfo);

        /**
         * 创建configBuilder对象，传入必要的参数
         * 将以上的定义的包路径packageInfo配置到赋值到configBuilder对象的packageInfo属性上
         * 因为packageInfo是私有成员变量，也没有提交提供公共的方法，所以使用反射注入
         * 为啥要这么干，看源码去吧
         */
        ConfigBuilder configBuilder = new ConfigBuilder(mpg.getPackageInfo(), mpg.getDataSource(), mpg.getStrategy(), mpg.getTemplate(), mpg.getGlobalConfig());
        Field packageInfoField = configBuilder.getClass().getDeclaredField("packageInfo");
        packageInfoField.setAccessible(true);
        packageInfoField.set(configBuilder,packageInfo);

        /**
         * 设置配置对象
         */
        mpg.setConfig(configBuilder);
    }

}
