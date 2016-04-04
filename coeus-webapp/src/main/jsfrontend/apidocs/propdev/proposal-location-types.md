## Proposal Location Types [/research-sys/api/v1/proposal-location-types/]

### Get Proposal Location Types by Key [GET /research-sys/api/v1/proposal-location-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}

### Get All Proposal Location Types [GET /research-sys/api/v1/proposal-location-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"},
              {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Location Types with Filtering [GET /research-sys/api/v1/proposal-location-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + locationTypeCode
            + locationTypeDesc
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"},
              {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Location Types [GET /research-sys/api/v1/proposal-location-types/]
	 
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
		
### Get Blueprint API specification for Proposal Location Types [GET /research-sys/api/v1/proposal-location-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Location Types.md"
            transfer-encoding:chunked


### Update Proposal Location Types [PUT /research-sys/api/v1/proposal-location-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Location Types [PUT /research-sys/api/v1/proposal-location-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"},
              {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Location Types [POST /research-sys/api/v1/proposal-location-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Location Types [POST /research-sys/api/v1/proposal-location-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"},
              {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"},
              {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Location Types by Key [DELETE /research-sys/api/v1/proposal-location-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Location Types [DELETE /research-sys/api/v1/proposal-location-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Location Types with Matching [DELETE /research-sys/api/v1/proposal-location-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + locationTypeCode
            + locationTypeDesc


+ Response 204
