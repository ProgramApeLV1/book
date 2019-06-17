package com.book;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GeneraCodePru {
    private static GeneraCodePru single = null;

    private GeneraCodePru() {
        super();
    }

    private static GeneraCodePru getSingle() {
        if(single == null) {
            single =new GeneraCodePru();
        }
        return single;
    }

    public void autoGeneration() {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/book?serverTimezone=GMT%2B8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setEntityLombokModel(false)
                .setDbColumnUnderline(false)
                .setTablePrefix(new String[]{"os_","cs_"})
                .setNaming(NamingStrategy.underline_to_camel);
        config.setActiveRecord(false)
                .setEnableCache(false)
                .setIdType(IdType.AUTO)
                .setAuthor("wyh")
                //指定输出文件夹位置
                .setOutputDir("C:\\Users\\DocWa\\Desktop\\sssss")
                .setFileOverride(true)
                .setServiceName("I%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setBaseColumnList(true);
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent("com.book")
                                .setController("controller")
                                .setEntity("model")
                                .setXml("xml")
                ).execute();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GeneraCodePru generator = GeneraCodePru.getSingle();
        generator.autoGeneration();
    }

}
