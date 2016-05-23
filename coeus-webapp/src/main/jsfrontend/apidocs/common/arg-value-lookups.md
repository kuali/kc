## Arg Value Lookups [/research-common/api/v1/arg-value-lookups/]

### Get Arg Value Lookups by Key [GET /research-common/api/v1/arg-value-lookups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Arg Value Lookups [GET /research-common/api/v1/arg-value-lookups/]
	 
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

### Get All Arg Value Lookups with Filtering [GET /research-common/api/v1/arg-value-lookups/]
    
+ Parameters

    + id (optional) - Arg Value Lookup Id. Maximum length is 12.
    + argumentName (optional) - Argument Name. Maximum length is 30.
    + value (optional) - Value. Maximum length is 200.
    + description (optional) - Description. Maximum length is 200.

            
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
			
### Get Schema for Arg Value Lookups [GET /research-common/api/v1/arg-value-lookups/]
	                                          
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
    
            {"columns":["id","argumentName","value","description"],"primaryKey":"id"}
		
### Get Blueprint API specification for Arg Value Lookups [GET /research-common/api/v1/arg-value-lookups/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Arg Value Lookups.md"
            transfer-encoding:chunked
### Update Arg Value Lookups [PUT /research-common/api/v1/arg-value-lookups/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Arg Value Lookups [PUT /research-common/api/v1/arg-value-lookups/]

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
### Insert Arg Value Lookups [POST /research-common/api/v1/arg-value-lookups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","argumentName": "(val)","value": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Arg Value Lookups [POST /research-common/api/v1/arg-value-lookups/]

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
### Delete Arg Value Lookups by Key [DELETE /research-common/api/v1/arg-value-lookups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Arg Value Lookups [DELETE /research-common/api/v1/arg-value-lookups/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Arg Value Lookups with Matching [DELETE /research-common/api/v1/arg-value-lookups/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Arg Value Lookup Id. Maximum length is 12.
    + argumentName (optional) - Argument Name. Maximum length is 30.
    + value (optional) - Value. Maximum length is 200.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
