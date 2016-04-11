## Funding Source Types [/research-sys/api/v1/funding-source-types/]

### Get Funding Source Types by Key [GET /research-sys/api/v1/funding-source-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"}

### Get All Funding Source Types [GET /research-sys/api/v1/funding-source-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"},
              {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Funding Source Types with Filtering [GET /research-sys/api/v1/funding-source-types/]
    
+ Parameters

        + fundingSourceTypeCode
            + description
            + fundingSourceTypeFlag

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"},
              {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Funding Source Types [GET /research-sys/api/v1/funding-source-types/]
	                                          
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
    
            {"columns":["fundingSourceTypeCode","description","fundingSourceTypeFlag"],"primaryKey":"fundingSourceTypeCode"}
		
### Get Blueprint API specification for Funding Source Types [GET /research-sys/api/v1/funding-source-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Funding Source Types.md"
            transfer-encoding:chunked


### Update Funding Source Types [PUT /research-sys/api/v1/funding-source-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Funding Source Types [PUT /research-sys/api/v1/funding-source-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"},
              {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Funding Source Types [POST /research-sys/api/v1/funding-source-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Funding Source Types [POST /research-sys/api/v1/funding-source-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"},
              {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"},
              {"fundingSourceTypeCode": "(val)","description": "(val)","fundingSourceTypeFlag": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Funding Source Types by Key [DELETE /research-sys/api/v1/funding-source-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Funding Source Types [DELETE /research-sys/api/v1/funding-source-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Funding Source Types with Matching [DELETE /research-sys/api/v1/funding-source-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + fundingSourceTypeCode
            + description
            + fundingSourceTypeFlag

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
