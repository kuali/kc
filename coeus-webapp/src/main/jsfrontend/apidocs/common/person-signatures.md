## Person Signatures [/research-sys/api/v1/person-signatures/]

### Get Person Signatures by Key [GET /research-sys/api/v1/person-signatures/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}

### Get All Person Signatures [GET /research-sys/api/v1/person-signatures/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Signatures with Filtering [GET /research-sys/api/v1/person-signatures/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + personSignatureId
            + personId
            + signatureActive
            + attachmentContent
            + fileName
            + contentType
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Signatures [GET /research-sys/api/v1/person-signatures/]
	 
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
		
### Get Blueprint API specification for Person Signatures [GET /research-sys/api/v1/person-signatures/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Signatures.md"
            transfer-encoding:chunked


### Update Person Signatures [PUT /research-sys/api/v1/person-signatures/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Signatures [PUT /research-sys/api/v1/person-signatures/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Signatures [POST /research-sys/api/v1/person-signatures/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Signatures [POST /research-sys/api/v1/person-signatures/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Signatures by Key [DELETE /research-sys/api/v1/person-signatures/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Signatures [DELETE /research-sys/api/v1/person-signatures/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Person Signatures with Matching [DELETE /research-sys/api/v1/person-signatures/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + personSignatureId
            + personId
            + signatureActive
            + attachmentContent
            + fileName
            + contentType


+ Response 204
