## Narrative Types [/propdev/api/v1/narrative-types/]

### Get Narrative Types by Key [GET /propdev/api/v1/narrative-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}

### Get All Narrative Types [GET /propdev/api/v1/narrative-types/]
	 
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

### Get All Narrative Types with Filtering [GET /propdev/api/v1/narrative-types/]
    
+ Parameters

    + code (optional) - Narrative Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + systemGenerated (optional) - System Generated. Maximum length is 1.
    + allowMultiple (optional) - Allow Multiple. Maximum length is 1.
    + narrativeTypeGroup (optional) - Narrative Type Group. Maximum length is 1.

            
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
			
### Get Schema for Narrative Types [GET /propdev/api/v1/narrative-types/]
	                                          
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
    
            {"columns":["code","description","systemGenerated","allowMultiple","narrativeTypeGroup"],"primaryKey":"code"}
		
### Get Blueprint API specification for Narrative Types [GET /propdev/api/v1/narrative-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Narrative Types.md"
            transfer-encoding:chunked


### Update Narrative Types [PUT /propdev/api/v1/narrative-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Narrative Types [PUT /propdev/api/v1/narrative-types/]

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

### Insert Narrative Types [POST /propdev/api/v1/narrative-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","systemGenerated": "(val)","allowMultiple": "(val)","narrativeTypeGroup": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Narrative Types [POST /propdev/api/v1/narrative-types/]

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
            
### Delete Narrative Types by Key [DELETE /propdev/api/v1/narrative-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narrative Types [DELETE /propdev/api/v1/narrative-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narrative Types with Matching [DELETE /propdev/api/v1/narrative-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Narrative Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + systemGenerated (optional) - System Generated. Maximum length is 1.
    + allowMultiple (optional) - Allow Multiple. Maximum length is 1.
    + narrativeTypeGroup (optional) - Narrative Type Group. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
