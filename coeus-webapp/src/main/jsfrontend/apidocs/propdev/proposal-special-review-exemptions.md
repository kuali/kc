## Proposal Special Review Exemptions [/research-sys/api/v1/proposal-special-review-exemptions/]

### Get Proposal Special Review Exemptions by Key [GET /research-sys/api/v1/proposal-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Proposal Special Review Exemptions [GET /research-sys/api/v1/proposal-special-review-exemptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Special Review Exemptions with Filtering [GET /research-sys/api/v1/proposal-special-review-exemptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + exemptionTypeCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Special Review Exemptions [GET /research-sys/api/v1/proposal-special-review-exemptions/]
	 
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
		
### Get Blueprint API specification for Proposal Special Review Exemptions [GET /research-sys/api/v1/proposal-special-review-exemptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Special Review Exemptions.md"
            transfer-encoding:chunked


### Update Proposal Special Review Exemptions [PUT /research-sys/api/v1/proposal-special-review-exemptions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Special Review Exemptions [PUT /research-sys/api/v1/proposal-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Special Review Exemptions [POST /research-sys/api/v1/proposal-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Special Review Exemptions [POST /research-sys/api/v1/proposal-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Special Review Exemptions by Key [DELETE /research-sys/api/v1/proposal-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Special Review Exemptions [DELETE /research-sys/api/v1/proposal-special-review-exemptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Special Review Exemptions with Matching [DELETE /research-sys/api/v1/proposal-special-review-exemptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + exemptionTypeCode


+ Response 204
