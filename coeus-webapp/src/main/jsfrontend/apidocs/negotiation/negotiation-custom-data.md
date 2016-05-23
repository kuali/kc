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
    
            {"negotiationCustomDataId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

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
              {"negotiationCustomDataId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"negotiationCustomDataId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Negotiation Custom Data with Filtering [GET /negotiation/api/v1/negotiation-custom-data/]
    
+ Parameters

    + negotiationCustomDataId (optional) - 
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
              {"negotiationCustomDataId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"negotiationCustomDataId": "(val)","negotiationNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
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
    
            {"columns":["negotiationCustomDataId","negotiationNumber","customAttributeId","value"],"primaryKey":"negotiationCustomDataId"}
		
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
