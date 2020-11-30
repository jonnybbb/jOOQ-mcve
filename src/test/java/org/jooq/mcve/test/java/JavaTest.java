package org.jooq.mcve.test.java;

import org.jooq.mcve.java.Routines;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaTest extends AbstractTest {

    @Test
    public void mcveTest() {
        Integer foo = Routines.foo(ctx.configuration(), 1);
        assertEquals(2, foo.intValue());
    }
}
