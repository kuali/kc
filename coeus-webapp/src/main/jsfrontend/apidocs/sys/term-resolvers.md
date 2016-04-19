## Term Resolvers [/research-sys/api/v1/term-resolvers/]

### Get Term Resolvers by Key [GET /research-sys/api/v1/term-resolvers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Term Resolvers [GET /research-sys/api/v1/term-resolvers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Term Resolvers with Filtering [GET /research-sys/api/v1/term-resolvers/]
    
+ Parameters

    + id (optional) - 
    + namespace (optional) - 
    + name (optional) - 
    + typeId (optional) - 
    + outputId (optional) - 
    + active (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Term Resolvers [GET /research-sys/api/v1/term-resolvers/]
	                                          
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
    
            {"columns":["id","namespace","name","typeId","outputId","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Term Resolvers [GET /research-sys/api/v1/term-resolvers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Term Resolvers.md"
            transfer-encoding:chunked


### Update Term Resolvers [PUT /research-sys/api/v1/term-resolvers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Term Resolvers [PUT /research-sys/api/v1/term-resolvers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Term Resolvers [POST /research-sys/api/v1/term-resolvers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Term Resolvers [POST /research-sys/api/v1/term-resolvers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","typeId": "(val)","outputId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Term Resolvers by Key [DELETE /research-sys/api/v1/term-resolvers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Term Resolvers [DELETE /research-sys/api/v1/term-resolvers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Term Resolvers with Matching [DELETE /research-sys/api/v1/term-resolvers/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + namespace (optional) - 
    + name (optional) - 
    + typeId (optional) - 
    + outputId (optional) - 
    + active (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
