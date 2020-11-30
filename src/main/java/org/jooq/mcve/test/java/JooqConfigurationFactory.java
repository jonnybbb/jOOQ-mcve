package org.jooq.mcve.test.java;

import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JooqConfigurationFactory {

    public static Configuration createJooqConfiguration() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mcve", "sa", "");
            var settings = new Settings();
            settings.setRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_QUOTED);
            var configuration = new DefaultConfiguration();
            configuration
                    .set(SQLDialect.POSTGRES)
                    .set(connection)
                    .set(settings);
            return configuration;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
