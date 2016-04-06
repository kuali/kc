## User Channel Subscriptions [/research-sys/api/v1/user-channel-subscriptions/]

### Get User Channel Subscriptions by Key [GET /research-sys/api/v1/user-channel-subscriptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","userId": "(val)","_primaryKey": "(val)"}

### Get All User Channel Subscriptions [GET /research-sys/api/v1/user-channel-subscriptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","userId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","userId": "(val)","_primaryKey": "(val)"}
            ]

### Get All User Channel Subscriptions with Filtering [GET /research-sys/api/v1/user-channel-subscriptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + userId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","userId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","userId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for User Channel Subscriptions [GET /research-sys/api/v1/user-channel-subscriptions/]
	 
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
		
### Get Blueprint API specification for User Channel Subscriptions [GET /research-sys/api/v1/user-channel-subscriptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="User Channel Subscriptions.md"
            transfer-encoding:chunked


### Update User Channel Subscriptions [PUT /research-sys/api/v1/user-channel-subscriptions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","userId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple User Channel Subscriptions [PUT /research-sys/api/v1/user-channel-subscriptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","userId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","userId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert User Channel Subscriptions [POST /research-sys/api/v1/user-channel-subscriptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","userId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","userId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple User Channel Subscriptions [POST /research-sys/api/v1/user-channel-subscriptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","userId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","userId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","userId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","userId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete User Channel Subscriptions by Key [DELETE /research-sys/api/v1/user-channel-subscriptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All User Channel Subscriptions [DELETE /research-sys/api/v1/user-channel-subscriptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All User Channel Subscriptions with Matching [DELETE /research-sys/api/v1/user-channel-subscriptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + userId


+ Response 204
