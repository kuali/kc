## Proposal Special Reviews [/research-sys/api/v1/proposal-special-reviews/]

### Get Proposal Special Reviews by Key [GET /research-sys/api/v1/proposal-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}

### Get All Proposal Special Reviews [GET /research-sys/api/v1/proposal-special-reviews/]
	 
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

### Get All Proposal Special Reviews with Filtering [GET /research-sys/api/v1/proposal-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + hierarchyProposalNumber
            + hiddenInHierarchy
            + approvalTypeCode
            + approvalDate
            + comments
            + protocolStatus
            + protocolNumber
            + specialReviewTypeCode
            + specialReviewNumber
            + applicationDate
            + expirationDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Special Reviews [GET /research-sys/api/v1/proposal-special-reviews/]
	 
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
		
### Get Blueprint API specification for Proposal Special Reviews [GET /research-sys/api/v1/proposal-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Special Reviews.md"
            transfer-encoding:chunked


### Update Proposal Special Reviews [PUT /research-sys/api/v1/proposal-special-reviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Special Reviews [PUT /research-sys/api/v1/proposal-special-reviews/]

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

### Insert Proposal Special Reviews [POST /research-sys/api/v1/proposal-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","approvalTypeCode": "(val)","approvalDate": "(val)","comments": "(val)","protocolStatus": "(val)","protocolNumber": "(val)","specialReviewTypeCode": "(val)","specialReviewNumber": "(val)","applicationDate": "(val)","expirationDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Special Reviews [POST /research-sys/api/v1/proposal-special-reviews/]

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
            
### Delete Proposal Special Reviews by Key [DELETE /research-sys/api/v1/proposal-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Special Reviews [DELETE /research-sys/api/v1/proposal-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Special Reviews with Matching [DELETE /research-sys/api/v1/proposal-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + hierarchyProposalNumber
            + hiddenInHierarchy
            + approvalTypeCode
            + approvalDate
            + comments
            + protocolStatus
            + protocolNumber
            + specialReviewTypeCode
            + specialReviewNumber
            + applicationDate
            + expirationDate


+ Response 204
