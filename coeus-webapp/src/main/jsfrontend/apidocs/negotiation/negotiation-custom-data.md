## Negotiation Custom Data [/research-sys/api/v1/negotiation-custom-data/]

### Get Negotiation Custom Data by Key [GET /research-sys/api/v1/negotiation-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Custom Data [GET /research-sys/api/v1/negotiation-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Negotiation Custom Data with Filtering [GET /research-sys/api/v1/negotiation-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + negotiationCustomDataId
            + negotiationId
            + negotiationNumber
            + customAttributeId
            + value
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Negotiation Custom Data [GET /research-sys/api/v1/negotiation-custom-data/]
	 
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
		
### Get Blueprint API specification for Negotiation Custom Data [GET /research-sys/api/v1/negotiation-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiation Custom Data.md"
            transfer-encoding:chunked


### Update Negotiation Custom Data [PUT /research-sys/api/v1/negotiation-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Custom Data [PUT /research-sys/api/v1/negotiation-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Negotiation Custom Data [POST /research-sys/api/v1/negotiation-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Custom Data [POST /research-sys/api/v1/negotiation-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Negotiation Custom Data by Key [DELETE /research-sys/api/v1/negotiation-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Custom Data [DELETE /research-sys/api/v1/negotiation-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Negotiation Custom Data with Matching [DELETE /research-sys/api/v1/negotiation-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + negotiationCustomDataId
            + negotiationId
            + negotiationNumber
            + customAttributeId
            + value


+ Response 204
