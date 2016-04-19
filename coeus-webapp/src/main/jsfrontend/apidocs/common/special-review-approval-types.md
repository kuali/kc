## Special Review Approval Types [/research-common/api/v1/special-review-approval-types/]

### Get Special Review Approval Types by Key [GET /research-common/api/v1/special-review-approval-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Special Review Approval Types [GET /research-common/api/v1/special-review-approval-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Special Review Approval Types with Filtering [GET /research-common/api/v1/special-review-approval-types/]
    
+ Parameters

    + approvalTypeCode (optional) - Special Review Approval Type Code. Maximum length is 3.
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
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Special Review Approval Types [GET /research-common/api/v1/special-review-approval-types/]
	                                          
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
    
            {"columns":["approvalTypeCode","description"],"primaryKey":"approvalTypeCode"}
		
### Get Blueprint API specification for Special Review Approval Types [GET /research-common/api/v1/special-review-approval-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Special Review Approval Types.md"
            transfer-encoding:chunked


### Update Special Review Approval Types [PUT /research-common/api/v1/special-review-approval-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Special Review Approval Types [PUT /research-common/api/v1/special-review-approval-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Special Review Approval Types [POST /research-common/api/v1/special-review-approval-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Special Review Approval Types [POST /research-common/api/v1/special-review-approval-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Special Review Approval Types by Key [DELETE /research-common/api/v1/special-review-approval-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Special Review Approval Types [DELETE /research-common/api/v1/special-review-approval-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Special Review Approval Types with Matching [DELETE /research-common/api/v1/special-review-approval-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + approvalTypeCode (optional) - Special Review Approval Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
