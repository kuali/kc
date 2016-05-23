## Entity Ethnicity Race Types [/research-sys/api/v1/entity-ethnicity-race-types/]

### Get Entity Ethnicity Race Types by Key [GET /research-sys/api/v1/entity-ethnicity-race-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Ethnicity Race Types [GET /research-sys/api/v1/entity-ethnicity-race-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Ethnicity Race Types with Filtering [GET /research-sys/api/v1/entity-ethnicity-race-types/]
    
+ Parameters

    + name (optional) - 
    + active (optional) - 
    + code (optional) - 
    + sortCode (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Ethnicity Race Types [GET /research-sys/api/v1/entity-ethnicity-race-types/]
	                                          
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
    
            {"columns":["name","active","code","sortCode"],"primaryKey":"code"}
		
### Get Blueprint API specification for Entity Ethnicity Race Types [GET /research-sys/api/v1/entity-ethnicity-race-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Ethnicity Race Types.md"
            transfer-encoding:chunked
### Update Entity Ethnicity Race Types [PUT /research-sys/api/v1/entity-ethnicity-race-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Ethnicity Race Types [PUT /research-sys/api/v1/entity-ethnicity-race-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Entity Ethnicity Race Types [POST /research-sys/api/v1/entity-ethnicity-race-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Ethnicity Race Types [POST /research-sys/api/v1/entity-ethnicity-race-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Entity Ethnicity Race Types by Key [DELETE /research-sys/api/v1/entity-ethnicity-race-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Ethnicity Race Types [DELETE /research-sys/api/v1/entity-ethnicity-race-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Ethnicity Race Types with Matching [DELETE /research-sys/api/v1/entity-ethnicity-race-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + name (optional) - 
    + active (optional) - 
    + code (optional) - 
    + sortCode (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
