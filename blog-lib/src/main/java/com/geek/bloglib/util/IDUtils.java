package com.geek.bloglib.util;

import java.util.UUID;

public class IDUtils {

    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
