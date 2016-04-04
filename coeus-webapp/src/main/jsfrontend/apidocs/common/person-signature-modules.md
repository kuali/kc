## Person Signature Modules [/research-sys/api/v1/person-signature-modules/]

### Get Person Signature Modules by Key [GET /research-sys/api/v1/person-signature-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}

### Get All Person Signature Modules [GET /research-sys/api/v1/person-signature-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"},
              {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Signature Modules with Filtering [GET /research-sys/api/v1/person-signature-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + personSignatureModuleId
            + personSignatureId
            + defaultSignature
            + signatureActive
            + moduleCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"},
              {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Signature Modules [GET /research-sys/api/v1/person-signature-modules/]
	 
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
		
### Get Blueprint API specification for Person Signature Modules [GET /research-sys/api/v1/person-signature-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Signature Modules.md"
            transfer-encoding:chunked


### Update Person Signature Modules [PUT /research-sys/api/v1/person-signature-modules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Signature Modules [PUT /research-sys/api/v1/person-signature-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"},
              {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Signature Modules [POST /research-sys/api/v1/person-signature-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Signature Modules [POST /research-sys/api/v1/person-signature-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"},
              {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"},
              {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Signature Modules by Key [DELETE /research-sys/api/v1/person-signature-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Signature Modules [DELETE /research-sys/api/v1/person-signature-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Person Signature Modules with Matching [DELETE /research-sys/api/v1/person-signature-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + personSignatureModuleId
            + personSignatureId
            + defaultSignature
            + signatureActive
            + moduleCode


+ Response 204
