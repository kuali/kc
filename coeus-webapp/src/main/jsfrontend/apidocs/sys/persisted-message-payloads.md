## Persisted Message Payloads [/research-sys/api/v1/persisted-message-payloads/]

### Get Persisted Message Payloads by Key [GET /research-sys/api/v1/persisted-message-payloads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"}

### Get All Persisted Message Payloads [GET /research-sys/api/v1/persisted-message-payloads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"},
              {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"}
            ]

### Get All Persisted Message Payloads with Filtering [GET /research-sys/api/v1/persisted-message-payloads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + routeQueueId
            + payload
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"},
              {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Persisted Message Payloads [GET /research-sys/api/v1/persisted-message-payloads/]
	 
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
		
### Get Blueprint API specification for Persisted Message Payloads [GET /research-sys/api/v1/persisted-message-payloads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Persisted Message Payloads.md"
            transfer-encoding:chunked


### Update Persisted Message Payloads [PUT /research-sys/api/v1/persisted-message-payloads/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Persisted Message Payloads [PUT /research-sys/api/v1/persisted-message-payloads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"},
              {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Persisted Message Payloads [POST /research-sys/api/v1/persisted-message-payloads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Persisted Message Payloads [POST /research-sys/api/v1/persisted-message-payloads/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"},
              {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"},
              {"routeQueueId": "(val)","payload": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Persisted Message Payloads by Key [DELETE /research-sys/api/v1/persisted-message-payloads/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Persisted Message Payloads [DELETE /research-sys/api/v1/persisted-message-payloads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Persisted Message Payloads with Matching [DELETE /research-sys/api/v1/persisted-message-payloads/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + routeQueueId
            + payload


+ Response 204
