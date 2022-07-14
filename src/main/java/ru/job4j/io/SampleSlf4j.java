package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleSlf4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean bool = true;
        byte b = 1;
        short sh = 10;
        int i = 100;
        long l = 1000L;
        float f = 1.1f;
        double d = 1.111d;
        char ch = '0';
        LOG.debug("bool={}, b={}, sh={}, i={}, l={}, f={}, d={}, ch={}",
                bool, b, sh, i, l, f, d, ch);

    }
}
