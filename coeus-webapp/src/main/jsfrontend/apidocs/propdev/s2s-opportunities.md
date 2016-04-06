## S2s Opportunities [/research-sys/api/v1/s2s-opportunities/]

### Get S2s Opportunities by Key [GET /research-sys/api/v1/s2s-opportunities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}

### Get All S2s Opportunities [GET /research-sys/api/v1/s2s-opportunities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"},
              {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s Opportunities with Filtering [GET /research-sys/api/v1/s2s-opportunities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + cfdaNumber
            + closingDate
            + competetionId
            + instructionUrl
            + openingDate
            + opportunity
            + opportunityId
            + opportunityTitle
            + revisionCode
            + revisionOtherDescription
            + s2sSubmissionTypeCode
            + schemaUrl
            + offeringAgency
            + agencyContactInfo
            + cfdaDescription
            + multiProject
            + providerCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"},
              {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Opportunities [GET /research-sys/api/v1/s2s-opportunities/]
	 
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
		
### Get Blueprint API specification for S2s Opportunities [GET /research-sys/api/v1/s2s-opportunities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Opportunities.md"
            transfer-encoding:chunked


### Update S2s Opportunities [PUT /research-sys/api/v1/s2s-opportunities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Opportunities [PUT /research-sys/api/v1/s2s-opportunities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"},
              {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s Opportunities [POST /research-sys/api/v1/s2s-opportunities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Opportunities [POST /research-sys/api/v1/s2s-opportunities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"},
              {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"},
              {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s Opportunities by Key [DELETE /research-sys/api/v1/s2s-opportunities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Opportunities [DELETE /research-sys/api/v1/s2s-opportunities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All S2s Opportunities with Matching [DELETE /research-sys/api/v1/s2s-opportunities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + cfdaNumber
            + closingDate
            + competetionId
            + instructionUrl
            + openingDate
            + opportunity
            + opportunityId
            + opportunityTitle
            + revisionCode
            + revisionOtherDescription
            + s2sSubmissionTypeCode
            + schemaUrl
            + offeringAgency
            + agencyContactInfo
            + cfdaDescription
            + multiProject
            + providerCode


+ Response 204
