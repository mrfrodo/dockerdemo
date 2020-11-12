# Docker

## build
docker build -t dd .
## run
docker run -ti -p 8080:8080 dd
## test
curl -i localhost:8080/greeting -X GET



#### Playing around with postgres we need a local (a docker) database. 
Download v 13 from docker hub

docker run --name pgl -e POSTGRES_PASSWORD=mysecretpassword -d -p 5432:5432 postgres:13

##### This dependency demands a db up and running
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

### Log into the docker database (first time)
##### Remember POSTGRES_PASSWORD=mysecretpassword
psql -h localhost -p 5432 -U postgres -d postgres

#### Create user
CREATE USER user1 WITH PASSWORD 'pass1';

#### Create database
CREATE DATABASE dockerdb;

#### Grant
grant all privileges on database dockerdb to user1;

### Log into the docker database (second time)
psql -h localhost -p 5432 -U user1 -d dockerdb

#### Check the new database (dockerdb) is up and running
`dockerdb=> \l
                                 List of databases
   Name    |  Owner   | Encoding |  Collate   |   Ctype    |   Access privileges   
-----------+----------+----------+------------+------------+-----------------------
 dockerdb  | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =Tc/postgres         +
           |          |          |            |            | postgres=CTc/postgres+
           |          |          |            |            | user1=CTc/postgres
 postgres  | postgres | UTF8     | en_US.utf8 | en_US.utf8 | 
 template0 | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =c/postgres          +
           |          |          |            |            | postgres=CTc/postgres
 template1 | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =c/postgres          +
           |          |          |            |            | postgres=CTc/postgres
(4 rows)`


-----------------------------------------------------------------------

### When all is set up
docker start pgl


##### actuator
curl localhost:8080/actuator/health
