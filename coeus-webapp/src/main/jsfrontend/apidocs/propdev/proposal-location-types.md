## Proposal Location Types [/propdev/api/v1/proposal-location-types/]

### Get Proposal Location Types by Key [GET /propdev/api/v1/proposal-location-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}

### Get All Proposal Location Types [GET /propdev/api/v1/proposal-location-types/]
	 
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

### Get All Proposal Location Types with Filtering [GET /propdev/api/v1/proposal-location-types/]
    
+ Parameters

    + locationTypeCode (optional) - Proposal Location Type. Maximum length is 3.
    + locationTypeDesc (optional) - Description. Maximum length is 200.

            
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
			
### Get Schema for Proposal Location Types [GET /propdev/api/v1/proposal-location-types/]
	                                          
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
    
            {"columns":["locationTypeCode","locationTypeDesc"],"primaryKey":"locationTypeCode"}
		
### Get Blueprint API specification for Proposal Location Types [GET /propdev/api/v1/proposal-location-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Location Types.md"
            transfer-encoding:chunked
### Update Proposal Location Types [PUT /propdev/api/v1/proposal-location-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Location Types [PUT /propdev/api/v1/proposal-location-types/]

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
### Insert Proposal Location Types [POST /propdev/api/v1/proposal-location-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"locationTypeCode": "(val)","locationTypeDesc": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Location Types [POST /propdev/api/v1/proposal-location-types/]

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
### Delete Proposal Location Types by Key [DELETE /propdev/api/v1/proposal-location-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Location Types [DELETE /propdev/api/v1/proposal-location-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Location Types with Matching [DELETE /propdev/api/v1/proposal-location-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + locationTypeCode (optional) - Proposal Location Type. Maximum length is 3.
    + locationTypeDesc (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
