[![Build Status](https://travis-ci.org/AgentSir/ContactSystem.svg?branch=master)](https://travis-ci.org/AgentSir/ContactSystem)

# Project Title

The REST service for getting contacts from db.

## Additional info

The Spring Boot web application. 

# Production mode

### Prerequisites

What things you need to install the software and how to install them

```
- VirtualBox
- Vagrant
```

### Installing

Install VirtualBox 5.1.xx

```
https://www.virtualbox.org/wiki/Downloads
```

Install Vagrant 1.9.x

```
https://www.vagrantup.com/downloads.html
```

## Deployment

Open folder in CMD (Windows)/Terminal (UBUNTU/MAC OC) with cloned project from GitHub repository. Execute following: 'vagrant up'.

#### Available endpoint
http://localhost:8088/hello/contacts?nameFilter=<regexp\>   (GET)  -- to find contacts which do not match filter where <regexp\> is regular expression


# Development mode

### Prerequisites

What things you need to install

```
- Java 1.8 JDK
- Maven 3.x
- MYSQL 5.x Server and Client (user: root / password: root)
```

## Deployment
1) Build project with maven:

    Open command line CMD (Windows)/Terminal (UBUNTU/MAC OC) and go to the folder with cloned project from GitHub repository. 
    Run: 'mvn clean package'.
    
2) Restore/migrate a database data from a backup
    
    Go to the folder with cloned project from GitHub repository.
    The backup (dump.sql) data located there ../data/db/backup_migration/dumb.sql
    
    ```
        - open command line CMD (Windows)/Terminal (UBUNTU/MAC OC);
        
        - connect to the mysql 'mysql -u root -p' 
            (it will ask to the enter password the password must be 'root');
            
        - execute statement in the sql file: 'source <project dir>/data/db/backup_migrate/dump.sql;'
            where <project dir> is a path to the cloned project from GitHub repository
    ```
3) Run application:

    Open newly created folder "target" in CMD (Windows)/Terminal (UBUNTU/MAC OC) with cloned project from GitHub repository. 
    Run: 'java -jar contactsystem.jar'
    
#### Available endpoint
http://localhost:8088/hello/contacts?nameFilter=<regexp\>   (GET)  -- to find contacts which do not match filter where <regexp\> is regular expression


