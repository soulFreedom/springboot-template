#!/bin/bash

# ���ö��ڴ��СΪ200M
export JAVA_OPTS="-Xmx200m"

# ���� Spring Boot ��Ŀ
rm -rf nohup.log
touch nohup.log
nohup /usr/java/jdk1.8.0_73/bin/java $JAVA_OPTS -jar "/usr/mpsp/demo/classes/demo-1.0.jar"  > nohup.log &
sleep 2
echo $! > server.pid
tail -f nohup.log
