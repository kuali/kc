## Delegate Types [/research-sys/api/v1/delegate-types/]

### Get Delegate Types by Key [GET /research-sys/api/v1/delegate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Delegate Types [GET /research-sys/api/v1/delegate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Delegate Types with Filtering [GET /research-sys/api/v1/delegate-types/]
    
+ Parameters

    + delegationId (optional) - Delegation. Maximum length is 10.
    + roleId (optional) - Role. Maximum length is 10.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + kimTypeId (optional) - Delegation Type. Maximum length is 40.
    + delegationTypeCode (optional) - Delegation Type Code. Maximum length is 40.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Delegate Types [GET /research-sys/api/v1/delegate-types/]
	                                          
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
    
            {"columns":["delegationId","roleId","active","kimTypeId","delegationTypeCode"],"primaryKey":"delegationId"}
		
### Get Blueprint API specification for Delegate Types [GET /research-sys/api/v1/delegate-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Delegate Types.md"
            transfer-encoding:chunked


### Update Delegate Types [PUT /research-sys/api/v1/delegate-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Delegate Types [PUT /research-sys/api/v1/delegate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Delegate Types [POST /research-sys/api/v1/delegate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Delegate Types [POST /research-sys/api/v1/delegate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Delegate Types by Key [DELETE /research-sys/api/v1/delegate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Delegate Types [DELETE /research-sys/api/v1/delegate-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Delegate Types with Matching [DELETE /research-sys/api/v1/delegate-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + delegationId (optional) - Delegation. Maximum length is 10.
    + roleId (optional) - Role. Maximum length is 10.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + kimTypeId (optional) - Delegation Type. Maximum length is 40.
    + delegationTypeCode (optional) - Delegation Type Code. Maximum length is 40.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
