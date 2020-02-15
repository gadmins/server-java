import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGen {
    private final static String moduleId = "gadmins-app";
    private final static String parent = "com.itfenbao.gadmins.app";
    private final static String tablePrefix = "sys_admin_";
    private final static String db = "gadmins";
    private final static String username = "root";
    private final static String password = "java";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/" + moduleId + "/src/main/java");
        globalConfig.setAuthor("itfenbao");
        globalConfig.setOpen(false);
        mpg.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/" + db + "?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        mpg.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("app");
        pc.setParent(parent);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setRestControllerStyle(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setSuperEntityClass("com.itfenbao.gadmins.common.entity.BaseEntity");
        strategy.setSuperEntityColumns("id", "created_by", "updated_by", "created_at", "updated_at", "enable");
//        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(tablePrefix);
//        strategy.setNameConvert(new INameConvert() {
//            @Override
//            public String entityNameConvert(TableInfo tableInfo) {
//                return null;
//            }
//
//            @Override
//            public String propertyNameConvert(TableField field) {
//                return null;
//            }
//        });

        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }
}
