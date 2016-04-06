## Narrative Types [/research-sys/api/v1/narrative-types/]

### Get Narrative Types by Key [GET /research-sys/api/v1/narrative-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}

### Get All Narrative Types [GET /research-sys/api/v1/narrative-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
            ]

### Get All Narrative Types with Filtering [GET /research-sys/api/v1/narrative-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + code
            + description
            + systemGenerated
            + allowMultiple
            + narrativeTypeGroup
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Narrative Types [GET /research-sys/api/v1/narrative-types/]
	 
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
		
### Get Blueprint API specification for Narrative Types [GET /research-sys/api/v1/narrative-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Narrative Types.md"
            transfer-encoding:chunked


### Update Narrative Types [PUT /research-sys/api/v1/narrative-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Narrative Types [PUT /research-sys/api/v1/narrative-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Narrative Types [POST /research-sys/api/v1/narrative-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Narrative Types [POST /research-sys/api/v1/narrative-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Narrative Types by Key [DELETE /research-sys/api/v1/narrative-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narrative Types [DELETE /research-sys/api/v1/narrative-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Narrative Types with Matching [DELETE /research-sys/api/v1/narrative-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + code
            + description
            + systemGenerated
            + allowMultiple
            + narrativeTypeGroup


+ Response 204
