## Science Keywords [/research-common/api/v1/science-keywords/]

### Get Science Keywords by Key [GET /research-common/api/v1/science-keywords/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Science Keywords [GET /research-common/api/v1/science-keywords/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Science Keywords with Filtering [GET /research-common/api/v1/science-keywords/]
    
+ Parameters

    + code (optional) - Science Keyword Code. Maximum length is 15.
    + description (optional) - The actual keyword(s) specific to the proposal that can be used in database lookups and reports. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Science Keywords [GET /research-common/api/v1/science-keywords/]
	                                          
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
    
            {"columns":["code","description"],"primaryKey":"code"}
		
### Get Blueprint API specification for Science Keywords [GET /research-common/api/v1/science-keywords/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Science Keywords.md"
            transfer-encoding:chunked
### Update Science Keywords [PUT /research-common/api/v1/science-keywords/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Science Keywords [PUT /research-common/api/v1/science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Science Keywords [POST /research-common/api/v1/science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Science Keywords [POST /research-common/api/v1/science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Science Keywords by Key [DELETE /research-common/api/v1/science-keywords/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Science Keywords [DELETE /research-common/api/v1/science-keywords/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Science Keywords with Matching [DELETE /research-common/api/v1/science-keywords/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Science Keyword Code. Maximum length is 15.
    + description (optional) - The actual keyword(s) specific to the proposal that can be used in database lookups and reports. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
