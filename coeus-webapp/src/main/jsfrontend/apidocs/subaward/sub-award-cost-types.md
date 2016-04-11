## Sub Award Cost Types [/research-sys/api/v1/sub-award-cost-types/]

### Get Sub Award Cost Types by Key [GET /research-sys/api/v1/sub-award-cost-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Cost Types [GET /research-sys/api/v1/sub-award-cost-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"},
              {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Cost Types with Filtering [GET /research-sys/api/v1/sub-award-cost-types/]
    
+ Parameters

        + costTypeCode
            + costTypeDescription

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"},
              {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Cost Types [GET /research-sys/api/v1/sub-award-cost-types/]
	                                          
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
    
            {"columns":["costTypeCode","costTypeDescription"],"primaryKey":"costTypeCode"}
		
### Get Blueprint API specification for Sub Award Cost Types [GET /research-sys/api/v1/sub-award-cost-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Cost Types.md"
            transfer-encoding:chunked


### Update Sub Award Cost Types [PUT /research-sys/api/v1/sub-award-cost-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Cost Types [PUT /research-sys/api/v1/sub-award-cost-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"},
              {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Cost Types [POST /research-sys/api/v1/sub-award-cost-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Cost Types [POST /research-sys/api/v1/sub-award-cost-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"},
              {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"},
              {"costTypeCode": "(val)","costTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Cost Types by Key [DELETE /research-sys/api/v1/sub-award-cost-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Cost Types [DELETE /research-sys/api/v1/sub-award-cost-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Cost Types with Matching [DELETE /research-sys/api/v1/sub-award-cost-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + costTypeCode
            + costTypeDescription

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
