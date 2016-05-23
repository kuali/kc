## Person Signature Modules [/research-common/api/v1/person-signature-modules/]

### Get Person Signature Modules by Key [GET /research-common/api/v1/person-signature-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}

### Get All Person Signature Modules [GET /research-common/api/v1/person-signature-modules/]
	 
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

### Get All Person Signature Modules with Filtering [GET /research-common/api/v1/person-signature-modules/]
    
+ Parameters

    + personSignatureModuleId (optional) - Person Signature Module ID. Maximum length is 12.
    + personSignatureId (optional) - Person Signature Code. Maximum length is 12.
    + defaultSignature (optional) - Indicate if this is the default module signature. Maximum length is 1.
    + signatureActive (optional) - Indicate if this signature is active for the module. Maximum length is 1.
    + moduleCode (optional) - Module Code. Maximum length is 5.

            
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
			
### Get Schema for Person Signature Modules [GET /research-common/api/v1/person-signature-modules/]
	                                          
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
    
            {"columns":["personSignatureModuleId","personSignatureId","defaultSignature","signatureActive","moduleCode"],"primaryKey":"personSignatureModuleId"}
		
### Get Blueprint API specification for Person Signature Modules [GET /research-common/api/v1/person-signature-modules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Signature Modules.md"
            transfer-encoding:chunked
### Update Person Signature Modules [PUT /research-common/api/v1/person-signature-modules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Signature Modules [PUT /research-common/api/v1/person-signature-modules/]

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
### Insert Person Signature Modules [POST /research-common/api/v1/person-signature-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personSignatureModuleId": "(val)","personSignatureId": "(val)","defaultSignature": "(val)","signatureActive": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Signature Modules [POST /research-common/api/v1/person-signature-modules/]

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
### Delete Person Signature Modules by Key [DELETE /research-common/api/v1/person-signature-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Signature Modules [DELETE /research-common/api/v1/person-signature-modules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Signature Modules with Matching [DELETE /research-common/api/v1/person-signature-modules/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + personSignatureModuleId (optional) - Person Signature Module ID. Maximum length is 12.
    + personSignatureId (optional) - Person Signature Code. Maximum length is 12.
    + defaultSignature (optional) - Indicate if this is the default module signature. Maximum length is 1.
    + signatureActive (optional) - Indicate if this signature is active for the module. Maximum length is 1.
    + moduleCode (optional) - Module Code. Maximum length is 5.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
