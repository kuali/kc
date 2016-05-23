## Correspondent Types [/irb/api/v1/correspondent-types/]

### Get Correspondent Types by Key [GET /irb/api/v1/correspondent-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}

### Get All Correspondent Types [GET /irb/api/v1/correspondent-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]

### Get All Correspondent Types with Filtering [GET /irb/api/v1/correspondent-types/]
    
+ Parameters

    + correspondentTypeCode (optional) - Correspondent Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + qualifier (optional) - Qualifier. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Correspondent Types [GET /irb/api/v1/correspondent-types/]
	                                          
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
    
            {"columns":["correspondentTypeCode","description","qualifier"],"primaryKey":"correspondentTypeCode"}
		
### Get Blueprint API specification for Correspondent Types [GET /irb/api/v1/correspondent-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Correspondent Types.md"
            transfer-encoding:chunked
### Update Correspondent Types [PUT /irb/api/v1/correspondent-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Correspondent Types [PUT /irb/api/v1/correspondent-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Correspondent Types [POST /irb/api/v1/correspondent-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Correspondent Types [POST /irb/api/v1/correspondent-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"correspondentTypeCode": "(val)","description": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
### Delete Correspondent Types by Key [DELETE /irb/api/v1/correspondent-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Correspondent Types [DELETE /irb/api/v1/correspondent-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Correspondent Types with Matching [DELETE /irb/api/v1/correspondent-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + correspondentTypeCode (optional) - Correspondent Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + qualifier (optional) - Qualifier. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
