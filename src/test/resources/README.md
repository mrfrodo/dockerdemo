### customer tests

curl -i -X POST -L localhost:8080/c \
  -H "Content-Type: application/json" \
  --data-binary "@src/test/resources/createCustomer.json"
  
curl -i -X POST -L localhost:8080/c/m \
  -H "Content-Type: application/json" \
  --data-binary "@src/test/resources/createCustomers.json"
  
curl -i -X POST -L localhost:8080/c \
  -H "Content-Type: application/json" \
  --data-binary "@src/test/resources/updateCustomer.json"
  
curl -i -X GET -L localhost:8080/c?cid=frodo_inc 

curl -i -X GET -L localhost:8080/c/all
 
curl -i -X DELETE -L localhost:8080/c?cid=frodo_inc 

curl -i -X DELETE -L localhost:8080/c/one?cid=frodo_inc
curl -i -X DELETE -L localhost:8080/c

### poem tests
curl -i -X POST -L localhost:8080/p \
  -H "Content-Type: application/json" \
  --data-binary "@src/test/resources/poem.json" 
  
curl -i -X POST -L localhost:8080/p \
  -H "Content-Type: application/json" \
  --data-binary "@src/test/resources/illegalpoem.json" 

 
   
  
  
