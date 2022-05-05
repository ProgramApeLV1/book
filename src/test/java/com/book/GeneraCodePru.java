package com.book;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class GeneraCodePru {
    private static GeneraCodePru single = null;

    private GeneraCodePru() {
        super();
    }

    private static GeneraCodePru getSingle() {
        if (single == null) {
            single = new GeneraCodePru();
        }
        return single;
    }

//    public void autoGeneration() {
//        GlobalConfig config = new GlobalConfig();
//        String dbUrl = "jdbc:mysql://localhost:3306/book?serverTimezone=GMT%2B8";
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setDbType(DbType.MYSQL)
//                .setUrl(dbUrl)
//                .setUsername("root")
//                .setPassword("123456")
//                .setDriverName("com.mysql.cj.jdbc.Driver");
//        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig
//                .setEntityLombokModel(false)
//                .setDbColumnUnderline(false)
//                .setTablePrefix(new String[]{"os_","cs_"})
//                .setNaming(NamingStrategy.underline_to_camel);
//        config.setActiveRecord(false)
//                .setEnableCache(false)
//                .setIdType(IdType.ASSIGN_UUID)
//                .setAuthor("wyh")
//                //指定输出文件夹位置
//                .setOutputDir("C:\\Users\\DocWa\\Desktop\\sssss")
//                .setFileOverride(true)
//                .setServiceName("I%sService")
//                .setServiceImplName("%sServiceImpl")
//                .setControllerName("%sController")
//                .setMapperName("%sMapper")
//                .setXmlName("%sMapper")
//                .setBaseColumnList(true);
//        new AutoGenerator().setGlobalConfig(config)
//                .setDataSource(dataSourceConfig)
//                .setStrategy(strategyConfig)
//                .setPackageInfo(
//                        new PackageConfig()
//                                .setParent("com.book")
//                                .setController("controller")
//                                .setEntity("model")
//                                .setXml("xml")
//                ).execute();
//    }

    public void autoGenerationV2() {
        String dbUrl = "jdbc:mysql://localhost:3306/book?serverTimezone=GMT%2B8";
        String outPath = "C:\\Users\\Wangyh\\Desktop";
        FastAutoGenerator.create(dbUrl, "root", "123456")
                .globalConfig(builder -> {
                    builder.author("wyh") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outPath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.book") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("os_role") // 设置需要生成的表名
                            .addTablePrefix("os_","cs_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GeneraCodePru generator = GeneraCodePru.getSingle();
        generator.autoGenerationV2();
    }

}
