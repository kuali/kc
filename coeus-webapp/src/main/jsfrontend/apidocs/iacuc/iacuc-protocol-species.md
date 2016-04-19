## Iacuc Protocol Species [/iacuc/api/v1/iacuc-protocol-species/]

### Get Iacuc Protocol Species by Key [GET /iacuc/api/v1/iacuc-protocol-species/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Species [GET /iacuc/api/v1/iacuc-protocol-species/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Species with Filtering [GET /iacuc/api/v1/iacuc-protocol-species/]
    
+ Parameters

    + iacucProtocolSpeciesId (optional) - Protocol Species Id. Maximum length is 22.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + speciesId (optional) - Species Id. Maximum length is 22.
    + speciesCode (optional) - Species Code. Maximum length is 50.
    + speciesGroup (optional) - Species Group. Maximum length is 50.
    + usdaCovered (optional) - USDA Covered Type. Maximum length is 3.
    + strain (optional) - Species Strain. Maximum length is 30.
    + speciesCount (optional) - Species Count. Maximum length is 8.
    + painCategoryCode (optional) - Pain Category. Maximum length is 50.
    + speciesCountCode (optional) - Count Type. Maximum length is 50.
    + procedureSummary (optional) - Procedure Summary. Maximum length is 2000.
    + exceptionsPresent (optional) - Exceptions. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Species [GET /iacuc/api/v1/iacuc-protocol-species/]
	                                          
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
    
            {"columns":["iacucProtocolSpeciesId","protocolId","protocolNumber","sequenceNumber","speciesId","speciesCode","speciesGroup","usdaCovered","strain","speciesCount","painCategoryCode","speciesCountCode","procedureSummary","exceptionsPresent"],"primaryKey":"iacucProtocolSpeciesId"}
		
### Get Blueprint API specification for Iacuc Protocol Species [GET /iacuc/api/v1/iacuc-protocol-species/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Species.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Species [PUT /iacuc/api/v1/iacuc-protocol-species/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Species [PUT /iacuc/api/v1/iacuc-protocol-species/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Species [POST /iacuc/api/v1/iacuc-protocol-species/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Species [POST /iacuc/api/v1/iacuc-protocol-species/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Species by Key [DELETE /iacuc/api/v1/iacuc-protocol-species/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Species [DELETE /iacuc/api/v1/iacuc-protocol-species/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Species with Matching [DELETE /iacuc/api/v1/iacuc-protocol-species/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucProtocolSpeciesId (optional) - Protocol Species Id. Maximum length is 22.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + speciesId (optional) - Species Id. Maximum length is 22.
    + speciesCode (optional) - Species Code. Maximum length is 50.
    + speciesGroup (optional) - Species Group. Maximum length is 50.
    + usdaCovered (optional) - USDA Covered Type. Maximum length is 3.
    + strain (optional) - Species Strain. Maximum length is 30.
    + speciesCount (optional) - Species Count. Maximum length is 8.
    + painCategoryCode (optional) - Pain Category. Maximum length is 50.
    + speciesCountCode (optional) - Count Type. Maximum length is 50.
    + procedureSummary (optional) - Procedure Summary. Maximum length is 2000.
    + exceptionsPresent (optional) - Exceptions. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
