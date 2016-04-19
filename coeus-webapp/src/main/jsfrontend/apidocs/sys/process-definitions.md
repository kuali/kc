## Process Definitions [/research-sys/api/v1/process-definitions/]

### Get Process Definitions by Key [GET /research-sys/api/v1/process-definitions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Process Definitions [GET /research-sys/api/v1/process-definitions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All Process Definitions with Filtering [GET /research-sys/api/v1/process-definitions/]
    
+ Parameters

    + processId (optional) - 
    + name (optional) - 
    + initial (optional) - 
    + lockVerNbr (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Process Definitions [GET /research-sys/api/v1/process-definitions/]
	                                          
+ Parameters

      + _schema (required) - will instruct the endpoint to return a schema data structure for the resource
      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"columns":["processId","name","initial","lockVerNbr"],"primaryKey":"processId"}
		
### Get Blueprint API specification for Process Definitions [GET /research-sys/api/v1/process-definitions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Process Definitions.md"
            transfer-encoding:chunked


### Update Process Definitions [PUT /research-sys/api/v1/process-definitions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Process Definitions [PUT /research-sys/api/v1/process-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Process Definitions [POST /research-sys/api/v1/process-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Process Definitions [POST /research-sys/api/v1/process-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"processId": "(val)","name": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Process Definitions by Key [DELETE /research-sys/api/v1/process-definitions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Process Definitions [DELETE /research-sys/api/v1/process-definitions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Process Definitions with Matching [DELETE /research-sys/api/v1/process-definitions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + processId (optional) - 
    + name (optional) - 
    + initial (optional) - 
    + lockVerNbr (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
