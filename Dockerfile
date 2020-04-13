FROM ibmjava:8-sdk
LABEL maintainer="IBM Java Engineering at IBM Cloud"

#ARG JAR_FILE=target/*.jar

#COPY ${JAR_FILE} cardlinkpossiblepwa.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
RUN apt-get update
RUN apt-get -y install curl gnupg
RUN curl -sL https://deb.nodesource.com/setup_11.x  | bash -
RUN apt-get -y install nodejs
RUN npm install

COPY target/cardlinkpossiblepwa-2.0-SNAPSHOT.jar cardlinkpossiblepwa.jar
ENTRYPOINT ["sh", "-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /cardlinkpossiblepwa.jar"]