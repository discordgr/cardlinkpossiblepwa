FROM ibmjava:8-sdk
LABEL maintainer="IBM Java Engineering at IBM Cloud"

#ARG JAR_FILE=target/*.jar

#COPY ${JAR_FILE} cardlinkpossiblepwa.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

COPY target/cardlinkpossiblepwa-2.0-SNAPSHOT.jar cardlinkpossiblepwa.jar
ENTRYPOINT ["java","-jar","/cardlinkpossiblepwa.jar"]