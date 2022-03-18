FROM ubuntu:20.04

# 타임존 설정
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

#  미러 서버 설정
RUN sed -i 's@archive.ubuntu.com@ftp.harukasan.org@g' /etc/apt/sources.list &&\
  RUN sed -i 's@ports.ubuntu.com@ftp.harukasan.org@g' /etc/apt/sources.list

# 필요한 패키지 설치
RUN apt update &&\
  RUN apt install -y nano openjdk-17-jdk git

# git 설정
RUN git config --global user.name "UniM0cha" &&\
  RUN git config --global user.email "solst_ice@naver.com" &&\
  RUN git config --global credential.helper store

# git clone
WORKDIR /root/github/
RUN git clone https://github.com/UniM0cha/Tutorial-Spring.git