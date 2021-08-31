TltTestProject
Application that allows user to create ,update and delete the notes. Restful API for application has been developed.

#Technologies and tools used

1.Java version 11.0 
2. Spring Boot Framework version 2.4.10
3. MongoDb 5.0.2 (Community Server) OR Mongo Container using Docker 
4. Maven Build tool
5. Eclipse IDE for development
6. Swagger2 for API Docuemntation (Springfox 3.0.0)

#Running the application Locally using Docker:
1. Run the mongodb using Docker Container (Pull the mongodb from the Docker hub and start the server on port 27017)
2. Build the Project using Maven Build tool.
3. Using Docker, build the docker image from the Dockerfile location in the project root foler.
4. Check for both mongodb and application docker images.
5. Run the application docker by linking it to the mongodb using the command mentioned below:
docker run -p 8080:8080 --name tlttest-notesapp-mongo --link mymongodb:mongo -d tlttest-notes-app:1.0

The docker images can be pushed to the docker hub using the command:
docker image push username/tagname

Command to execute the Spring Application (without docker) from the terminal : java -jar jarname (This has to be executed from the location where the jar file is present)

#Testing and API endpoints:
#For the API endpoints refer to the below URL once the spring application is started successfully

http://localhost:8080/swagger-ui/ Under the Controller the endpoints are listed and they can also be tested.

