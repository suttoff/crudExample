# Archive

# About 
Online Championships Management API Project

    Contains the apis below
        Player:
            - Get player
            - Add player
            - Update player
            - Delete player
            - Archive to create player
            
        Team:
            - Get Team
            - Add Team

## Create mongoDB environment    
    
Run following commands to create mongoDb wth replicaSet: 


- docker pull mongo:4.0.4
- docker run -d -p 27017-27019:27017-27019 --name mongodb mongo:4.0.4

## Useful Link
Import file Archive.postman_collecton.json to your Postman for valid requests
```
MongoDB configuration example link:
https://www.thepolyglotdeveloper.com/2019/01/getting-started-mongodb-docker-container-deployment/

MongoDB access tool:
https://robomongo.org/
```



## Run Aplication.java and type http://localhost:8080/ in your browser :)