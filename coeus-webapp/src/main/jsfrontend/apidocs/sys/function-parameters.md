## Function Parameters [/research-sys/api/v1/function-parameters/]

### Get Function Parameters by Key [GET /research-sys/api/v1/function-parameters/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Function Parameters [GET /research-sys/api/v1/function-parameters/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Function Parameters with Filtering [GET /research-sys/api/v1/function-parameters/]
    
+ Parameters

    + id (optional) - 
    + name (optional) - 
    + description (optional) - 
    + parameterType (optional) - 
    + sequenceNumber (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Function Parameters [GET /research-sys/api/v1/function-parameters/]
	                                          
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
    
            {"columns":["id","name","description","parameterType","sequenceNumber"],"primaryKey":"id"}
		
### Get Blueprint API specification for Function Parameters [GET /research-sys/api/v1/function-parameters/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Function Parameters.md"
            transfer-encoding:chunked
### Update Function Parameters [PUT /research-sys/api/v1/function-parameters/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Function Parameters [PUT /research-sys/api/v1/function-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Function Parameters [POST /research-sys/api/v1/function-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Function Parameters [POST /research-sys/api/v1/function-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
### Delete Function Parameters by Key [DELETE /research-sys/api/v1/function-parameters/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Function Parameters [DELETE /research-sys/api/v1/function-parameters/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Function Parameters with Matching [DELETE /research-sys/api/v1/function-parameters/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + name (optional) - 
    + description (optional) - 
    + parameterType (optional) - 
    + sequenceNumber (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
