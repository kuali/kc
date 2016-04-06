## Message Deliveries [/research-sys/api/v1/message-deliveries/]

### Get Message Deliveries by Key [GET /research-sys/api/v1/message-deliveries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"}

### Get All Message Deliveries [GET /research-sys/api/v1/message-deliveries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Message Deliveries with Filtering [GET /research-sys/api/v1/message-deliveries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + delivererTypeName
            + delivererSystemId
            + deliveryStatus
            + processCount
            + lockVerNbr
            + lockedDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Message Deliveries [GET /research-sys/api/v1/message-deliveries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Message Deliveries [GET /research-sys/api/v1/message-deliveries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Message Deliveries.md"
            transfer-encoding:chunked


### Update Message Deliveries [PUT /research-sys/api/v1/message-deliveries/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Message Deliveries [PUT /research-sys/api/v1/message-deliveries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Message Deliveries [POST /research-sys/api/v1/message-deliveries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Message Deliveries [POST /research-sys/api/v1/message-deliveries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","delivererTypeName": "(val)","delivererSystemId": "(val)","deliveryStatus": "(val)","processCount": "(val)","lockVerNbr": "(val)","lockedDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Message Deliveries by Key [DELETE /research-sys/api/v1/message-deliveries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Message Deliveries [DELETE /research-sys/api/v1/message-deliveries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Message Deliveries with Matching [DELETE /research-sys/api/v1/message-deliveries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + delivererTypeName
            + delivererSystemId
            + deliveryStatus
            + processCount
            + lockVerNbr
            + lockedDate


+ Response 204
