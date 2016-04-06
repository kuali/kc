## Indirectcost Rate Types [/research-sys/api/v1/indirectcost-rate-types/]

### Get Indirectcost Rate Types by Key [GET /research-sys/api/v1/indirectcost-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Indirectcost Rate Types [GET /research-sys/api/v1/indirectcost-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Indirectcost Rate Types with Filtering [GET /research-sys/api/v1/indirectcost-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + indirectcostRateTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Indirectcost Rate Types [GET /research-sys/api/v1/indirectcost-rate-types/]
	 
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
		
### Get Blueprint API specification for Indirectcost Rate Types [GET /research-sys/api/v1/indirectcost-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Indirectcost Rate Types.md"
            transfer-encoding:chunked


### Update Indirectcost Rate Types [PUT /research-sys/api/v1/indirectcost-rate-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Indirectcost Rate Types [PUT /research-sys/api/v1/indirectcost-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Indirectcost Rate Types [POST /research-sys/api/v1/indirectcost-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Indirectcost Rate Types [POST /research-sys/api/v1/indirectcost-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Indirectcost Rate Types by Key [DELETE /research-sys/api/v1/indirectcost-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Indirectcost Rate Types [DELETE /research-sys/api/v1/indirectcost-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Indirectcost Rate Types with Matching [DELETE /research-sys/api/v1/indirectcost-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + indirectcostRateTypeCode
            + description


+ Response 204
