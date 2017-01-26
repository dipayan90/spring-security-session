FROM java:8
COPY target /home/default/
WORKDIR /home/default/
CMD ["java","-jar","sessionwebapp-0.0.1-SNAPSHOT.jar"]