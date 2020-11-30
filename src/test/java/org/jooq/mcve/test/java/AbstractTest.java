package org.jooq.mcve.test.java;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractTest {

    public Connection connection;
    public DSLContext ctx;


    @Before
    public void setup() throws Exception {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mcve", "sa", "");
        var settings = new Settings();
        settings.setRenderQuotedNames(RenderQuotedNames.ALWAYS);
        var configuration = new DefaultConfiguration();
        configuration
                .set(SQLDialect.POSTGRES)
                .set(connection)
                .set(settings);
        ctx = DSL.using(configuration);
    }

    @After
    public void after() throws Exception {
        ctx = null;
        connection.close();
        connection = null;
    }
}
