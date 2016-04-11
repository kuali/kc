## Protocol References [/research-sys/api/v1/protocol-references/]

### Get Protocol References by Key [GET /research-sys/api/v1/protocol-references/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Protocol References [GET /research-sys/api/v1/protocol-references/]
	 
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

### Get All Protocol References with Filtering [GET /research-sys/api/v1/protocol-references/]
    
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
			
### Get Schema for Protocol References [GET /research-sys/api/v1/protocol-references/]
	                                          
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
		
### Get Blueprint API specification for Protocol References [GET /research-sys/api/v1/protocol-references/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol References.md"
            transfer-encoding:chunked


### Update Protocol References [PUT /research-sys/api/v1/protocol-references/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol References [PUT /research-sys/api/v1/protocol-references/]

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

### Insert Protocol References [POST /research-sys/api/v1/protocol-references/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolReferenceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolReferenceNumber": "(val)","protocolReferenceTypeCode": "(val)","referenceKey": "(val)","applicationDate": "(val)","approvalDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol References [POST /research-sys/api/v1/protocol-references/]

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
            
### Delete Protocol References by Key [DELETE /research-sys/api/v1/protocol-references/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol References [DELETE /research-sys/api/v1/protocol-references/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol References with Matching [DELETE /research-sys/api/v1/protocol-references/]

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

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
