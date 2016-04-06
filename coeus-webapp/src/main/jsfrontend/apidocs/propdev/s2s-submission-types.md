## S2s Submission Types [/research-sys/api/v1/s2s-submission-types/]

### Get S2s Submission Types by Key [GET /research-sys/api/v1/s2s-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All S2s Submission Types [GET /research-sys/api/v1/s2s-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s Submission Types with Filtering [GET /research-sys/api/v1/s2s-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + code
            + description
            + sortId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Submission Types [GET /research-sys/api/v1/s2s-submission-types/]
	 
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
		
### Get Blueprint API specification for S2s Submission Types [GET /research-sys/api/v1/s2s-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Submission Types.md"
            transfer-encoding:chunked


### Update S2s Submission Types [PUT /research-sys/api/v1/s2s-submission-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Submission Types [PUT /research-sys/api/v1/s2s-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s Submission Types [POST /research-sys/api/v1/s2s-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Submission Types [POST /research-sys/api/v1/s2s-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s Submission Types by Key [DELETE /research-sys/api/v1/s2s-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Submission Types [DELETE /research-sys/api/v1/s2s-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All S2s Submission Types with Matching [DELETE /research-sys/api/v1/s2s-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + code
            + description
            + sortId


+ Response 204
