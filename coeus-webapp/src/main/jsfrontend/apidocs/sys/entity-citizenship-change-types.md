## Entity Citizenship Change Types [/research-sys/api/v1/entity-citizenship-change-types/]

### Get Entity Citizenship Change Types by Key [GET /research-sys/api/v1/entity-citizenship-change-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Citizenship Change Types [GET /research-sys/api/v1/entity-citizenship-change-types/]
	 
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

### Get All Entity Citizenship Change Types with Filtering [GET /research-sys/api/v1/entity-citizenship-change-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + name
            + active
            + code
            + sortCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Citizenship Change Types [GET /research-sys/api/v1/entity-citizenship-change-types/]
	 
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
		
### Get Blueprint API specification for Entity Citizenship Change Types [GET /research-sys/api/v1/entity-citizenship-change-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Citizenship Change Types.md"
            transfer-encoding:chunked


### Update Entity Citizenship Change Types [PUT /research-sys/api/v1/entity-citizenship-change-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Citizenship Change Types [PUT /research-sys/api/v1/entity-citizenship-change-types/]

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

### Insert Entity Citizenship Change Types [POST /research-sys/api/v1/entity-citizenship-change-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Citizenship Change Types [POST /research-sys/api/v1/entity-citizenship-change-types/]

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
            
### Delete Entity Citizenship Change Types by Key [DELETE /research-sys/api/v1/entity-citizenship-change-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Citizenship Change Types [DELETE /research-sys/api/v1/entity-citizenship-change-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Entity Citizenship Change Types with Matching [DELETE /research-sys/api/v1/entity-citizenship-change-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + name
            + active
            + code
            + sortCode


+ Response 204
