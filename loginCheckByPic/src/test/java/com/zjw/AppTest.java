package com.zjw;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testBigDecimaladd(){
        List<BigDecimal> bigDecimals = new ArrayList<>();
        bigDecimals.add(new BigDecimal("1"));
        bigDecimals.add(new BigDecimal("1"));
        bigDecimals.add(new BigDecimal("7"));
        bigDecimals.add(new BigDecimal("1"));
        bigDecimals.add(new BigDecimal("1"));
        BigDecimal reduce = bigDecimals.stream().reduce(BigDecimal.ONE, BigDecimal::multiply);

        Integer i = 10;
        Integer o = null;

        String s = Integer.toString(i);
        String t = Integer.toString(o);
        System.out.println("t = " + t);
        System.out.println("s = " + s);


        assertTrue(reduce.compareTo(new BigDecimal("7"))==0);
    }
}
