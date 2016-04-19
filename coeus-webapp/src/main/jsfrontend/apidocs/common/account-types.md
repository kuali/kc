## Account Types [/research-common/api/v1/account-types/]

### Get Account Types by Key [GET /research-common/api/v1/account-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Account Types [GET /research-common/api/v1/account-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Account Types with Filtering [GET /research-common/api/v1/account-types/]
    
+ Parameters

    + accountTypeCode (optional) - Account Type Code. Maximum length is 22.
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
              {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Account Types [GET /research-common/api/v1/account-types/]
	                                          
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
    
            {"columns":["accountTypeCode","description"],"primaryKey":"accountTypeCode"}
		
### Get Blueprint API specification for Account Types [GET /research-common/api/v1/account-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Account Types.md"
            transfer-encoding:chunked


### Update Account Types [PUT /research-common/api/v1/account-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Account Types [PUT /research-common/api/v1/account-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Account Types [POST /research-common/api/v1/account-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Account Types [POST /research-common/api/v1/account-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Account Types by Key [DELETE /research-common/api/v1/account-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Account Types [DELETE /research-common/api/v1/account-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Account Types with Matching [DELETE /research-common/api/v1/account-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + accountTypeCode (optional) - Account Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
