#Start with a Linux micro-container
FROM maven:3.3.9-jdk-8

#Move your test cases to the container filesystem
ADD ./ /test

RUN ls -lrt /test/

#Setting default working directory
WORKDIR /test

#Verify installation in logs
RUN mvn -version

ENTRYPOINT ["mvn", "clean", "test"]