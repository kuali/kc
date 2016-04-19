## Negotiations [/negotiation/api/v1/negotiations/]

### Get Negotiations by Key [GET /negotiation/api/v1/negotiations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}

### Get All Negotiations [GET /negotiation/api/v1/negotiations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"},
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Negotiations with Filtering [GET /negotiation/api/v1/negotiations/]
    
+ Parameters

    + negotiationId (optional) - Negotiation ID. Maximum length is 22.
    + documentNumber (optional) - Document Number. Maximum length is 25.
    + negotiationStatusId (optional) - Negotiation Status. Maximum length is 22.
    + negotiationAgreementTypeId (optional) - Agreement Type. Maximum length is 22.
    + negotiationAssociationTypeId (optional) - Negotiation Association Type. Maximum length is 22.
    + negotiatorPersonId (optional) - Negotiator. Maximum length is 40.
    + negotiatorName (optional) - Full Name. Maximum length is 90.
    + negotiationStartDate (optional) - Start Date. Maximum length is 21.
    + negotiationEndDate (optional) - End Date. Maximum length is 21.
    + anticipatedAwardDate (optional) - Anticipated Award Date. Maximum length is 21.
    + documentFolder (optional) - Document Folder. Maximum length is 255.
    + associatedDocumentId (optional) - Negotiation Association ID. Maximum length is 25.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"},
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Negotiations [GET /negotiation/api/v1/negotiations/]
	                                          
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
    
            {"columns":["negotiationId","documentNumber","negotiationStatusId","negotiationAgreementTypeId","negotiationAssociationTypeId","negotiatorPersonId","negotiatorName","negotiationStartDate","negotiationEndDate","anticipatedAwardDate","documentFolder","associatedDocumentId"],"primaryKey":"negotiationId"}
		
### Get Blueprint API specification for Negotiations [GET /negotiation/api/v1/negotiations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiations.md"
            transfer-encoding:chunked


### Update Negotiations [PUT /negotiation/api/v1/negotiations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiations [PUT /negotiation/api/v1/negotiations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"},
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Negotiations [POST /negotiation/api/v1/negotiations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiations [POST /negotiation/api/v1/negotiations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"},
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"},
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Negotiations by Key [DELETE /negotiation/api/v1/negotiations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiations [DELETE /negotiation/api/v1/negotiations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiations with Matching [DELETE /negotiation/api/v1/negotiations/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + negotiationId (optional) - Negotiation ID. Maximum length is 22.
    + documentNumber (optional) - Document Number. Maximum length is 25.
    + negotiationStatusId (optional) - Negotiation Status. Maximum length is 22.
    + negotiationAgreementTypeId (optional) - Agreement Type. Maximum length is 22.
    + negotiationAssociationTypeId (optional) - Negotiation Association Type. Maximum length is 22.
    + negotiatorPersonId (optional) - Negotiator. Maximum length is 40.
    + negotiatorName (optional) - Full Name. Maximum length is 90.
    + negotiationStartDate (optional) - Start Date. Maximum length is 21.
    + negotiationEndDate (optional) - End Date. Maximum length is 21.
    + anticipatedAwardDate (optional) - Anticipated Award Date. Maximum length is 21.
    + documentFolder (optional) - Document Folder. Maximum length is 255.
    + associatedDocumentId (optional) - Negotiation Association ID. Maximum length is 25.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
