# Archive

# About

Online Championships Management API Project

    Contains the apis below
        Player:
            - Get player
            - Create player
            - Update player
            - Delete player
            - Archive to create player
            
        Team:
            - Get Team
            - Create Team
            - Delete Team
            - Add member to team

## Create mongoDB environment

Run following commands to create mongoDb wth replicaSet:

- docker pull mongo:4.0.4
- docker run -d -p 27017-27019:27017-27019 --name mongodb mongo:4.0.4

## Useful Link

- Link to access the Swagger documentation http://localhost:8080/swagger-ui.html#
- Import file Archive.postman_collection.json to your Postman for valid requests

# After installing Docker and Image, you will only run the command below

- sudo docker start mongodb

```
MongoDB configuration example link:
https://www.thepolyglotdeveloper.com/2019/01/getting-started-mongodb-docker-container-deployment/

MongoDB access tool:
https://robomongo.org/
```

## Run ArchiveApplication.java and type http://localhost:8080/ in your browser :)