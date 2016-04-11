## Iacuc Protocol Species [/research-sys/api/v1/iacuc-protocol-species/]

### Get Iacuc Protocol Species by Key [GET /research-sys/api/v1/iacuc-protocol-species/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Species [GET /research-sys/api/v1/iacuc-protocol-species/]
	 
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

### Get All Iacuc Protocol Species with Filtering [GET /research-sys/api/v1/iacuc-protocol-species/]
    
+ Parameters

        + iacucProtocolSpeciesId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + speciesId
            + speciesCode
            + speciesGroup
            + usdaCovered
            + strain
            + speciesCount
            + painCategoryCode
            + speciesCountCode
            + procedureSummary
            + exceptionsPresent

            
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
			
### Get Schema for Iacuc Protocol Species [GET /research-sys/api/v1/iacuc-protocol-species/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Species [GET /research-sys/api/v1/iacuc-protocol-species/]
	 
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


### Update Iacuc Protocol Species [PUT /research-sys/api/v1/iacuc-protocol-species/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Species [PUT /research-sys/api/v1/iacuc-protocol-species/]

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

### Insert Iacuc Protocol Species [POST /research-sys/api/v1/iacuc-protocol-species/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolSpeciesId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","speciesId": "(val)","speciesCode": "(val)","speciesGroup": "(val)","usdaCovered": "(val)","strain": "(val)","speciesCount": "(val)","painCategoryCode": "(val)","speciesCountCode": "(val)","procedureSummary": "(val)","exceptionsPresent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Species [POST /research-sys/api/v1/iacuc-protocol-species/]

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
            
### Delete Iacuc Protocol Species by Key [DELETE /research-sys/api/v1/iacuc-protocol-species/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Species [DELETE /research-sys/api/v1/iacuc-protocol-species/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Species with Matching [DELETE /research-sys/api/v1/iacuc-protocol-species/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + iacucProtocolSpeciesId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + speciesId
            + speciesCode
            + speciesGroup
            + usdaCovered
            + strain
            + speciesCount
            + painCategoryCode
            + speciesCountCode
            + procedureSummary
            + exceptionsPresent

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
