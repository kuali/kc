## Formulated Types [/research-common/api/v1/formulated-types/]

### Get Formulated Types by Key [GET /research-common/api/v1/formulated-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Formulated Types [GET /research-common/api/v1/formulated-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Formulated Types with Filtering [GET /research-common/api/v1/formulated-types/]
    
+ Parameters

    + formulatedTypeCode (optional) - Formulated Type Code. Maximum length is 22.
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
              {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Formulated Types [GET /research-common/api/v1/formulated-types/]
	                                          
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
    
            {"columns":["formulatedTypeCode","description"],"primaryKey":"formulatedTypeCode"}
		
### Get Blueprint API specification for Formulated Types [GET /research-common/api/v1/formulated-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Formulated Types.md"
            transfer-encoding:chunked


### Update Formulated Types [PUT /research-common/api/v1/formulated-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Formulated Types [PUT /research-common/api/v1/formulated-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Formulated Types [POST /research-common/api/v1/formulated-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Formulated Types [POST /research-common/api/v1/formulated-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"formulatedTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Formulated Types by Key [DELETE /research-common/api/v1/formulated-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Formulated Types [DELETE /research-common/api/v1/formulated-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Formulated Types with Matching [DELETE /research-common/api/v1/formulated-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + formulatedTypeCode (optional) - Formulated Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
