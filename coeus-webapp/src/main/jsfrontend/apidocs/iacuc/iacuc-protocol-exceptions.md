## Iacuc Protocol Exceptions [/iacuc/api/v1/iacuc-protocol-exceptions/]

### Get Iacuc Protocol Exceptions by Key [GET /iacuc/api/v1/iacuc-protocol-exceptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Exceptions [GET /iacuc/api/v1/iacuc-protocol-exceptions/]
	 
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

### Get All Iacuc Protocol Exceptions with Filtering [GET /iacuc/api/v1/iacuc-protocol-exceptions/]
    
+ Parameters

    + iacucProtocolExceptionId (optional) - Protocol Exception Id. Maximum length is 22.
    + speciesCode (optional) - Species. Maximum length is 50.
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + exceptionId (optional) - Exception Id. Maximum length is 22.
    + exceptionCategoryCode (optional) - Exception Category. Maximum length is 50.
    + exceptionDescription (optional) - Exception Justification and Description. Maximum length is 1000.
    + exceptionCount (optional) - Exception Count. Maximum length is 8.

            
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
			
### Get Schema for Iacuc Protocol Exceptions [GET /iacuc/api/v1/iacuc-protocol-exceptions/]
	                                          
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
    
            {"columns":["iacucProtocolExceptionId","speciesCode","protocolId","protocolNumber","sequenceNumber","exceptionId","exceptionCategoryCode","exceptionDescription","exceptionCount"],"primaryKey":"iacucProtocolExceptionId"}
		
### Get Blueprint API specification for Iacuc Protocol Exceptions [GET /iacuc/api/v1/iacuc-protocol-exceptions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Exceptions.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Exceptions [PUT /iacuc/api/v1/iacuc-protocol-exceptions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Exceptions [PUT /iacuc/api/v1/iacuc-protocol-exceptions/]

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
### Insert Iacuc Protocol Exceptions [POST /iacuc/api/v1/iacuc-protocol-exceptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolExceptionId": "(val)","speciesCode": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","exceptionId": "(val)","exceptionCategoryCode": "(val)","exceptionDescription": "(val)","exceptionCount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Exceptions [POST /iacuc/api/v1/iacuc-protocol-exceptions/]

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
### Delete Iacuc Protocol Exceptions by Key [DELETE /iacuc/api/v1/iacuc-protocol-exceptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Exceptions [DELETE /iacuc/api/v1/iacuc-protocol-exceptions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Exceptions with Matching [DELETE /iacuc/api/v1/iacuc-protocol-exceptions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucProtocolExceptionId (optional) - Protocol Exception Id. Maximum length is 22.
    + speciesCode (optional) - Species. Maximum length is 50.
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + exceptionId (optional) - Exception Id. Maximum length is 22.
    + exceptionCategoryCode (optional) - Exception Category. Maximum length is 50.
    + exceptionDescription (optional) - Exception Justification and Description. Maximum length is 1000.
    + exceptionCount (optional) - Exception Count. Maximum length is 8.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
