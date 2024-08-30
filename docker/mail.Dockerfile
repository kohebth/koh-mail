FROM openjdk:11-jdk-slim-buster

COPY build/distributions/mail-1.0-SNAPSHOT.tar /opt/dist.tar
WORKDIR /
RUN tar -xf /opt/dist.tar
RUN mv mail-1.0-SNAPSHOT/bin/* /bin
RUN mv mail-1.0-SNAPSHOT/lib/* /lib
RUN rm /opt/dist.tar
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=0.0.0.0:8081"

ENTRYPOINT [ "mail", "koh.service.mail.App" ]
