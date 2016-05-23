## Narratives [/propdev/api/v1/narratives/]

### Get Narratives by Key [GET /propdev/api/v1/narratives/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Narratives [GET /propdev/api/v1/narratives/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Narratives with Filtering [GET /propdev/api/v1/narratives/]
    
+ Parameters

    + moduleNumber (optional) - Module Number. Maximum length is 4.
    + comments (optional) - Proposal attachment comments. Maximum length is 300.
    + contactName (optional) - Contact Name. Maximum length is 30.
    + emailAddress (optional) - Email Address. Maximum length is 60.
    + moduleSequenceNumber (optional) - Module Sequence Number. Maximum length is 4.
    + moduleStatusCode (optional) - The Types that the Proposal Attachment Status be. Maximum length is 3.
    + moduleTitle (optional) - This field is a free-form text field in which the user is able to name the attachment they are uploading beyond merely the type.  Ex. 'Mass Spectrometer Quotation' instead of merely having "equipment" as an attachment type. Maximum length is 150.
    + narrativeTypeCode (optional) - Types of the Proposal Attachments. Maximum length is 3.
    + phoneNumber (optional) - Phone Number. Maximum length is 20.
    + name (optional) - This is the name of the file path and name that the user is uploading; can be typed in or the user can use the "browse" feature to find the file on their computer or attached mass storage device. Maximum length is 150.
    + type (optional) - Type.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Narratives [GET /propdev/api/v1/narratives/]
	                                          
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
    
            {"columns":["moduleNumber","comments","contactName","emailAddress","moduleSequenceNumber","moduleStatusCode","moduleTitle","narrativeTypeCode","phoneNumber","name","type","hierarchyProposalNumber","hiddenInHierarchy"],"primaryKey":"developmentProposal:moduleNumber"}
		
### Get Blueprint API specification for Narratives [GET /propdev/api/v1/narratives/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Narratives.md"
            transfer-encoding:chunked
### Update Narratives [PUT /propdev/api/v1/narratives/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Narratives [PUT /propdev/api/v1/narratives/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Narratives [POST /propdev/api/v1/narratives/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Narratives [POST /propdev/api/v1/narratives/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
### Delete Narratives by Key [DELETE /propdev/api/v1/narratives/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narratives [DELETE /propdev/api/v1/narratives/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narratives with Matching [DELETE /propdev/api/v1/narratives/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + moduleNumber (optional) - Module Number. Maximum length is 4.
    + comments (optional) - Proposal attachment comments. Maximum length is 300.
    + contactName (optional) - Contact Name. Maximum length is 30.
    + emailAddress (optional) - Email Address. Maximum length is 60.
    + moduleSequenceNumber (optional) - Module Sequence Number. Maximum length is 4.
    + moduleStatusCode (optional) - The Types that the Proposal Attachment Status be. Maximum length is 3.
    + moduleTitle (optional) - This field is a free-form text field in which the user is able to name the attachment they are uploading beyond merely the type.  Ex. 'Mass Spectrometer Quotation' instead of merely having "equipment" as an attachment type. Maximum length is 150.
    + narrativeTypeCode (optional) - Types of the Proposal Attachments. Maximum length is 3.
    + phoneNumber (optional) - Phone Number. Maximum length is 20.
    + name (optional) - This is the name of the file path and name that the user is uploading; can be typed in or the user can use the "browse" feature to find the file on their computer or attached mass storage device. Maximum length is 150.
    + type (optional) - Type.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
