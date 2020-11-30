package org.jooq.mcve.groovy


import org.jooq.mcve.java.Routines

class SpockAutocleanupTest extends BaseDBTest {

    def "A simple DB test"() {

        when:
        def result = Routines.foo(jooq.configuration(), 1)

        then:
        result == 2
    }

}
