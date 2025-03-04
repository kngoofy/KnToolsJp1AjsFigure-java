#!/bin/bash 

java \
  -cp ".;../../../build/libs/*;" \
  -XX:+UseG1GC \
  -Xms4096M -Xmx8192M \
  -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=1024m \
  -Dlog4j2.loggerContextFactory=org.apache.logging.log4j.simple.SimpleLoggerContextFactory \
  -Xlog:gc*:file=../../../../target/gclog/gc.log:tags,time,uptime \
  \
  -Dlog4j2.loggerContextFactory=org.apache.logging.log4j.simple.SimpleLoggerContextFactory \
  org.westclan.kntools.jp1ajsfigure.util.FromFileMain \
  ../../main/resources/Template-KnToolsJp1AjsFigure.xlsm \
  ../../../../target/ShellExtargetJP1AJS.xlsm \
  ./def/jp1ajs.def

 exit
