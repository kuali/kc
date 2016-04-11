## Term Resolver Parameter Specifications [/research-sys/api/v1/term-resolver-parameter-specifications/]

### Get Term Resolver Parameter Specifications by Key [GET /research-sys/api/v1/term-resolver-parameter-specifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}

### Get All Term Resolver Parameter Specifications [GET /research-sys/api/v1/term-resolver-parameter-specifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]

### Get All Term Resolver Parameter Specifications with Filtering [GET /research-sys/api/v1/term-resolver-parameter-specifications/]
    
+ Parameters

        + id
            + name

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Term Resolver Parameter Specifications [GET /research-sys/api/v1/term-resolver-parameter-specifications/]
	                                          
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
    
            {"columns":["id","name"],"primaryKey":"id"}
		
### Get Blueprint API specification for Term Resolver Parameter Specifications [GET /research-sys/api/v1/term-resolver-parameter-specifications/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Term Resolver Parameter Specifications.md"
            transfer-encoding:chunked


### Update Term Resolver Parameter Specifications [PUT /research-sys/api/v1/term-resolver-parameter-specifications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Term Resolver Parameter Specifications [PUT /research-sys/api/v1/term-resolver-parameter-specifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Term Resolver Parameter Specifications [POST /research-sys/api/v1/term-resolver-parameter-specifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Term Resolver Parameter Specifications [POST /research-sys/api/v1/term-resolver-parameter-specifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Term Resolver Parameter Specifications by Key [DELETE /research-sys/api/v1/term-resolver-parameter-specifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Term Resolver Parameter Specifications [DELETE /research-sys/api/v1/term-resolver-parameter-specifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Term Resolver Parameter Specifications with Matching [DELETE /research-sys/api/v1/term-resolver-parameter-specifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + name

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
