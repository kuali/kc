## Iacuc Protocol Funding Sources [/iacuc/api/v1/iacuc-protocol-funding-sources/]

### Get Iacuc Protocol Funding Sources by Key [GET /iacuc/api/v1/iacuc-protocol-funding-sources/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Funding Sources [GET /iacuc/api/v1/iacuc-protocol-funding-sources/]
	 
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

### Get All Iacuc Protocol Funding Sources with Filtering [GET /iacuc/api/v1/iacuc-protocol-funding-sources/]
    
+ Parameters

    + protocolFundingSourceId (optional) - Protocol Funding Source Id. Maximum length is 12.
    + protocolId (optional) - Protocol Id. Maximum length is 12.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + fundingSourceTypeCode (optional) - Funding Source Type Code. Maximum length is 22.
    + fundingSourceNumber (optional) - Funding Source Number. Maximum length is 200.
    + fundingSourceName (optional) - Funding Source Name. Maximum length is 200.

            
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
			
### Get Schema for Iacuc Protocol Funding Sources [GET /iacuc/api/v1/iacuc-protocol-funding-sources/]
	                                          
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
    
            {"columns":["protocolFundingSourceId","protocolId","protocolNumber","sequenceNumber","fundingSourceTypeCode","fundingSourceNumber","fundingSourceName"],"primaryKey":"protocolFundingSourceId"}
		
### Get Blueprint API specification for Iacuc Protocol Funding Sources [GET /iacuc/api/v1/iacuc-protocol-funding-sources/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Funding Sources.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Funding Sources [PUT /iacuc/api/v1/iacuc-protocol-funding-sources/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Funding Sources [PUT /iacuc/api/v1/iacuc-protocol-funding-sources/]

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
### Insert Iacuc Protocol Funding Sources [POST /iacuc/api/v1/iacuc-protocol-funding-sources/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Funding Sources [POST /iacuc/api/v1/iacuc-protocol-funding-sources/]

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
### Delete Iacuc Protocol Funding Sources by Key [DELETE /iacuc/api/v1/iacuc-protocol-funding-sources/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Funding Sources [DELETE /iacuc/api/v1/iacuc-protocol-funding-sources/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Funding Sources with Matching [DELETE /iacuc/api/v1/iacuc-protocol-funding-sources/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolFundingSourceId (optional) - Protocol Funding Source Id. Maximum length is 12.
    + protocolId (optional) - Protocol Id. Maximum length is 12.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + fundingSourceTypeCode (optional) - Funding Source Type Code. Maximum length is 22.
    + fundingSourceNumber (optional) - Funding Source Number. Maximum length is 200.
    + fundingSourceName (optional) - Funding Source Name. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
