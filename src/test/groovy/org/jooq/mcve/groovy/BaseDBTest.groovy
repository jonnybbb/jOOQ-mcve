package org.jooq.mcve.groovy

import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.jooq.mcve.test.java.JooqConfigurationFactory
import spock.lang.AutoCleanup
import spock.lang.Specification

class BaseDBTest extends Specification {

    @AutoCleanup
    DSLContext jooq

    void setup() {
        jooq = DSL.using(JooqConfigurationFactory.createJooqConfiguration())
    }
}
