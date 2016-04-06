## Rate Types [/research-sys/api/v1/rate-types/]

### Get Rate Types by Key [GET /research-sys/api/v1/rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Rate Types [GET /research-sys/api/v1/rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rate Types with Filtering [GET /research-sys/api/v1/rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + rateClassCode
            + rateTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rate Types [GET /research-sys/api/v1/rate-types/]
	 
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
		
### Get Blueprint API specification for Rate Types [GET /research-sys/api/v1/rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rate Types.md"
            transfer-encoding:chunked


### Update Rate Types [PUT /research-sys/api/v1/rate-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rate Types [PUT /research-sys/api/v1/rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rate Types [POST /research-sys/api/v1/rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rate Types [POST /research-sys/api/v1/rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rate Types by Key [DELETE /research-sys/api/v1/rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Types [DELETE /research-sys/api/v1/rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Rate Types with Matching [DELETE /research-sys/api/v1/rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + rateClassCode
            + rateTypeCode
            + description


+ Response 204
