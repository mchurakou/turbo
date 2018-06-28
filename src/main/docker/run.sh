#!/bin/sh

echo "********************************************************"
echo "Waiting for the database server to start on port $DATABASESERVER_PORT"
echo "********************************************************"
while ! `nc -z $H2_URL $H2_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"



echo "********************************************************"
echo "Starting turvo-service "
echo "********************************************************"
java -DH2_URL=$H2_URL:$H2_PORT -Dspring.profiles.active=$PROFILE -jar /usr/local/turvo-service/@project.build.finalName@.jar
