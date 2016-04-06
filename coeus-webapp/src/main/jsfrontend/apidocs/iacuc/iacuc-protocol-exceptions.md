## Iacuc Protocol Exceptions [/research-sys/api/v1/iacuc-protocol-exceptions/]

### Get Iacuc Protocol Exceptions by Key [GET /research-sys/api/v1/iacuc-protocol-exceptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Exceptions [GET /research-sys/api/v1/iacuc-protocol-exceptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Exceptions with Filtering [GET /research-sys/api/v1/iacuc-protocol-exceptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + iacucProtocolExceptionId
            + speciesCode
            + protocolId
            + protocolNumber
            + sequenceNumber
            + exceptionId
            + exceptionCategoryCode
            + exceptionDescription
            + exceptionCount
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Exceptions [GET /research-sys/api/v1/iacuc-protocol-exceptions/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Exceptions [GET /research-sys/api/v1/iacuc-protocol-exceptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Exceptions.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Exceptions [PUT /research-sys/api/v1/iacuc-protocol-exceptions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Exceptions [PUT /research-sys/api/v1/iacuc-protocol-exceptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Exceptions [POST /research-sys/api/v1/iacuc-protocol-exceptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Exceptions [POST /research-sys/api/v1/iacuc-protocol-exceptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Exceptions by Key [DELETE /research-sys/api/v1/iacuc-protocol-exceptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Exceptions [DELETE /research-sys/api/v1/iacuc-protocol-exceptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Exceptions with Matching [DELETE /research-sys/api/v1/iacuc-protocol-exceptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + iacucProtocolExceptionId
            + speciesCode
            + protocolId
            + protocolNumber
            + sequenceNumber
            + exceptionId
            + exceptionCategoryCode
            + exceptionDescription
            + exceptionCount


+ Response 204
