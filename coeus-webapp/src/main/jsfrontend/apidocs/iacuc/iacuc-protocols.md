## Iacuc Protocols [/iacuc/api/v1/iacuc-protocols/]

### Get Iacuc Protocols by Key [GET /iacuc/api/v1/iacuc-protocols/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocols [GET /iacuc/api/v1/iacuc-protocols/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"},
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocols with Filtering [GET /iacuc/api/v1/iacuc-protocols/]
    
+ Parameters

    + protocolId (optional) - Ac Protocol Id. Maximum length is 22.
    + documentNumber (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + active (optional) - Active. Maximum length is 1.
    + protocolTypeCode (optional) - Protocol Type Code. Maximum length is 22.
    + protocolStatusCode (optional) - Protocol Status Code. Maximum length is 3.
    + title (optional) - Title. Maximum length is 2000.
    + description (optional) - Description. Maximum length is 2000.
    + initialSubmissionDate (optional) - Initial Submission Date. Maximum length is 10.
    + approvalDate (optional) - Approval Date. Maximum length is 10.
    + expirationDate (optional) - Expiration Date. Maximum length is 10.
    + fdaApplicationNumber (optional) - Fda Application Number. Maximum length is 15.
    + protocolProjectTypeCode (optional) - Protocol Project Type Code. Maximum length is 3.
    + referenceNumber1 (optional) - Reference Number 1. Maximum length is 50.
    + referenceNumber2 (optional) - Reference Number 2. Maximum length is 50.
    + isBillable (optional) - Is Billable. Maximum length is 1.
    + specialReviewIndicator (optional) - Special Review Indicator. Maximum length is 2.
    + keyStudyPersonIndicator (optional) - Key Study Person Indicator. Maximum length is 2.
    + fundingSourceIndicator (optional) - Funding Source Indicator. Maximum length is 2.
    + correspondentIndicator (optional) - Correspondent Indicator. Maximum length is 2.
    + referenceIndicator (optional) - Reference Indicator. Maximum length is 2.
    + layStatement1 (optional) - Lay Statement 1. Maximum length is 2000.
    + layStatement2 (optional) - Lay Statement 2. Maximum length is 2000.
    + createTimestamp (optional) - 
    + createUser (optional) - 
    + lastApprovalDate (optional) - Last Approval Date. Maximum length is 10.
    + overviewTimeline (optional) - Overview and Timeline. Maximum length is 2000.
    + speciesStudyGroupIndicator (optional) - Species Study Group Indicator. Maximum length is 2.
    + alternativeSearchIndicator (optional) - Alternative Search Indicator. Maximum length is 2.
    + scientificJustifIndicator (optional) - Scientific Justif Indicator. Maximum length is 2.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"},
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocols [GET /iacuc/api/v1/iacuc-protocols/]
	                                          
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
    
            {"columns":["protocolId","documentNumber","protocolNumber","sequenceNumber","active","protocolTypeCode","protocolStatusCode","title","description","initialSubmissionDate","approvalDate","expirationDate","fdaApplicationNumber","protocolProjectTypeCode","referenceNumber1","referenceNumber2","isBillable","specialReviewIndicator","keyStudyPersonIndicator","fundingSourceIndicator","correspondentIndicator","referenceIndicator","layStatement1","layStatement2","createTimestamp","createUser","lastApprovalDate","overviewTimeline","speciesStudyGroupIndicator","alternativeSearchIndicator","scientificJustifIndicator"],"primaryKey":"protocolId"}
		
### Get Blueprint API specification for Iacuc Protocols [GET /iacuc/api/v1/iacuc-protocols/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocols.md"
            transfer-encoding:chunked


### Update Iacuc Protocols [PUT /iacuc/api/v1/iacuc-protocols/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocols [PUT /iacuc/api/v1/iacuc-protocols/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"},
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocols [POST /iacuc/api/v1/iacuc-protocols/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocols [POST /iacuc/api/v1/iacuc-protocols/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"},
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"},
              {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocols by Key [DELETE /iacuc/api/v1/iacuc-protocols/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocols [DELETE /iacuc/api/v1/iacuc-protocols/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocols with Matching [DELETE /iacuc/api/v1/iacuc-protocols/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolId (optional) - Ac Protocol Id. Maximum length is 22.
    + documentNumber (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + active (optional) - Active. Maximum length is 1.
    + protocolTypeCode (optional) - Protocol Type Code. Maximum length is 22.
    + protocolStatusCode (optional) - Protocol Status Code. Maximum length is 3.
    + title (optional) - Title. Maximum length is 2000.
    + description (optional) - Description. Maximum length is 2000.
    + initialSubmissionDate (optional) - Initial Submission Date. Maximum length is 10.
    + approvalDate (optional) - Approval Date. Maximum length is 10.
    + expirationDate (optional) - Expiration Date. Maximum length is 10.
    + fdaApplicationNumber (optional) - Fda Application Number. Maximum length is 15.
    + protocolProjectTypeCode (optional) - Protocol Project Type Code. Maximum length is 3.
    + referenceNumber1 (optional) - Reference Number 1. Maximum length is 50.
    + referenceNumber2 (optional) - Reference Number 2. Maximum length is 50.
    + isBillable (optional) - Is Billable. Maximum length is 1.
    + specialReviewIndicator (optional) - Special Review Indicator. Maximum length is 2.
    + keyStudyPersonIndicator (optional) - Key Study Person Indicator. Maximum length is 2.
    + fundingSourceIndicator (optional) - Funding Source Indicator. Maximum length is 2.
    + correspondentIndicator (optional) - Correspondent Indicator. Maximum length is 2.
    + referenceIndicator (optional) - Reference Indicator. Maximum length is 2.
    + layStatement1 (optional) - Lay Statement 1. Maximum length is 2000.
    + layStatement2 (optional) - Lay Statement 2. Maximum length is 2000.
    + createTimestamp (optional) - 
    + createUser (optional) - 
    + lastApprovalDate (optional) - Last Approval Date. Maximum length is 10.
    + overviewTimeline (optional) - Overview and Timeline. Maximum length is 2000.
    + speciesStudyGroupIndicator (optional) - Species Study Group Indicator. Maximum length is 2.
    + alternativeSearchIndicator (optional) - Alternative Search Indicator. Maximum length is 2.
    + scientificJustifIndicator (optional) - Scientific Justif Indicator. Maximum length is 2.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
