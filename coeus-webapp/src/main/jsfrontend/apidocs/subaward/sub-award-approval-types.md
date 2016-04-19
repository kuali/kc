## Sub Award Approval Types [/subaward/api/v1/sub-award-approval-types/]

### Get Sub Award Approval Types by Key [GET /subaward/api/v1/sub-award-approval-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Approval Types [GET /subaward/api/v1/sub-award-approval-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Approval Types with Filtering [GET /subaward/api/v1/sub-award-approval-types/]
    
+ Parameters

    + subAwardApprovalTypeCode (optional) - SubAward Approval Type Code. Maximum length is 22.
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
              {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Approval Types [GET /subaward/api/v1/sub-award-approval-types/]
	                                          
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
    
            {"columns":["subAwardApprovalTypeCode","description"],"primaryKey":"subAwardApprovalTypeCode"}
		
### Get Blueprint API specification for Sub Award Approval Types [GET /subaward/api/v1/sub-award-approval-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Approval Types.md"
            transfer-encoding:chunked


### Update Sub Award Approval Types [PUT /subaward/api/v1/sub-award-approval-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Approval Types [PUT /subaward/api/v1/sub-award-approval-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Approval Types [POST /subaward/api/v1/sub-award-approval-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Approval Types [POST /subaward/api/v1/sub-award-approval-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardApprovalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Approval Types by Key [DELETE /subaward/api/v1/sub-award-approval-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Approval Types [DELETE /subaward/api/v1/sub-award-approval-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Approval Types with Matching [DELETE /subaward/api/v1/sub-award-approval-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardApprovalTypeCode (optional) - SubAward Approval Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
