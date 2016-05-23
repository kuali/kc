## Protocol Funding Sources [/irb/api/v1/protocol-funding-sources/]

### Get Protocol Funding Sources by Key [GET /irb/api/v1/protocol-funding-sources/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}

### Get All Protocol Funding Sources [GET /irb/api/v1/protocol-funding-sources/]
	 
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

### Get All Protocol Funding Sources with Filtering [GET /irb/api/v1/protocol-funding-sources/]
    
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
			
### Get Schema for Protocol Funding Sources [GET /irb/api/v1/protocol-funding-sources/]
	                                          
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
		
### Get Blueprint API specification for Protocol Funding Sources [GET /irb/api/v1/protocol-funding-sources/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Funding Sources.md"
            transfer-encoding:chunked
### Update Protocol Funding Sources [PUT /irb/api/v1/protocol-funding-sources/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Funding Sources [PUT /irb/api/v1/protocol-funding-sources/]

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
### Insert Protocol Funding Sources [POST /irb/api/v1/protocol-funding-sources/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolFundingSourceId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","fundingSourceTypeCode": "(val)","fundingSourceNumber": "(val)","fundingSourceName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Funding Sources [POST /irb/api/v1/protocol-funding-sources/]

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
### Delete Protocol Funding Sources by Key [DELETE /irb/api/v1/protocol-funding-sources/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Funding Sources [DELETE /irb/api/v1/protocol-funding-sources/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Funding Sources with Matching [DELETE /irb/api/v1/protocol-funding-sources/]

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
