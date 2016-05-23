## Iacuc Protocol References [/iacuc/api/v1/iacuc-protocol-references/]

### Get Iacuc Protocol References by Key [GET /iacuc/api/v1/iacuc-protocol-references/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol References [GET /iacuc/api/v1/iacuc-protocol-references/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol References with Filtering [GET /iacuc/api/v1/iacuc-protocol-references/]
    
+ Parameters

    + protocolReferenceId (optional) - Protocol Reference Id. Maximum length is 22.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + protocolReferenceNumber (optional) - Protocol Reference Number. Maximum length is 22.
    + protocolReferenceTypeCode (optional) - Protocol Reference Type Code. Maximum length is 3.
    + referenceKey (optional) - Reference Key. Maximum length is 50.
    + applicationDate (optional) - Application Date. Maximum length is 10.
    + approvalDate (optional) - Approval Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 400.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol References [GET /iacuc/api/v1/iacuc-protocol-references/]
	                                          
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
    
            {"columns":["protocolReferenceId","protocolId","protocolNumber","sequenceNumber","protocolReferenceNumber","protocolReferenceTypeCode","referenceKey","applicationDate","approvalDate","comments"],"primaryKey":"protocolReferenceId"}
		
### Get Blueprint API specification for Iacuc Protocol References [GET /iacuc/api/v1/iacuc-protocol-references/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol References.md"
            transfer-encoding:chunked
### Update Iacuc Protocol References [PUT /iacuc/api/v1/iacuc-protocol-references/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol References [PUT /iacuc/api/v1/iacuc-protocol-references/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol References [POST /iacuc/api/v1/iacuc-protocol-references/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol References [POST /iacuc/api/v1/iacuc-protocol-references/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol References by Key [DELETE /iacuc/api/v1/iacuc-protocol-references/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol References [DELETE /iacuc/api/v1/iacuc-protocol-references/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol References with Matching [DELETE /iacuc/api/v1/iacuc-protocol-references/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolReferenceId (optional) - Protocol Reference Id. Maximum length is 22.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + protocolReferenceNumber (optional) - Protocol Reference Number. Maximum length is 22.
    + protocolReferenceTypeCode (optional) - Protocol Reference Type Code. Maximum length is 3.
    + referenceKey (optional) - Reference Key. Maximum length is 50.
    + applicationDate (optional) - Application Date. Maximum length is 10.
    + approvalDate (optional) - Approval Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 400.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
