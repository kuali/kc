## Iacuc Protocol References [/research-sys/api/v1/iacuc-protocol-references/]

### Get Iacuc Protocol References by Key [GET /research-sys/api/v1/iacuc-protocol-references/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol References [GET /research-sys/api/v1/iacuc-protocol-references/]
	 
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

### Get All Iacuc Protocol References with Filtering [GET /research-sys/api/v1/iacuc-protocol-references/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolReferenceId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + protocolReferenceNumber
            + protocolReferenceTypeCode
            + referenceKey
            + applicationDate
            + approvalDate
            + comments
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol References [GET /research-sys/api/v1/iacuc-protocol-references/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol References [GET /research-sys/api/v1/iacuc-protocol-references/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol References.md"
            transfer-encoding:chunked


### Update Iacuc Protocol References [PUT /research-sys/api/v1/iacuc-protocol-references/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol References [PUT /research-sys/api/v1/iacuc-protocol-references/]

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

### Insert Iacuc Protocol References [POST /research-sys/api/v1/iacuc-protocol-references/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol References [POST /research-sys/api/v1/iacuc-protocol-references/]

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
            
### Delete Iacuc Protocol References by Key [DELETE /research-sys/api/v1/iacuc-protocol-references/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol References [DELETE /research-sys/api/v1/iacuc-protocol-references/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol References with Matching [DELETE /research-sys/api/v1/iacuc-protocol-references/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolReferenceId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + protocolReferenceNumber
            + protocolReferenceTypeCode
            + referenceKey
            + applicationDate
            + approvalDate
            + comments


+ Response 204
