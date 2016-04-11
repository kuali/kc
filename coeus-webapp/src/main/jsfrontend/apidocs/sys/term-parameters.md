## Term Parameters [/research-sys/api/v1/term-parameters/]

### Get Term Parameters by Key [GET /research-sys/api/v1/term-parameters/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Term Parameters [GET /research-sys/api/v1/term-parameters/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Term Parameters with Filtering [GET /research-sys/api/v1/term-parameters/]
    
+ Parameters

        + id
            + name
            + value

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Term Parameters [GET /research-sys/api/v1/term-parameters/]
	                                          
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
    
            {"columns":["id","name","value"],"primaryKey":"id"}
		
### Get Blueprint API specification for Term Parameters [GET /research-sys/api/v1/term-parameters/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Term Parameters.md"
            transfer-encoding:chunked


### Update Term Parameters [PUT /research-sys/api/v1/term-parameters/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Term Parameters [PUT /research-sys/api/v1/term-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Term Parameters [POST /research-sys/api/v1/term-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Term Parameters [POST /research-sys/api/v1/term-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Term Parameters by Key [DELETE /research-sys/api/v1/term-parameters/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Term Parameters [DELETE /research-sys/api/v1/term-parameters/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Term Parameters with Matching [DELETE /research-sys/api/v1/term-parameters/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + name
            + value

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
