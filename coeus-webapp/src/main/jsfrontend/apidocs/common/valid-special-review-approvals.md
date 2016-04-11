## Valid Special Review Approvals [/research-sys/api/v1/valid-special-review-approvals/]

### Get Valid Special Review Approvals by Key [GET /research-sys/api/v1/valid-special-review-approvals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}

### Get All Valid Special Review Approvals [GET /research-sys/api/v1/valid-special-review-approvals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"},
              {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Special Review Approvals with Filtering [GET /research-sys/api/v1/valid-special-review-approvals/]
    
+ Parameters

        + validSpecialReviewApprovalId
            + specialReviewTypeCode
            + approvalTypeCode
            + applicationDateFlag
            + approvalDateFlag
            + exemptNumberFlag
            + protocolNumberFlag

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"},
              {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Special Review Approvals [GET /research-sys/api/v1/valid-special-review-approvals/]
	                                          
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
    
            {"columns":["validSpecialReviewApprovalId","specialReviewTypeCode","approvalTypeCode","applicationDateFlag","approvalDateFlag","exemptNumberFlag","protocolNumberFlag"],"primaryKey":"validSpecialReviewApprovalId"}
		
### Get Blueprint API specification for Valid Special Review Approvals [GET /research-sys/api/v1/valid-special-review-approvals/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Special Review Approvals.md"
            transfer-encoding:chunked


### Update Valid Special Review Approvals [PUT /research-sys/api/v1/valid-special-review-approvals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Special Review Approvals [PUT /research-sys/api/v1/valid-special-review-approvals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"},
              {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Valid Special Review Approvals [POST /research-sys/api/v1/valid-special-review-approvals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Special Review Approvals [POST /research-sys/api/v1/valid-special-review-approvals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"},
              {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"},
              {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Valid Special Review Approvals by Key [DELETE /research-sys/api/v1/valid-special-review-approvals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Special Review Approvals [DELETE /research-sys/api/v1/valid-special-review-approvals/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Special Review Approvals with Matching [DELETE /research-sys/api/v1/valid-special-review-approvals/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + validSpecialReviewApprovalId
            + specialReviewTypeCode
            + approvalTypeCode
            + applicationDateFlag
            + approvalDateFlag
            + exemptNumberFlag
            + protocolNumberFlag

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
