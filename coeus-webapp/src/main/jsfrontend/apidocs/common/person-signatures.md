## Person Signatures [/research-common/api/v1/person-signatures/]

### Get Person Signatures by Key [GET /research-common/api/v1/person-signatures/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}

### Get All Person Signatures [GET /research-common/api/v1/person-signatures/]
	 
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

### Get All Person Signatures with Filtering [GET /research-common/api/v1/person-signatures/]
    
+ Parameters

    + personSignatureId (optional) - Person Signature Code. Maximum length is 12.
    + personId (optional) - KC Person. Maximum length is 40.
    + signatureActive (optional) - Indicate if this signature is active. Maximum length is 1.
    + attachmentContent (optional) - 
    + fileName (optional) - 
    + contentType (optional) - 

            
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
			
### Get Schema for Person Signatures [GET /research-common/api/v1/person-signatures/]
	                                          
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
    
            {"columns":["personSignatureId","personId","signatureActive","attachmentContent","fileName","contentType"],"primaryKey":"personSignatureId"}
		
### Get Blueprint API specification for Person Signatures [GET /research-common/api/v1/person-signatures/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Signatures.md"
            transfer-encoding:chunked


### Update Person Signatures [PUT /research-common/api/v1/person-signatures/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Signatures [PUT /research-common/api/v1/person-signatures/]

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

### Insert Person Signatures [POST /research-common/api/v1/person-signatures/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personSignatureId": "(val)","personId": "(val)","signatureActive": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Signatures [POST /research-common/api/v1/person-signatures/]

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
            
### Delete Person Signatures by Key [DELETE /research-common/api/v1/person-signatures/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Signatures [DELETE /research-common/api/v1/person-signatures/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Signatures with Matching [DELETE /research-common/api/v1/person-signatures/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + personSignatureId (optional) - Person Signature Code. Maximum length is 12.
    + personId (optional) - KC Person. Maximum length is 40.
    + signatureActive (optional) - Indicate if this signature is active. Maximum length is 1.
    + attachmentContent (optional) - 
    + fileName (optional) - 
    + contentType (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
