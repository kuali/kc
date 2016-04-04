## Special Review Approval Types [/research-sys/api/v1/special-review-approval-types/]

### Get Special Review Approval Types by Key [GET /research-sys/api/v1/special-review-approval-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Special Review Approval Types [GET /research-sys/api/v1/special-review-approval-types/]
	 
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

### Get All Special Review Approval Types with Filtering [GET /research-sys/api/v1/special-review-approval-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + approvalTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Special Review Approval Types [GET /research-sys/api/v1/special-review-approval-types/]
	 
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
		
### Get Blueprint API specification for Special Review Approval Types [GET /research-sys/api/v1/special-review-approval-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Special Review Approval Types.md"
            transfer-encoding:chunked


### Update Special Review Approval Types [PUT /research-sys/api/v1/special-review-approval-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Special Review Approval Types [PUT /research-sys/api/v1/special-review-approval-types/]

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

### Insert Special Review Approval Types [POST /research-sys/api/v1/special-review-approval-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"approvalTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Special Review Approval Types [POST /research-sys/api/v1/special-review-approval-types/]

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
            
### Delete Special Review Approval Types by Key [DELETE /research-sys/api/v1/special-review-approval-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Special Review Approval Types [DELETE /research-sys/api/v1/special-review-approval-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Special Review Approval Types with Matching [DELETE /research-sys/api/v1/special-review-approval-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + approvalTypeCode
            + description


+ Response 204
