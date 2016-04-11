## Negotiation Person Mass Changes [/research-sys/api/v1/negotiation-person-mass-changes/]

### Get Negotiation Person Mass Changes by Key [GET /research-sys/api/v1/negotiation-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Person Mass Changes [GET /research-sys/api/v1/negotiation-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"},
              {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"}
            ]

### Get All Negotiation Person Mass Changes with Filtering [GET /research-sys/api/v1/negotiation-person-mass-changes/]
    
+ Parameters

        + negotiationPersonMassChangeId
            + personMassChangeId
            + negotiator

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"},
              {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Negotiation Person Mass Changes [GET /research-sys/api/v1/negotiation-person-mass-changes/]
	                                          
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
    
            {"columns":["negotiationPersonMassChangeId","personMassChangeId","negotiator"],"primaryKey":"negotiationPersonMassChangeId"}
		
### Get Blueprint API specification for Negotiation Person Mass Changes [GET /research-sys/api/v1/negotiation-person-mass-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiation Person Mass Changes.md"
            transfer-encoding:chunked


### Update Negotiation Person Mass Changes [PUT /research-sys/api/v1/negotiation-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Person Mass Changes [PUT /research-sys/api/v1/negotiation-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"},
              {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Negotiation Person Mass Changes [POST /research-sys/api/v1/negotiation-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Person Mass Changes [POST /research-sys/api/v1/negotiation-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"},
              {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"},
              {"negotiationPersonMassChangeId": "(val)","personMassChangeId": "(val)","negotiator": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Negotiation Person Mass Changes by Key [DELETE /research-sys/api/v1/negotiation-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Person Mass Changes [DELETE /research-sys/api/v1/negotiation-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Person Mass Changes with Matching [DELETE /research-sys/api/v1/negotiation-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + negotiationPersonMassChangeId
            + personMassChangeId
            + negotiator

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
