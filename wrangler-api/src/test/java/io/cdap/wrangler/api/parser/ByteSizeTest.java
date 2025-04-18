package io.cdap.wrangler.api.parser;
import org.junit.Assert;
import org.junit.Test;

public class ByteSizeTest {

    @Test
    public void testParseKB(){
        ByteSize size = new ByteSize("10kb");
        Assert.assertEquals(10240, size.getBytes());
    }

    @Test
    public void testParseMB(){
        ByteSize size = new ByteSize("1.5MB");
        Assert.assertEquals(1572864,size.getBytes());
    }



}
