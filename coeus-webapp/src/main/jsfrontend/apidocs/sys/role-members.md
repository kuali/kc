## Role Members [/research-sys/api/v1/role-members/]

### Get Role Members by Key [GET /research-sys/api/v1/role-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}

### Get All Role Members [GET /research-sys/api/v1/role-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Role Members with Filtering [GET /research-sys/api/v1/role-members/]
    
+ Parameters

    + id (optional) - Role Member Identifier. Maximum length is 40.
    + roleId (optional) - Role. Maximum length is 40.
    + activeFromDateValue (optional) - Active From Date Value.
    + activeToDateValue (optional) - Active To Date Value.
    + memberId (optional) - Member Identifier. Maximum length is 40.
    + typeCode (optional) - Member Type Code. Maximum length is 40.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Role Members [GET /research-sys/api/v1/role-members/]
	                                          
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
    
            {"columns":["id","roleId","activeFromDateValue","activeToDateValue","memberId","typeCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Role Members [GET /research-sys/api/v1/role-members/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Role Members.md"
            transfer-encoding:chunked


### Update Role Members [PUT /research-sys/api/v1/role-members/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Role Members [PUT /research-sys/api/v1/role-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Role Members [POST /research-sys/api/v1/role-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Role Members [POST /research-sys/api/v1/role-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Role Members by Key [DELETE /research-sys/api/v1/role-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Members [DELETE /research-sys/api/v1/role-members/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Members with Matching [DELETE /research-sys/api/v1/role-members/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Role Member Identifier. Maximum length is 40.
    + roleId (optional) - Role. Maximum length is 40.
    + activeFromDateValue (optional) - Active From Date Value.
    + activeToDateValue (optional) - Active To Date Value.
    + memberId (optional) - Member Identifier. Maximum length is 40.
    + typeCode (optional) - Member Type Code. Maximum length is 40.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
