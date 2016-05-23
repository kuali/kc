## Group Document Members [/research-sys/api/v1/group-document-members/]

### Get Group Document Members by Key [GET /research-sys/api/v1/group-document-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Group Document Members [GET /research-sys/api/v1/group-document-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Group Document Members with Filtering [GET /research-sys/api/v1/group-document-members/]
    
+ Parameters

    + groupMemberId (optional) - Group Member Identifier. Maximum length is 40.
    + groupId (optional) - Group Id.
    + memberId (optional) - Member Identifier. Maximum length is 40.
    + memberName (optional) - Member Name. Maximum length is 80.
    + memberTypeCode (optional) - Member Type Code. Maximum length is 40.
    + activeFromDate (optional) - Start Date. Maximum length is 21.
    + activeToDate (optional) - End Date. Maximum length is 21.
    + documentNumber (optional) - Document Number.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Group Document Members [GET /research-sys/api/v1/group-document-members/]
	                                          
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
    
            {"columns":["groupMemberId","groupId","memberId","memberName","memberTypeCode","activeFromDate","activeToDate","documentNumber"],"primaryKey":"groupMemberId"}
		
### Get Blueprint API specification for Group Document Members [GET /research-sys/api/v1/group-document-members/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Group Document Members.md"
            transfer-encoding:chunked
### Update Group Document Members [PUT /research-sys/api/v1/group-document-members/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Group Document Members [PUT /research-sys/api/v1/group-document-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Group Document Members [POST /research-sys/api/v1/group-document-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Group Document Members [POST /research-sys/api/v1/group-document-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"groupMemberId": "(val)","groupId": "(val)","memberId": "(val)","memberName": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
### Delete Group Document Members by Key [DELETE /research-sys/api/v1/group-document-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Group Document Members [DELETE /research-sys/api/v1/group-document-members/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Group Document Members with Matching [DELETE /research-sys/api/v1/group-document-members/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + groupMemberId (optional) - Group Member Identifier. Maximum length is 40.
    + groupId (optional) - Group Id.
    + memberId (optional) - Member Identifier. Maximum length is 40.
    + memberName (optional) - Member Name. Maximum length is 80.
    + memberTypeCode (optional) - Member Type Code. Maximum length is 40.
    + activeFromDate (optional) - Start Date. Maximum length is 21.
    + activeToDate (optional) - End Date. Maximum length is 21.
    + documentNumber (optional) - Document Number.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
