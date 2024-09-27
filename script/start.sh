#!/bin/bash

# 设置堆内存大小为200M
export JAVA_OPTS="-Xmx200m"

# 启动 Spring Boot 项目
rm -rf nohup.log
touch nohup.log
nohup /usr/java/jdk1.8.0_73/bin/java $JAVA_OPTS -jar "/usr/mpsp/demo/classes/demo-1.0.jar"  > nohup.log &
sleep 2
echo $! > server.pid
tail -f nohup.log
