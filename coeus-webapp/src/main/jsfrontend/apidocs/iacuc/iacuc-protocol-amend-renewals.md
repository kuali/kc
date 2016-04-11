## Iacuc Protocol Amend Renewals [/research-sys/api/v1/iacuc-protocol-amend-renewals/]

### Get Iacuc Protocol Amend Renewals by Key [GET /research-sys/api/v1/iacuc-protocol-amend-renewals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Amend Renewals [GET /research-sys/api/v1/iacuc-protocol-amend-renewals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Amend Renewals with Filtering [GET /research-sys/api/v1/iacuc-protocol-amend-renewals/]
    
+ Parameters

        + id
            + protoAmendRenNumber
            + dateCreated
            + summary
            + protocolId
            + protocolNumber
            + sequenceNumber

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Amend Renewals [GET /research-sys/api/v1/iacuc-protocol-amend-renewals/]
	                                          
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
    
            {"columns":["id","protoAmendRenNumber","dateCreated","summary","protocolId","protocolNumber","sequenceNumber"],"primaryKey":"id"}
		
### Get Blueprint API specification for Iacuc Protocol Amend Renewals [GET /research-sys/api/v1/iacuc-protocol-amend-renewals/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Amend Renewals.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Amend Renewals [PUT /research-sys/api/v1/iacuc-protocol-amend-renewals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Amend Renewals [PUT /research-sys/api/v1/iacuc-protocol-amend-renewals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Amend Renewals [POST /research-sys/api/v1/iacuc-protocol-amend-renewals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Amend Renewals [POST /research-sys/api/v1/iacuc-protocol-amend-renewals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protoAmendRenNumber": "(val)","dateCreated": "(val)","summary": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Amend Renewals by Key [DELETE /research-sys/api/v1/iacuc-protocol-amend-renewals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Amend Renewals [DELETE /research-sys/api/v1/iacuc-protocol-amend-renewals/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Amend Renewals with Matching [DELETE /research-sys/api/v1/iacuc-protocol-amend-renewals/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + protoAmendRenNumber
            + dateCreated
            + summary
            + protocolId
            + protocolNumber
            + sequenceNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
