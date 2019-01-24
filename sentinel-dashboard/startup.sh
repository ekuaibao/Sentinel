#!/bin/bash

CP="/conf"

add_cp() {
  for L in $@; do
    CP="$CP:$L"
  done
}

add_cp $APP_HOME/lib/*.jar

exec java $JAVA_OPTS \
     -Dserver.port=8080 \
     -Dcsp.sentinel.dashboard.server=localhost:8080 \
     -Dcsp.sentinel.api.context=${CONF_CONTEXT_PATH} \
     -Dproject.name=sentinel-dashboard \
     -cp $CP \
     org.springframework.boot.loader.JarLauncher $@