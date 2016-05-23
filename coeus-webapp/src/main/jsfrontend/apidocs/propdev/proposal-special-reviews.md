## Proposal Special Reviews [/propdev/api/v1/proposal-special-reviews/]

### Get Proposal Special Reviews by Key [GET /propdev/api/v1/proposal-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}

### Get All Proposal Special Reviews [GET /propdev/api/v1/proposal-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Special Reviews with Filtering [GET /propdev/api/v1/proposal-special-reviews/]
    
+ Parameters

    + id (optional) - Proposal Special Review Id. Maximum length is 22.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.
    + approvalTypeCode (optional) - Approval Status Type Code. Maximum length is 3.
    + approvalDate (optional) - Approval Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 2000.
    + protocolStatus (optional) - Protocol Status.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + specialReviewTypeCode (optional) - Special Review Type Code. Maximum length is 3.
    + specialReviewNumber (optional) - Special Review Number. Maximum length is 22.
    + applicationDate (optional) - Application Date. Maximum length is 10.
    + expirationDate (optional) - Expiration Date. Maximum length is 10.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Special Reviews [GET /propdev/api/v1/proposal-special-reviews/]
	                                          
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
    
            {"columns":["id","hierarchyProposalNumber","hiddenInHierarchy","approvalTypeCode","approvalDate","comments","protocolStatus","protocolNumber","specialReviewTypeCode","specialReviewNumber","applicationDate","expirationDate"],"primaryKey":"id"}
		
### Get Blueprint API specification for Proposal Special Reviews [GET /propdev/api/v1/proposal-special-reviews/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Special Reviews.md"
            transfer-encoding:chunked
### Update Proposal Special Reviews [PUT /propdev/api/v1/proposal-special-reviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Special Reviews [PUT /propdev/api/v1/proposal-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Special Reviews [POST /propdev/api/v1/proposal-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Special Reviews [POST /propdev/api/v1/proposal-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Special Reviews by Key [DELETE /propdev/api/v1/proposal-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Special Reviews [DELETE /propdev/api/v1/proposal-special-reviews/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Special Reviews with Matching [DELETE /propdev/api/v1/proposal-special-reviews/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Proposal Special Review Id. Maximum length is 22.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.
    + approvalTypeCode (optional) - Approval Status Type Code. Maximum length is 3.
    + approvalDate (optional) - Approval Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 2000.
    + protocolStatus (optional) - Protocol Status.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + specialReviewTypeCode (optional) - Special Review Type Code. Maximum length is 3.
    + specialReviewNumber (optional) - Special Review Number. Maximum length is 22.
    + applicationDate (optional) - Application Date. Maximum length is 10.
    + expirationDate (optional) - Expiration Date. Maximum length is 10.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
