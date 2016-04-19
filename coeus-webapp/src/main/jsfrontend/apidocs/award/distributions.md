## Distributions [/award/api/v1/distributions/]

### Get Distributions by Key [GET /award/api/v1/distributions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Distributions [GET /award/api/v1/distributions/]
	 
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

### Get All Distributions with Filtering [GET /award/api/v1/distributions/]
    
+ Parameters

    + ospDistributionCode (optional) - OSP File Copy Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + active (optional) - Active. Maximum length is 1.

            
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
			
### Get Schema for Distributions [GET /award/api/v1/distributions/]
	                                          
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
		
### Get Blueprint API specification for Distributions [GET /award/api/v1/distributions/]
	 
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


### Update Distributions [PUT /award/api/v1/distributions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Distributions [PUT /award/api/v1/distributions/]

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

### Insert Distributions [POST /award/api/v1/distributions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"ospDistributionCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Distributions [POST /award/api/v1/distributions/]

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
            
### Delete Distributions by Key [DELETE /award/api/v1/distributions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Distributions [DELETE /award/api/v1/distributions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Distributions with Matching [DELETE /award/api/v1/distributions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + ospDistributionCode (optional) - OSP File Copy Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
