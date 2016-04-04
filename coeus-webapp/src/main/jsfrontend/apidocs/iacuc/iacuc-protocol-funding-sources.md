## Iacuc Protocol Funding Sources [/research-sys/api/v1/iacuc-protocol-funding-sources/]

### Get Iacuc Protocol Funding Sources by Key [GET /research-sys/api/v1/iacuc-protocol-funding-sources/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Funding Sources [GET /research-sys/api/v1/iacuc-protocol-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"},
              {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Funding Sources with Filtering [GET /research-sys/api/v1/iacuc-protocol-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolFundingSourceId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + fundingSourceTypeCode
            + fundingSourceNumber
            + fundingSourceName
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"},
              {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Funding Sources [GET /research-sys/api/v1/iacuc-protocol-funding-sources/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Funding Sources [GET /research-sys/api/v1/iacuc-protocol-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Funding Sources.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Funding Sources [PUT /research-sys/api/v1/iacuc-protocol-funding-sources/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Funding Sources [PUT /research-sys/api/v1/iacuc-protocol-funding-sources/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"},
              {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Funding Sources [POST /research-sys/api/v1/iacuc-protocol-funding-sources/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Funding Sources [POST /research-sys/api/v1/iacuc-protocol-funding-sources/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"},
              {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"},
              {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Funding Sources by Key [DELETE /research-sys/api/v1/iacuc-protocol-funding-sources/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Funding Sources [DELETE /research-sys/api/v1/iacuc-protocol-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Funding Sources with Matching [DELETE /research-sys/api/v1/iacuc-protocol-funding-sources/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolFundingSourceId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + fundingSourceTypeCode
            + fundingSourceNumber
            + fundingSourceName


+ Response 204
