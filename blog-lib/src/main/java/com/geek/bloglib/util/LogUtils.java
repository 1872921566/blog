package com.geek.bloglib.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtils {

    public static void info(Object object){
      log.info("=======================================================");

      log.info("object==>{}",object);

      log.info("=======================================================");
    }
}
