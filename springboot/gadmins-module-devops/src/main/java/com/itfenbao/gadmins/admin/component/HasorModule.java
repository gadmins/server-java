package com.itfenbao.gadmins.admin.component;

import lombok.extern.slf4j.Slf4j;
import net.hasor.core.ApiBinder;
import net.hasor.core.AppContext;
import net.hasor.core.DimModule;
import net.hasor.dataql.DataQL;
import net.hasor.db.JdbcModule;
import net.hasor.db.Level;
import net.hasor.spring.SpringModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@DimModule
@Component
public class HasorModule implements SpringModule {
    @Autowired
    private DataSource dataSource;

    private AppContext appContext;

    private DataQL hasorDataQl;

    @Override
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        // .DataSource form Spring boot into Hasor
        apiBinder.installModule(new JdbcModule(Level.Full, this.dataSource));
        // .custom DataQL
        //apiBinder.tryCast(QueryApiBinder.class).loadUdfSource(apiBinder.findClass(DimUdfSource.class));
        //apiBinder.tryCast(QueryApiBinder.class).bindFragment("sql", SqlFragment.class);
    }

    @Override
    public void onStart(AppContext appContext) throws Throwable {
        this.appContext = appContext;
        hasorDataQl = appContext.getInstance(DataQL.class);
    }

    @Lazy
    @Bean
    public DataQL dataQL() {
        return hasorDataQl;
    }
}
