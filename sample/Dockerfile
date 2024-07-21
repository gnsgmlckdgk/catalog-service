#1. 새 이미지에 대한 베이스 이미지로 우분투 22.04를 지정한다.
FROM ubuntu:22.04

#2. 익숙한 bash 명령을 사용해 JRE를 설치한다.
RUN apt-get update && apt-get install -y default-jre

#3. 실행 컨테이너의 엔트리 포인트를 정의한다.
# (컨테이너 실행을 위한 진입점, 가상머신과 달리 컨테이너는 운영체제를 실행하기 위한 것이 아니라 작업을 실행하기 위한 것)
# (실제로 docker run ubuntu 를 실행하면 컨테이너는 즉시 빠져나온다. => 어떤 작업도 엔트리 포인트로 정의되지 않았기 때문이다.)
ENTRYPOINT ["java", "--version"]
