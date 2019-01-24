FROM docker.ekuaibao.com/jdk:8

MAINTAINER ekuaibao

ENV APP_HOME=/app
ENV CONF_ZK_SERVER=zookeeper:2181
ENV CONF_ZK_PATH=/sentinel

COPY target/sentinel-dashboard.jar /app/lib/sentinel-dashboard.jar
COPY startup.sh /app/bin/startup.sh

RUN chmod +x /app/bin/startup.sh

WORKDIR $APP_HOME

CMD [ "bin/startup.sh" ]
