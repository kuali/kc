## Negotiation Locations [/research-sys/api/v1/negotiation-locations/]

### Get Negotiation Locations by Key [GET /research-sys/api/v1/negotiation-locations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Locations [GET /research-sys/api/v1/negotiation-locations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Negotiation Locations with Filtering [GET /research-sys/api/v1/negotiation-locations/]
    
+ Parameters

        + id
            + code
            + description
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Negotiation Locations [GET /research-sys/api/v1/negotiation-locations/]
	                                          
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
    
            {"columns":["id","code","description","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Negotiation Locations [GET /research-sys/api/v1/negotiation-locations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiation Locations.md"
            transfer-encoding:chunked


### Update Negotiation Locations [PUT /research-sys/api/v1/negotiation-locations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Locations [PUT /research-sys/api/v1/negotiation-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Negotiation Locations [POST /research-sys/api/v1/negotiation-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Locations [POST /research-sys/api/v1/negotiation-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Negotiation Locations by Key [DELETE /research-sys/api/v1/negotiation-locations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Locations [DELETE /research-sys/api/v1/negotiation-locations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Locations with Matching [DELETE /research-sys/api/v1/negotiation-locations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + code
            + description
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
