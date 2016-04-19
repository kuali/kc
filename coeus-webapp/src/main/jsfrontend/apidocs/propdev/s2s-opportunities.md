## S2s Opportunities [/propdev/api/v1/s2s-opportunities/]

### Get S2s Opportunities by Key [GET /propdev/api/v1/s2s-opportunities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}

### Get All S2s Opportunities [GET /propdev/api/v1/s2s-opportunities/]
	 
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

### Get All S2s Opportunities with Filtering [GET /propdev/api/v1/s2s-opportunities/]
    
+ Parameters

    + cfdaNumber (optional) - A unique identifier for the sponsor and the funding opportunity announcement.  AKA "Catalogue of Federal Domestic Assistance Number". Maximum length is 6.
    + closingDate (optional) - Closing Date. Maximum length is 21.
    + competetionId (optional) - Competition Id. Maximum length is 50.
    + instructionUrl (optional) - Instruction Page. Maximum length is 300.
    + openingDate (optional) - Opening Date. Maximum length is 21.
    + opportunity (optional) - Opportunity. Maximum length is 40000000.
    + opportunityId (optional) - A unique identifier associated with each sponsor's funding opportunity announcement. AKA "Funding Opportunity Announcement number" or "FOA number". Maximum length is 50.
    + opportunityTitle (optional) - The title of a publicly available document, announcing a federal agency's intentions to award discretionary grants or cooperative agreements, usually as a result of competition for funds.  AKA  Funding opportunity announcements, notices of funding availability, or solicitations. Maximum length is 255.
    + revisionCode (optional) - S2s Revision Type Code. Maximum length is 3.
    + revisionOtherDescription (optional) - Revision Other Description. Maximum length is 45.
    + s2sSubmissionTypeCode (optional) - Submission Type. Maximum length is 3.
    + schemaUrl (optional) - Schema URL. Maximum length is 300.
    + offeringAgency (optional) - Offering Agency.
    + agencyContactInfo (optional) - Agency Contact Info.
    + cfdaDescription (optional) - Cfda Description.
    + multiProject (optional) - Is this a multiple project opportunity?. Maximum length is 1.
    + providerCode (optional) - S2S Provider. Maximum length is 4.

            
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
			
### Get Schema for S2s Opportunities [GET /propdev/api/v1/s2s-opportunities/]
	                                          
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
    
            {"columns":["cfdaNumber","closingDate","competetionId","instructionUrl","openingDate","opportunity","opportunityId","opportunityTitle","revisionCode","revisionOtherDescription","s2sSubmissionTypeCode","schemaUrl","offeringAgency","agencyContactInfo","cfdaDescription","multiProject","providerCode"],"primaryKey":"developmentProposal"}
		
### Get Blueprint API specification for S2s Opportunities [GET /propdev/api/v1/s2s-opportunities/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Opportunities.md"
            transfer-encoding:chunked


### Update S2s Opportunities [PUT /propdev/api/v1/s2s-opportunities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Opportunities [PUT /propdev/api/v1/s2s-opportunities/]

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

### Insert S2s Opportunities [POST /propdev/api/v1/s2s-opportunities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"cfdaNumber": "(val)","closingDate": "(val)","competetionId": "(val)","instructionUrl": "(val)","openingDate": "(val)","opportunity": "(val)","opportunityId": "(val)","opportunityTitle": "(val)","revisionCode": "(val)","revisionOtherDescription": "(val)","s2sSubmissionTypeCode": "(val)","schemaUrl": "(val)","offeringAgency": "(val)","agencyContactInfo": "(val)","cfdaDescription": "(val)","multiProject": "(val)","providerCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Opportunities [POST /propdev/api/v1/s2s-opportunities/]

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
            
### Delete S2s Opportunities by Key [DELETE /propdev/api/v1/s2s-opportunities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Opportunities [DELETE /propdev/api/v1/s2s-opportunities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Opportunities with Matching [DELETE /propdev/api/v1/s2s-opportunities/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + cfdaNumber (optional) - A unique identifier for the sponsor and the funding opportunity announcement.  AKA "Catalogue of Federal Domestic Assistance Number". Maximum length is 6.
    + closingDate (optional) - Closing Date. Maximum length is 21.
    + competetionId (optional) - Competition Id. Maximum length is 50.
    + instructionUrl (optional) - Instruction Page. Maximum length is 300.
    + openingDate (optional) - Opening Date. Maximum length is 21.
    + opportunity (optional) - Opportunity. Maximum length is 40000000.
    + opportunityId (optional) - A unique identifier associated with each sponsor's funding opportunity announcement. AKA "Funding Opportunity Announcement number" or "FOA number". Maximum length is 50.
    + opportunityTitle (optional) - The title of a publicly available document, announcing a federal agency's intentions to award discretionary grants or cooperative agreements, usually as a result of competition for funds.  AKA  Funding opportunity announcements, notices of funding availability, or solicitations. Maximum length is 255.
    + revisionCode (optional) - S2s Revision Type Code. Maximum length is 3.
    + revisionOtherDescription (optional) - Revision Other Description. Maximum length is 45.
    + s2sSubmissionTypeCode (optional) - Submission Type. Maximum length is 3.
    + schemaUrl (optional) - Schema URL. Maximum length is 300.
    + offeringAgency (optional) - Offering Agency.
    + agencyContactInfo (optional) - Agency Contact Info.
    + cfdaDescription (optional) - Cfda Description.
    + multiProject (optional) - Is this a multiple project opportunity?. Maximum length is 1.
    + providerCode (optional) - S2S Provider. Maximum length is 4.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
