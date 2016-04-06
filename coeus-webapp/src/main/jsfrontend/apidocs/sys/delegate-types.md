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
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + delegationId
            + roleId
            + active
            + kimTypeId
            + delegationTypeCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","active": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Delegate Types [GET /research-sys/api/v1/delegate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Delegate Types [GET /research-sys/api/v1/delegate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

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
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Delegate Types with Matching [DELETE /research-sys/api/v1/delegate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + delegationId
            + roleId
            + active
            + kimTypeId
            + delegationTypeCode


+ Response 204
