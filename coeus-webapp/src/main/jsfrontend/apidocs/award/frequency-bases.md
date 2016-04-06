## Frequency Bases [/research-sys/api/v1/frequency-bases/]

### Get Frequency Bases by Key [GET /research-sys/api/v1/frequency-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Frequency Bases [GET /research-sys/api/v1/frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Frequency Bases with Filtering [GET /research-sys/api/v1/frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + frequencyBaseCode
            + description
            + regenerationTypeName
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Frequency Bases [GET /research-sys/api/v1/frequency-bases/]
	 
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
		
### Get Blueprint API specification for Frequency Bases [GET /research-sys/api/v1/frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Frequency Bases.md"
            transfer-encoding:chunked


### Update Frequency Bases [PUT /research-sys/api/v1/frequency-bases/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Frequency Bases [PUT /research-sys/api/v1/frequency-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Frequency Bases [POST /research-sys/api/v1/frequency-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Frequency Bases [POST /research-sys/api/v1/frequency-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Frequency Bases by Key [DELETE /research-sys/api/v1/frequency-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Frequency Bases [DELETE /research-sys/api/v1/frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Frequency Bases with Matching [DELETE /research-sys/api/v1/frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + frequencyBaseCode
            + description
            + regenerationTypeName
            + active


+ Response 204
