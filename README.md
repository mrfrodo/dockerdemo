# Docker

## build
docker build -t dd .
## run
docker run -ti -p 8080:8080 dd
## test
curl -i localhost:8080/greeting -X GET
