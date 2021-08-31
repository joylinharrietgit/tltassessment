## TltTestAssignment
Application that allows user to create ,update and delete the notes. Restful API for application has been developed.

##Technologies and tools used

1.Java version 11.0 

2. Spring Boot Framework version 2.4.10


3. MongoDb 5.0.2 (Community Server) OR Mongo Container using Docker 
 
4. Maven Build tool

5. Eclipse IDE for development

6. Swagger2 for API Docuemntation (Springfox 3.0.0)

## Running the application using Docker:
1. Build the Project using Maven Build tool in your IDE
2. Pull the mongodb from the Docker hub create the image for the container:
    ```docker
    docker pull mongo:5.0.2
    ```
    Image will be created for the DB.
    
3. Using Docker, build the docker image from the Dockerfile location in the project root folder.
    ```docker
    docker build -t tlttest-notes-app:2.0
    ```
4. Check for the Docker images
    ```docker
    docker images
    ```
5. Depoy the application using Docker compose
  ```docker-compose 
  docker-compose up
  ```

The docker images can be pushed to the docker hub using the command:
docker image push username/tagname

## Testing and API endpoints:
  For the API endpoints refer to the below URL once the spring application is started successfully

http://localhost:8080/swagger-ui/ Under the Controller the endpoints are listed and they can also be tested.

