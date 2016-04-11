## Role Document Delegations [/research-sys/api/v1/role-document-delegations/]

### Get Role Document Delegations by Key [GET /research-sys/api/v1/role-document-delegations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Role Document Delegations [GET /research-sys/api/v1/role-document-delegations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Role Document Delegations with Filtering [GET /research-sys/api/v1/role-document-delegations/]
    
+ Parameters

        + delegationId
            + roleId
            + kimTypeId
            + delegationTypeCode
            + documentNumber
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Role Document Delegations [GET /research-sys/api/v1/role-document-delegations/]
	                                          
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
    
            {"columns":["delegationId","roleId","kimTypeId","delegationTypeCode","documentNumber","active"],"primaryKey":"delegationId"}
		
### Get Blueprint API specification for Role Document Delegations [GET /research-sys/api/v1/role-document-delegations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Role Document Delegations.md"
            transfer-encoding:chunked


### Update Role Document Delegations [PUT /research-sys/api/v1/role-document-delegations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Role Document Delegations [PUT /research-sys/api/v1/role-document-delegations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Role Document Delegations [POST /research-sys/api/v1/role-document-delegations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Role Document Delegations [POST /research-sys/api/v1/role-document-delegations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"delegationId": "(val)","roleId": "(val)","kimTypeId": "(val)","delegationTypeCode": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Role Document Delegations by Key [DELETE /research-sys/api/v1/role-document-delegations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Document Delegations [DELETE /research-sys/api/v1/role-document-delegations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Document Delegations with Matching [DELETE /research-sys/api/v1/role-document-delegations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + delegationId
            + roleId
            + kimTypeId
            + delegationTypeCode
            + documentNumber
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
