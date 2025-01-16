FROM swr.cn-south-1.myhuaweicloud.com/djdj/base/openjdk:jdk-21.0.2
WORKDIR /opt/project
COPY ./target/*.jar app.jar
EXPOSE 8000
ENV JAVA_DJDJ_OPTS="-Xms1g -Xmx1g -Xlog:gc*:file=gc-%t.log:tags,uptime,time,level:filecount=5,filesize=10M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heapdump.hprof"
CMD java -Dfile.encoding=UTF-8 -Duser.timezone=GMT+08 -Dserver.port=8000 -jar $JAVA_DJDJ_OPTS app.jar
