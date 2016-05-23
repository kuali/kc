## Rate Classes [/research-common/api/v1/rate-classes/]

### Get Rate Classes by Key [GET /research-common/api/v1/rate-classes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Rate Classes [GET /research-common/api/v1/rate-classes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rate Classes with Filtering [GET /research-common/api/v1/rate-classes/]
    
+ Parameters

    + code (optional) - Rate Class Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + rateClassTypeCode (optional) - Rate Class Type. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rate Classes [GET /research-common/api/v1/rate-classes/]
	                                          
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
    
            {"columns":["code","description","rateClassTypeCode"],"primaryKey":"code"}
		
### Get Blueprint API specification for Rate Classes [GET /research-common/api/v1/rate-classes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rate Classes.md"
            transfer-encoding:chunked
### Update Rate Classes [PUT /research-common/api/v1/rate-classes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rate Classes [PUT /research-common/api/v1/rate-classes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Rate Classes [POST /research-common/api/v1/rate-classes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rate Classes [POST /research-common/api/v1/rate-classes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","rateClassTypeCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Rate Classes by Key [DELETE /research-common/api/v1/rate-classes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Classes [DELETE /research-common/api/v1/rate-classes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Classes with Matching [DELETE /research-common/api/v1/rate-classes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Rate Class Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + rateClassTypeCode (optional) - Rate Class Type. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
