## S2s Submission Types [/propdev/api/v1/s2s-submission-types/]

### Get S2s Submission Types by Key [GET /propdev/api/v1/s2s-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All S2s Submission Types [GET /propdev/api/v1/s2s-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s Submission Types with Filtering [GET /propdev/api/v1/s2s-submission-types/]
    
+ Parameters

    + code (optional) - Submission Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + sortId (optional) - Sort Id.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Submission Types [GET /propdev/api/v1/s2s-submission-types/]
	                                          
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
    
            {"columns":["code","description","sortId"],"primaryKey":"code"}
		
### Get Blueprint API specification for S2s Submission Types [GET /propdev/api/v1/s2s-submission-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Submission Types.md"
            transfer-encoding:chunked


### Update S2s Submission Types [PUT /propdev/api/v1/s2s-submission-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Submission Types [PUT /propdev/api/v1/s2s-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s Submission Types [POST /propdev/api/v1/s2s-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Submission Types [POST /propdev/api/v1/s2s-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s Submission Types by Key [DELETE /propdev/api/v1/s2s-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Submission Types [DELETE /propdev/api/v1/s2s-submission-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Submission Types with Matching [DELETE /propdev/api/v1/s2s-submission-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Submission Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + sortId (optional) - Sort Id.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
