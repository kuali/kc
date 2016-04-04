## Negotiations [/research-sys/api/v1/negotiations/]

### Get Negotiations by Key [GET /research-sys/api/v1/negotiations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}

### Get All Negotiations [GET /research-sys/api/v1/negotiations/]
	 
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

### Get All Negotiations with Filtering [GET /research-sys/api/v1/negotiations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + negotiationId
            + documentNumber
            + negotiationStatusId
            + negotiationAgreementTypeId
            + negotiationAssociationTypeId
            + negotiatorPersonId
            + negotiatorName
            + negotiationStartDate
            + negotiationEndDate
            + anticipatedAwardDate
            + documentFolder
            + associatedDocumentId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"},
              {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Negotiations [GET /research-sys/api/v1/negotiations/]
	 
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
		
### Get Blueprint API specification for Negotiations [GET /research-sys/api/v1/negotiations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiations.md"
            transfer-encoding:chunked


### Update Negotiations [PUT /research-sys/api/v1/negotiations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiations [PUT /research-sys/api/v1/negotiations/]

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

### Insert Negotiations [POST /research-sys/api/v1/negotiations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"negotiationId": "(val)","documentNumber": "(val)","negotiationStatusId": "(val)","negotiationAgreementTypeId": "(val)","negotiationAssociationTypeId": "(val)","negotiatorPersonId": "(val)","negotiatorName": "(val)","negotiationStartDate": "(val)","negotiationEndDate": "(val)","anticipatedAwardDate": "(val)","documentFolder": "(val)","associatedDocumentId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiations [POST /research-sys/api/v1/negotiations/]

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
            
### Delete Negotiations by Key [DELETE /research-sys/api/v1/negotiations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiations [DELETE /research-sys/api/v1/negotiations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Negotiations with Matching [DELETE /research-sys/api/v1/negotiations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + negotiationId
            + documentNumber
            + negotiationStatusId
            + negotiationAgreementTypeId
            + negotiationAssociationTypeId
            + negotiatorPersonId
            + negotiatorName
            + negotiationStartDate
            + negotiationEndDate
            + anticipatedAwardDate
            + documentFolder
            + associatedDocumentId


+ Response 204
