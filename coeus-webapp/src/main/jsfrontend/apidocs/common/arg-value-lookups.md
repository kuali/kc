## Arg Value Lookups [/research-sys/api/v1/arg-value-lookups/]

### Get Arg Value Lookups by Key [GET /research-sys/api/v1/arg-value-lookups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Arg Value Lookups [GET /research-sys/api/v1/arg-value-lookups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Arg Value Lookups with Filtering [GET /research-sys/api/v1/arg-value-lookups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + argumentName
            + value
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Arg Value Lookups [GET /research-sys/api/v1/arg-value-lookups/]
	 
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
		
### Get Blueprint API specification for Arg Value Lookups [GET /research-sys/api/v1/arg-value-lookups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Arg Value Lookups.md"
            transfer-encoding:chunked


### Update Arg Value Lookups [PUT /research-sys/api/v1/arg-value-lookups/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Arg Value Lookups [PUT /research-sys/api/v1/arg-value-lookups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Arg Value Lookups [POST /research-sys/api/v1/arg-value-lookups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Arg Value Lookups [POST /research-sys/api/v1/arg-value-lookups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Arg Value Lookups by Key [DELETE /research-sys/api/v1/arg-value-lookups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Arg Value Lookups [DELETE /research-sys/api/v1/arg-value-lookups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Arg Value Lookups with Matching [DELETE /research-sys/api/v1/arg-value-lookups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + argumentName
            + value
            + description


+ Response 204
