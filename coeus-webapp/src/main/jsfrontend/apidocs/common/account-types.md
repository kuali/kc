## Account Types [/research-sys/api/v1/account-types/]

### Get Account Types by Key [GET /research-sys/api/v1/account-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Account Types [GET /research-sys/api/v1/account-types/]
	 
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

### Get All Account Types with Filtering [GET /research-sys/api/v1/account-types/]
    
+ Parameters

        + accountTypeCode
            + description

            
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
			
### Get Schema for Account Types [GET /research-sys/api/v1/account-types/]
	                                          
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
		
### Get Blueprint API specification for Account Types [GET /research-sys/api/v1/account-types/]
	 
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


### Update Account Types [PUT /research-sys/api/v1/account-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Account Types [PUT /research-sys/api/v1/account-types/]

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

### Insert Account Types [POST /research-sys/api/v1/account-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"accountTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Account Types [POST /research-sys/api/v1/account-types/]

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
            
### Delete Account Types by Key [DELETE /research-sys/api/v1/account-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Account Types [DELETE /research-sys/api/v1/account-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Account Types with Matching [DELETE /research-sys/api/v1/account-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + accountTypeCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
