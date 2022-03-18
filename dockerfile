FROM ubuntu:20.04

RUN sed -i 's@archive.ubuntu.com@kr.archive.ubuntu.com@g' /etc/apt/sources.list
RUN apt update
RUN apt install nano openjdk-17-jdk git
RUN git config --global user.name "UniM0cha"
RUN git config --global user.email "solst_ice@naver.com"
RUN git config --global credential.helper store

WORKDIR /root/github/
RUN git clone https://github.com/UniM0cha/Tutorial-Spring.git