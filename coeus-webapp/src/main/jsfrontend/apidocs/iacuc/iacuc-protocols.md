## Iacuc Protocols [/research-sys/api/v1/iacuc-protocols/]

### Get Iacuc Protocols by Key [GET /research-sys/api/v1/iacuc-protocols/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocols [GET /research-sys/api/v1/iacuc-protocols/]
	 
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

### Get All Iacuc Protocols with Filtering [GET /research-sys/api/v1/iacuc-protocols/]
    
+ Parameters

        + protocolId
            + documentNumber
            + protocolNumber
            + sequenceNumber
            + active
            + protocolTypeCode
            + protocolStatusCode
            + title
            + description
            + initialSubmissionDate
            + approvalDate
            + expirationDate
            + fdaApplicationNumber
            + protocolProjectTypeCode
            + referenceNumber1
            + referenceNumber2
            + isBillable
            + specialReviewIndicator
            + keyStudyPersonIndicator
            + fundingSourceIndicator
            + correspondentIndicator
            + referenceIndicator
            + layStatement1
            + layStatement2
            + createTimestamp
            + createUser
            + lastApprovalDate
            + overviewTimeline
            + speciesStudyGroupIndicator
            + alternativeSearchIndicator
            + scientificJustifIndicator

            
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
			
### Get Schema for Iacuc Protocols [GET /research-sys/api/v1/iacuc-protocols/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocols [GET /research-sys/api/v1/iacuc-protocols/]
	 
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


### Update Iacuc Protocols [PUT /research-sys/api/v1/iacuc-protocols/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocols [PUT /research-sys/api/v1/iacuc-protocols/]

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

### Insert Iacuc Protocols [POST /research-sys/api/v1/iacuc-protocols/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolId": "(val)","documentNumber": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","active": "(val)","protocolTypeCode": "(val)","protocolStatusCode": "(val)","title": "(val)","description": "(val)","initialSubmissionDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","fdaApplicationNumber": "(val)","protocolProjectTypeCode": "(val)","referenceNumber1": "(val)","referenceNumber2": "(val)","isBillable": "(val)","specialReviewIndicator": "(val)","keyStudyPersonIndicator": "(val)","fundingSourceIndicator": "(val)","correspondentIndicator": "(val)","referenceIndicator": "(val)","layStatement1": "(val)","layStatement2": "(val)","createTimestamp": "(val)","createUser": "(val)","lastApprovalDate": "(val)","overviewTimeline": "(val)","speciesStudyGroupIndicator": "(val)","alternativeSearchIndicator": "(val)","scientificJustifIndicator": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocols [POST /research-sys/api/v1/iacuc-protocols/]

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
            
### Delete Iacuc Protocols by Key [DELETE /research-sys/api/v1/iacuc-protocols/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocols [DELETE /research-sys/api/v1/iacuc-protocols/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocols with Matching [DELETE /research-sys/api/v1/iacuc-protocols/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolId
            + documentNumber
            + protocolNumber
            + sequenceNumber
            + active
            + protocolTypeCode
            + protocolStatusCode
            + title
            + description
            + initialSubmissionDate
            + approvalDate
            + expirationDate
            + fdaApplicationNumber
            + protocolProjectTypeCode
            + referenceNumber1
            + referenceNumber2
            + isBillable
            + specialReviewIndicator
            + keyStudyPersonIndicator
            + fundingSourceIndicator
            + correspondentIndicator
            + referenceIndicator
            + layStatement1
            + layStatement2
            + createTimestamp
            + createUser
            + lastApprovalDate
            + overviewTimeline
            + speciesStudyGroupIndicator
            + alternativeSearchIndicator
            + scientificJustifIndicator

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
