## Indirectcost Rate Types [/instprop/api/v1/indirectcost-rate-types/]

### Get Indirectcost Rate Types by Key [GET /instprop/api/v1/indirectcost-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Indirectcost Rate Types [GET /instprop/api/v1/indirectcost-rate-types/]
	 
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

### Get All Indirectcost Rate Types with Filtering [GET /instprop/api/v1/indirectcost-rate-types/]
    
+ Parameters

    + indirectcostRateTypeCode (optional) - Indirectcost Rate Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.

            
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
			
### Get Schema for Indirectcost Rate Types [GET /instprop/api/v1/indirectcost-rate-types/]
	                                          
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
    
            {"columns":["indirectcostRateTypeCode","description"],"primaryKey":"indirectcostRateTypeCode"}
		
### Get Blueprint API specification for Indirectcost Rate Types [GET /instprop/api/v1/indirectcost-rate-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Indirectcost Rate Types.md"
            transfer-encoding:chunked
### Update Indirectcost Rate Types [PUT /instprop/api/v1/indirectcost-rate-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Indirectcost Rate Types [PUT /instprop/api/v1/indirectcost-rate-types/]

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
### Insert Indirectcost Rate Types [POST /instprop/api/v1/indirectcost-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"indirectcostRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Indirectcost Rate Types [POST /instprop/api/v1/indirectcost-rate-types/]

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
### Delete Indirectcost Rate Types by Key [DELETE /instprop/api/v1/indirectcost-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Indirectcost Rate Types [DELETE /instprop/api/v1/indirectcost-rate-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Indirectcost Rate Types with Matching [DELETE /instprop/api/v1/indirectcost-rate-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + indirectcostRateTypeCode (optional) - Indirectcost Rate Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
