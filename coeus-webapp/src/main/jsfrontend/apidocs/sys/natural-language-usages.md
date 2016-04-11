## Natural Language Usages [/research-sys/api/v1/natural-language-usages/]

### Get Natural Language Usages by Key [GET /research-sys/api/v1/natural-language-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Natural Language Usages [GET /research-sys/api/v1/natural-language-usages/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Natural Language Usages with Filtering [GET /research-sys/api/v1/natural-language-usages/]
    
+ Parameters

        + id
            + name
            + description
            + namespace
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Natural Language Usages [GET /research-sys/api/v1/natural-language-usages/]
	                                          
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
    
            {"columns":["id","name","description","namespace","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Natural Language Usages [GET /research-sys/api/v1/natural-language-usages/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Natural Language Usages.md"
            transfer-encoding:chunked


### Update Natural Language Usages [PUT /research-sys/api/v1/natural-language-usages/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Natural Language Usages [PUT /research-sys/api/v1/natural-language-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Natural Language Usages [POST /research-sys/api/v1/natural-language-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Natural Language Usages [POST /research-sys/api/v1/natural-language-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","namespace": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Natural Language Usages by Key [DELETE /research-sys/api/v1/natural-language-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Natural Language Usages [DELETE /research-sys/api/v1/natural-language-usages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Natural Language Usages with Matching [DELETE /research-sys/api/v1/natural-language-usages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + name
            + description
            + namespace
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
