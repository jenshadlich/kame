FROM ubuntu:trusty

RUN \
  apt-get update && \
  apt-get install -y software-properties-common

RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

WORKDIR /data

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

CMD ["bash"]

ADD target/crawler-service-0.1-SNAPSHOT.jar /data/crawler-service-0.1-SNAPSHOT.jar
ADD config.yaml /data/config.yaml

CMD java -jar crawler-service-0.1-SNAPSHOT.jar server /data/config.yaml

EXPOSE 8080