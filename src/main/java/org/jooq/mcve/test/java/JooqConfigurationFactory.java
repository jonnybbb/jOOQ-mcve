package org.jooq.mcve.test.java;

import org.jooq.Configuration;
import org.jooq.ExecuteListener;
import org.jooq.ExecuteListenerProvider;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JooqConfigurationFactory {

    public static Configuration createJooqConfiguration(Optional<ExecuteListener> maybeProfilerProvider) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mcve", "sa", "");
            var settings = new Settings();
            settings.setRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_QUOTED);
            //we add some more listeners - depending on runtime settings
            List<ExecuteListenerProvider> executeListenerProviders = new ArrayList<>();
            maybeProfilerProvider.ifPresent(profilerProvider -> executeListenerProviders.add(new DefaultExecuteListenerProvider(profilerProvider)));
            var configuration = new DefaultConfiguration();
            configuration
                    .set(SQLDialect.POSTGRES)
                    .set(connection)
                    .set(executeListenerProviders.toArray(new ExecuteListenerProvider[]{}))
                    .set(settings);
            return configuration;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
