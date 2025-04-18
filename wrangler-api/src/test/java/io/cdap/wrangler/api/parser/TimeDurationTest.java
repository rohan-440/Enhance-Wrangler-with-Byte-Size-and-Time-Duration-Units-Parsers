package io.cdap.wrangler.api.parser;
import io.cdap.wrangler.api.Optional;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TimeDurationTest {

    @Test
    public void testMS(){
        TimeDuration time = new TimeDuration("5ms");
        Assert.assertEquals(5,time.getMillis());
    }

    @Test
    public void testS(){
        TimeDuration time = new TimeDuration("2.1s");
        Assert.assertEquals(2100,time.getMillis());
    }
}
