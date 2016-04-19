## Iacuc Principles [/iacuc/api/v1/iacuc-principles/]

### Get Iacuc Principles by Key [GET /iacuc/api/v1/iacuc-principles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Principles [GET /iacuc/api/v1/iacuc-principles/]
	 
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

### Get All Iacuc Principles with Filtering [GET /iacuc/api/v1/iacuc-principles/]
    
+ Parameters

    + iacucPrinciplesId (optional) - 
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + reduction (optional) - Reduction. Maximum length is 2000.
    + refinement (optional) - Refinement. Maximum length is 2000.
    + replacement (optional) - Replacement. Maximum length is 2000.
    + searchRequired (optional) - Search Required. Maximum length is 1.
    + exceptionsPresent (optional) - 

            
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
			
### Get Schema for Iacuc Principles [GET /iacuc/api/v1/iacuc-principles/]
	                                          
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
    
            {"columns":["iacucPrinciplesId","protocolId","protocolNumber","sequenceNumber","reduction","refinement","replacement","searchRequired","exceptionsPresent"],"primaryKey":"iacucPrinciplesId"}
		
### Get Blueprint API specification for Iacuc Principles [GET /iacuc/api/v1/iacuc-principles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Principles.md"
            transfer-encoding:chunked


### Update Iacuc Principles [PUT /iacuc/api/v1/iacuc-principles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Principles [PUT /iacuc/api/v1/iacuc-principles/]

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

### Insert Iacuc Principles [POST /iacuc/api/v1/iacuc-principles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucPrinciplesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","reduction": "(val)","refinement": "(val)","replacement": "(val)","searchRequired": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Principles [POST /iacuc/api/v1/iacuc-principles/]

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
            
### Delete Iacuc Principles by Key [DELETE /iacuc/api/v1/iacuc-principles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Principles [DELETE /iacuc/api/v1/iacuc-principles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Principles with Matching [DELETE /iacuc/api/v1/iacuc-principles/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucPrinciplesId (optional) - 
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + reduction (optional) - Reduction. Maximum length is 2000.
    + refinement (optional) - Refinement. Maximum length is 2000.
    + replacement (optional) - Replacement. Maximum length is 2000.
    + searchRequired (optional) - Search Required. Maximum length is 1.
    + exceptionsPresent (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
