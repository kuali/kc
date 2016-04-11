## Fanda Rate Types [/research-sys/api/v1/fanda-rate-types/]

### Get Fanda Rate Types by Key [GET /research-sys/api/v1/fanda-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Fanda Rate Types [GET /research-sys/api/v1/fanda-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Fanda Rate Types with Filtering [GET /research-sys/api/v1/fanda-rate-types/]
    
+ Parameters

        + fandaRateTypeCode
            + description

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Fanda Rate Types [GET /research-sys/api/v1/fanda-rate-types/]
	                                          
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
    
            {"columns":["fandaRateTypeCode","description"],"primaryKey":"fandaRateTypeCode"}
		
### Get Blueprint API specification for Fanda Rate Types [GET /research-sys/api/v1/fanda-rate-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Fanda Rate Types.md"
            transfer-encoding:chunked


### Update Fanda Rate Types [PUT /research-sys/api/v1/fanda-rate-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Fanda Rate Types [PUT /research-sys/api/v1/fanda-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Fanda Rate Types [POST /research-sys/api/v1/fanda-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Fanda Rate Types [POST /research-sys/api/v1/fanda-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Fanda Rate Types by Key [DELETE /research-sys/api/v1/fanda-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Fanda Rate Types [DELETE /research-sys/api/v1/fanda-rate-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Fanda Rate Types with Matching [DELETE /research-sys/api/v1/fanda-rate-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + fandaRateTypeCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
