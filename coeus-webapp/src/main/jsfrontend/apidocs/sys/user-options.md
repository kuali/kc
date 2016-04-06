## User Options [/research-sys/api/v1/user-options/]

### Get User Options by Key [GET /research-sys/api/v1/user-options/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All User Options [GET /research-sys/api/v1/user-options/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All User Options with Filtering [GET /research-sys/api/v1/user-options/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + workflowId
            + optionId
            + optionVal
            + lockVerNbr
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for User Options [GET /research-sys/api/v1/user-options/]
	 
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
		
### Get Blueprint API specification for User Options [GET /research-sys/api/v1/user-options/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="User Options.md"
            transfer-encoding:chunked


### Update User Options [PUT /research-sys/api/v1/user-options/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple User Options [PUT /research-sys/api/v1/user-options/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert User Options [POST /research-sys/api/v1/user-options/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple User Options [POST /research-sys/api/v1/user-options/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"workflowId": "(val)","optionId": "(val)","optionVal": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete User Options by Key [DELETE /research-sys/api/v1/user-options/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All User Options [DELETE /research-sys/api/v1/user-options/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All User Options with Matching [DELETE /research-sys/api/v1/user-options/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + workflowId
            + optionId
            + optionVal
            + lockVerNbr


+ Response 204
