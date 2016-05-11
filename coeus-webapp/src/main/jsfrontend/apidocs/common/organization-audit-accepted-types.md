## Organization Audit Accepted Types [/research-common/api/v1/organization-audit-accepted-types/]

### Get Organization Audit Accepted Types by Key [GET /research-common/api/v1/organization-audit-accepted-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Organization Audit Accepted Types [GET /research-common/api/v1/organization-audit-accepted-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Organization Audit Accepted Types with Filtering [GET /research-common/api/v1/organization-audit-accepted-types/]
    
+ Parameters

    + code (optional) - Organization Audit Accepted Type Code. Maximum length is 3.
    + active (optional) - Is Active. Maximum length is 1.
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
              {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Organization Audit Accepted Types [GET /research-common/api/v1/organization-audit-accepted-types/]
	                                          
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
    
            {"columns":["code","active","description"],"primaryKey":"code"}
		
### Get Blueprint API specification for Organization Audit Accepted Types [GET /research-common/api/v1/organization-audit-accepted-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Organization Audit Accepted Types.md"
            transfer-encoding:chunked


### Update Organization Audit Accepted Types [PUT /research-common/api/v1/organization-audit-accepted-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Organization Audit Accepted Types [PUT /research-common/api/v1/organization-audit-accepted-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Organization Audit Accepted Types [POST /research-common/api/v1/organization-audit-accepted-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Organization Audit Accepted Types [POST /research-common/api/v1/organization-audit-accepted-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Organization Audit Accepted Types by Key [DELETE /research-common/api/v1/organization-audit-accepted-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Audit Accepted Types [DELETE /research-common/api/v1/organization-audit-accepted-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Audit Accepted Types with Matching [DELETE /research-common/api/v1/organization-audit-accepted-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Organization Audit Accepted Type Code. Maximum length is 3.
    + active (optional) - Is Active. Maximum length is 1.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
