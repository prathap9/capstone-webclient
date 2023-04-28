FROM openjdk:8
EXPOSE 9797
ADD build/libs/employee-management-system-webclient-0.0.1-SNAPSHOT.jar employee-management-system-webclient-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/employee-management-system-webclient-0.0.1-SNAPSHOT.jar"]