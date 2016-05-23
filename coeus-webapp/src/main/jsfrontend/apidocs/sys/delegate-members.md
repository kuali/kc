## Delegate Members [/research-sys/api/v1/delegate-members/]

### Get Delegate Members by Key [GET /research-sys/api/v1/delegate-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}

### Get All Delegate Members [GET /research-sys/api/v1/delegate-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Delegate Members with Filtering [GET /research-sys/api/v1/delegate-members/]
    
+ Parameters

    + delegationMemberId (optional) - 
    + delegationId (optional) - 
    + roleMemberId (optional) - 
    + activeFromDateValue (optional) - 
    + activeToDateValue (optional) - 
    + memberId (optional) - 
    + typeCode (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Delegate Members [GET /research-sys/api/v1/delegate-members/]
	                                          
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
    
            {"columns":["delegationMemberId","delegationId","roleMemberId","activeFromDateValue","activeToDateValue","memberId","typeCode"],"primaryKey":"delegationMemberId"}
		
### Get Blueprint API specification for Delegate Members [GET /research-sys/api/v1/delegate-members/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Delegate Members.md"
            transfer-encoding:chunked
### Update Delegate Members [PUT /research-sys/api/v1/delegate-members/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Delegate Members [PUT /research-sys/api/v1/delegate-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Delegate Members [POST /research-sys/api/v1/delegate-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Delegate Members [POST /research-sys/api/v1/delegate-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","delegationId": "(val)","roleMemberId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Delegate Members by Key [DELETE /research-sys/api/v1/delegate-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Delegate Members [DELETE /research-sys/api/v1/delegate-members/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Delegate Members with Matching [DELETE /research-sys/api/v1/delegate-members/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + delegationMemberId (optional) - 
    + delegationId (optional) - 
    + roleMemberId (optional) - 
    + activeFromDateValue (optional) - 
    + activeToDateValue (optional) - 
    + memberId (optional) - 
    + typeCode (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
