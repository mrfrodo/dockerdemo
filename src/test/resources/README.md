curl -i -X POST -L localhost:8080/c \
  -H "Content-Type: application/json" \
  --data-binary "@src/test/resources/createCustomer.json"
  
  
 curl -i -X GET -L localhost:8080/c?cid=frodo_inc 
  
  
