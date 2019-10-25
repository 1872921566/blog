package com.geek.bloglib.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInfoUtils {

    public static void log(Object object){
      log.info("=======================================================");

      log.info("object==>{}",object);

      log.info("=======================================================");
    }
}
