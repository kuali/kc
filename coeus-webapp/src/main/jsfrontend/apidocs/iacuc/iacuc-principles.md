## Iacuc Principles [/research-sys/api/v1/iacuc-principles/]

### Get Iacuc Principles by Key [GET /research-sys/api/v1/iacuc-principles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Principles [GET /research-sys/api/v1/iacuc-principles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"},
              {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Principles with Filtering [GET /research-sys/api/v1/iacuc-principles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + iacucPrinciplesId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + reduction
            + refinement
            + replacement
            + searchRequired
            + exceptionsPresent
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"},
              {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Principles [GET /research-sys/api/v1/iacuc-principles/]
	 
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
		
### Get Blueprint API specification for Iacuc Principles [GET /research-sys/api/v1/iacuc-principles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Principles.md"
            transfer-encoding:chunked


### Update Iacuc Principles [PUT /research-sys/api/v1/iacuc-principles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Principles [PUT /research-sys/api/v1/iacuc-principles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"},
              {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Principles [POST /research-sys/api/v1/iacuc-principles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Principles [POST /research-sys/api/v1/iacuc-principles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"},
              {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"},
              {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Principles by Key [DELETE /research-sys/api/v1/iacuc-principles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Principles [DELETE /research-sys/api/v1/iacuc-principles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Principles with Matching [DELETE /research-sys/api/v1/iacuc-principles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + iacucPrinciplesId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + reduction
            + refinement
            + replacement
            + searchRequired
            + exceptionsPresent


+ Response 204
