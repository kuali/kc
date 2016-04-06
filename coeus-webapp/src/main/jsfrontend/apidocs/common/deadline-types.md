## Deadline Types [/research-sys/api/v1/deadline-types/]

### Get Deadline Types by Key [GET /research-sys/api/v1/deadline-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Deadline Types [GET /research-sys/api/v1/deadline-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Deadline Types with Filtering [GET /research-sys/api/v1/deadline-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + deadlineTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Deadline Types [GET /research-sys/api/v1/deadline-types/]
	 
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
		
### Get Blueprint API specification for Deadline Types [GET /research-sys/api/v1/deadline-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Deadline Types.md"
            transfer-encoding:chunked


### Update Deadline Types [PUT /research-sys/api/v1/deadline-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Deadline Types [PUT /research-sys/api/v1/deadline-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Deadline Types [POST /research-sys/api/v1/deadline-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Deadline Types [POST /research-sys/api/v1/deadline-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"deadlineTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Deadline Types by Key [DELETE /research-sys/api/v1/deadline-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Deadline Types [DELETE /research-sys/api/v1/deadline-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Deadline Types with Matching [DELETE /research-sys/api/v1/deadline-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + deadlineTypeCode
            + description


+ Response 204
