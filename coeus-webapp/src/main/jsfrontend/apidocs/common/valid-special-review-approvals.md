## Valid Special Review Approvals [/research-common/api/v1/valid-special-review-approvals/]

### Get Valid Special Review Approvals by Key [GET /research-common/api/v1/valid-special-review-approvals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}

### Get All Valid Special Review Approvals [GET /research-common/api/v1/valid-special-review-approvals/]
	 
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

### Get All Valid Special Review Approvals with Filtering [GET /research-common/api/v1/valid-special-review-approvals/]
    
+ Parameters

    + validSpecialReviewApprovalId (optional) - Valid Special Review Approval Id. Maximum length is 22.
    + specialReviewTypeCode (optional) - Special Review Type Code. Maximum length is 3.
    + approvalTypeCode (optional) - Special Review Approval Type Code. Maximum length is 3.
    + applicationDateFlag (optional) - Validate Application Date. Maximum length is 1.
    + approvalDateFlag (optional) - Validate Approval Date. Maximum length is 1.
    + exemptNumberFlag (optional) - Validate Exempt Number is required or not. Maximum length is 1.
    + protocolNumberFlag (optional) - Validate Protocol Number. Maximum length is 1.

            
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
			
### Get Schema for Valid Special Review Approvals [GET /research-common/api/v1/valid-special-review-approvals/]
	                                          
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
		
### Get Blueprint API specification for Valid Special Review Approvals [GET /research-common/api/v1/valid-special-review-approvals/]
	 
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
### Update Valid Special Review Approvals [PUT /research-common/api/v1/valid-special-review-approvals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Special Review Approvals [PUT /research-common/api/v1/valid-special-review-approvals/]

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
### Insert Valid Special Review Approvals [POST /research-common/api/v1/valid-special-review-approvals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validSpecialReviewApprovalId": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","applicationDateFlag": "(val)","approvalDateFlag": "(val)","exemptNumberFlag": "(val)","protocolNumberFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Special Review Approvals [POST /research-common/api/v1/valid-special-review-approvals/]

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
### Delete Valid Special Review Approvals by Key [DELETE /research-common/api/v1/valid-special-review-approvals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Special Review Approvals [DELETE /research-common/api/v1/valid-special-review-approvals/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Special Review Approvals with Matching [DELETE /research-common/api/v1/valid-special-review-approvals/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validSpecialReviewApprovalId (optional) - Valid Special Review Approval Id. Maximum length is 22.
    + specialReviewTypeCode (optional) - Special Review Type Code. Maximum length is 3.
    + approvalTypeCode (optional) - Special Review Approval Type Code. Maximum length is 3.
    + applicationDateFlag (optional) - Validate Application Date. Maximum length is 1.
    + approvalDateFlag (optional) - Validate Approval Date. Maximum length is 1.
    + exemptNumberFlag (optional) - Validate Exempt Number is required or not. Maximum length is 1.
    + protocolNumberFlag (optional) - Validate Protocol Number. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
