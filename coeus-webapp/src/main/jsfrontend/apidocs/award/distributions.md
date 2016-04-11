## Distributions [/research-sys/api/v1/distributions/]

### Get Distributions by Key [GET /research-sys/api/v1/distributions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Distributions [GET /research-sys/api/v1/distributions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Distributions with Filtering [GET /research-sys/api/v1/distributions/]
    
+ Parameters

        + ospDistributionCode
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
              {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Distributions [GET /research-sys/api/v1/distributions/]
	                                          
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
    
            {"columns":["ospDistributionCode","description","active"],"primaryKey":"ospDistributionCode"}
		
### Get Blueprint API specification for Distributions [GET /research-sys/api/v1/distributions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Distributions.md"
            transfer-encoding:chunked


### Update Distributions [PUT /research-sys/api/v1/distributions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Distributions [PUT /research-sys/api/v1/distributions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Distributions [POST /research-sys/api/v1/distributions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Distributions [POST /research-sys/api/v1/distributions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Distributions by Key [DELETE /research-sys/api/v1/distributions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Distributions [DELETE /research-sys/api/v1/distributions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Distributions with Matching [DELETE /research-sys/api/v1/distributions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + ospDistributionCode
            + description
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
