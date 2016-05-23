## Iacuc Species Count Types [/iacuc/api/v1/iacuc-species-count-types/]

### Get Iacuc Species Count Types by Key [GET /iacuc/api/v1/iacuc-species-count-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Species Count Types [GET /iacuc/api/v1/iacuc-species-count-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Species Count Types with Filtering [GET /iacuc/api/v1/iacuc-species-count-types/]
    
+ Parameters

    + speciesCountCode (optional) - Species Count Code. Maximum length is 3.
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
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Species Count Types [GET /iacuc/api/v1/iacuc-species-count-types/]
	                                          
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
    
            {"columns":["speciesCountCode","description"],"primaryKey":"speciesCountCode"}
		
### Get Blueprint API specification for Iacuc Species Count Types [GET /iacuc/api/v1/iacuc-species-count-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Species Count Types.md"
            transfer-encoding:chunked
### Update Iacuc Species Count Types [PUT /iacuc/api/v1/iacuc-species-count-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Species Count Types [PUT /iacuc/api/v1/iacuc-species-count-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Species Count Types [POST /iacuc/api/v1/iacuc-species-count-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Species Count Types [POST /iacuc/api/v1/iacuc-species-count-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Species Count Types by Key [DELETE /iacuc/api/v1/iacuc-species-count-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Species Count Types [DELETE /iacuc/api/v1/iacuc-species-count-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Species Count Types with Matching [DELETE /iacuc/api/v1/iacuc-species-count-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + speciesCountCode (optional) - Species Count Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
