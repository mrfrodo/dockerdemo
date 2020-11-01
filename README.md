# Docker

## build
docker build -t dd .
## run
docker run -ti -p 8080:8080 dd
## test
curl -i localhost:8080/greeting -X GET



#### Playing around with postgres we need a local (a docker) database. 
Let us download v 13 from docker hub
docker run --name pgl -e POSTGRES_PASSWORD=mysecretpassword -d -p 5432:5432 postgres:13

##### This dependency demands a db up and running
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

## Log into the docker database
psql -h localhost -U postgres -d postgres

### Create user
CREATE USER user1 WITH PASSWORD 'pass1';

## Create database
CREATE DATABASE dockerdb;

## Grant
grant all privileges on database dockerdb to user1;
