import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * 代码生成器
 *
 * @author itfenbao
 */
public class CodeGen {
    /**
     * 决定生成代码路径
     */
    private final static String MODULE_ID = "gadmins-module-admin";
    /**
     * 包名
     */
    private final static String PARENT = "com.itfenbao.gadmins";
    /**
     * 模块名
     */
    private final static String MODULE_NAME = "admin";
    /**
     * 表前缀
     */
    private final static String TABLE_PREFIX = "sys_admin_";

    private final static String db = "gadmins";
    private final static String username = "root";
    private final static String password = "java";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/" + MODULE_ID + "/src/main/java");
        globalConfig.setAuthor("itfenbao");
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true);
        mpg.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/" + db + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        mpg.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT);
        pc.setModuleName(MODULE_NAME);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setRestControllerStyle(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setSuperEntityClass("com.itfenbao.gadmins.core.entity.BaseEntity");
        strategy.setSuperEntityColumns("id", "created_by", "updated_by", "created_at", "updated_at", "enable");
        strategy.setTablePrefix(TABLE_PREFIX);
        strategy.setLikeTable(new LikeTable(TABLE_PREFIX, SqlLike.RIGHT));
        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }
}
