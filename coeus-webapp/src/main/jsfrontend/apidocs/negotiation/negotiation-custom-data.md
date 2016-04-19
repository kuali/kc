## Negotiation Custom Data [/negotiation/api/v1/negotiation-custom-data/]

### Get Negotiation Custom Data by Key [GET /negotiation/api/v1/negotiation-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Custom Data [GET /negotiation/api/v1/negotiation-custom-data/]
	 
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

### Get All Negotiation Custom Data with Filtering [GET /negotiation/api/v1/negotiation-custom-data/]
    
+ Parameters

    + negotiationCustomDataId (optional) - 
    + negotiationId (optional) - 
    + negotiationNumber (optional) - 
    + customAttributeId (optional) - 
    + value (optional) - Value. Maximum length is 2000.

            
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
			
### Get Schema for Negotiation Custom Data [GET /negotiation/api/v1/negotiation-custom-data/]
	                                          
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
    
            {"columns":["negotiationCustomDataId","negotiationId","negotiationNumber","customAttributeId","value"],"primaryKey":"negotiationCustomDataId"}
		
### Get Blueprint API specification for Negotiation Custom Data [GET /negotiation/api/v1/negotiation-custom-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiation Custom Data.md"
            transfer-encoding:chunked


### Update Negotiation Custom Data [PUT /negotiation/api/v1/negotiation-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Custom Data [PUT /negotiation/api/v1/negotiation-custom-data/]

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

### Insert Negotiation Custom Data [POST /negotiation/api/v1/negotiation-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"negotiationCustomDataId": "(val)","negotiationId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Custom Data [POST /negotiation/api/v1/negotiation-custom-data/]

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
            
### Delete Negotiation Custom Data by Key [DELETE /negotiation/api/v1/negotiation-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Custom Data [DELETE /negotiation/api/v1/negotiation-custom-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Custom Data with Matching [DELETE /negotiation/api/v1/negotiation-custom-data/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + negotiationCustomDataId (optional) - 
    + negotiationId (optional) - 
    + negotiationNumber (optional) - 
    + customAttributeId (optional) - 
    + value (optional) - Value. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
