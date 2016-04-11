## Negotiation Agreement Types [/research-sys/api/v1/negotiation-agreement-types/]

### Get Negotiation Agreement Types by Key [GET /research-sys/api/v1/negotiation-agreement-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Agreement Types [GET /research-sys/api/v1/negotiation-agreement-types/]
	 
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

### Get All Negotiation Agreement Types with Filtering [GET /research-sys/api/v1/negotiation-agreement-types/]
    
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
			
### Get Schema for Negotiation Agreement Types [GET /research-sys/api/v1/negotiation-agreement-types/]
	                                          
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
		
### Get Blueprint API specification for Negotiation Agreement Types [GET /research-sys/api/v1/negotiation-agreement-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiation Agreement Types.md"
            transfer-encoding:chunked


### Update Negotiation Agreement Types [PUT /research-sys/api/v1/negotiation-agreement-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Agreement Types [PUT /research-sys/api/v1/negotiation-agreement-types/]

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

### Insert Negotiation Agreement Types [POST /research-sys/api/v1/negotiation-agreement-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Agreement Types [POST /research-sys/api/v1/negotiation-agreement-types/]

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
            
### Delete Negotiation Agreement Types by Key [DELETE /research-sys/api/v1/negotiation-agreement-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Agreement Types [DELETE /research-sys/api/v1/negotiation-agreement-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Agreement Types with Matching [DELETE /research-sys/api/v1/negotiation-agreement-types/]

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
