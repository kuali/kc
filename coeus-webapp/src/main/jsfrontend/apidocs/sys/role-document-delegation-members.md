## Role Document Delegation Members [/research-sys/api/v1/role-document-delegation-members/]

### Get Role Document Delegation Members by Key [GET /research-sys/api/v1/role-document-delegation-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Role Document Delegation Members [GET /research-sys/api/v1/role-document-delegation-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Role Document Delegation Members with Filtering [GET /research-sys/api/v1/role-document-delegation-members/]
    
+ Parameters

        + delegationMemberId
            + roleMemberId
            + delegationId
            + memberId
            + memberTypeCode
            + memberName
            + activeFromDate
            + activeToDate
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
              {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Role Document Delegation Members [GET /research-sys/api/v1/role-document-delegation-members/]
	                                          
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
    
            {"columns":["delegationMemberId","roleMemberId","delegationId","memberId","memberTypeCode","memberName","activeFromDate","activeToDate","documentNumber","active"],"primaryKey":"delegationMemberId"}
		
### Get Blueprint API specification for Role Document Delegation Members [GET /research-sys/api/v1/role-document-delegation-members/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Role Document Delegation Members.md"
            transfer-encoding:chunked


### Update Role Document Delegation Members [PUT /research-sys/api/v1/role-document-delegation-members/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Role Document Delegation Members [PUT /research-sys/api/v1/role-document-delegation-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Role Document Delegation Members [POST /research-sys/api/v1/role-document-delegation-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Role Document Delegation Members [POST /research-sys/api/v1/role-document-delegation-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","roleMemberId": "(val)","delegationId": "(val)","memberId": "(val)","memberTypeCode": "(val)","memberName": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Role Document Delegation Members by Key [DELETE /research-sys/api/v1/role-document-delegation-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Document Delegation Members [DELETE /research-sys/api/v1/role-document-delegation-members/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Document Delegation Members with Matching [DELETE /research-sys/api/v1/role-document-delegation-members/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + delegationMemberId
            + roleMemberId
            + delegationId
            + memberId
            + memberTypeCode
            + memberName
            + activeFromDate
            + activeToDate
            + documentNumber
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
