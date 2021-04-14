#chrome + openjdk
FROM includeno/javachrome:openjdk-11.0.10-jdkchrome89.0.4389.114

EXPOSE 8080
WORKDIR /app

#add jar file
ADD spider/target/spider-0.1.jar /app/application.jar

ENV appname=spider8080

CMD java -jar /app/application.jar --spring.application.name=${appname}