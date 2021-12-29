package com.vinspier.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

public class DatasourceConfig {

    private DatasourceConfig() {

    }

    private static  DataSourceConfig DSC_LOCAL = new DataSourceConfig();

    private static  DataSourceConfig DSC_TEST = new DataSourceConfig();


    public static DataSourceConfig getDscLocal(){
        DSC_LOCAL.setDbType(DbType.MYSQL);

        //自定义类型转换
        DSC_LOCAL.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });

        DSC_LOCAL.setDriverName(jdbc_drivername);
        DSC_LOCAL.setUsername(jdbc_username);
        DSC_LOCAL.setPassword(jdbc_password);
        DSC_LOCAL.setUrl(jdbc_connectionURL);

        return DSC_LOCAL;
    }

    public static DataSourceConfig getDscTest(){
        DSC_TEST.setDbType(DbType.MYSQL);

        //自定义类型转换
        DSC_TEST.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });

        return DSC_TEST;
    }

    /**  本地数据链接配置 */
    private static String jdbc_drivername = "com.mysql.cj.jdbc.Driver";
    private static String ip_port = "localhost:3306/";
    private static String database = "test";
    private static String jdbc_connectionURL = "jdbc:mysql://" + ip_port + database + "?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    private static String jdbc_username = "root";
    private static String jdbc_password = "Root@123";


}
