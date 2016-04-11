## Cost Share Types [/research-sys/api/v1/cost-share-types/]

### Get Cost Share Types by Key [GET /research-sys/api/v1/cost-share-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Cost Share Types [GET /research-sys/api/v1/cost-share-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Cost Share Types with Filtering [GET /research-sys/api/v1/cost-share-types/]
    
+ Parameters

        + costShareTypeCode
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
              {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Cost Share Types [GET /research-sys/api/v1/cost-share-types/]
	                                          
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
    
            {"columns":["costShareTypeCode","description"],"primaryKey":"costShareTypeCode"}
		
### Get Blueprint API specification for Cost Share Types [GET /research-sys/api/v1/cost-share-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Cost Share Types.md"
            transfer-encoding:chunked


### Update Cost Share Types [PUT /research-sys/api/v1/cost-share-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Cost Share Types [PUT /research-sys/api/v1/cost-share-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Cost Share Types [POST /research-sys/api/v1/cost-share-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Cost Share Types [POST /research-sys/api/v1/cost-share-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"costShareTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Cost Share Types by Key [DELETE /research-sys/api/v1/cost-share-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Cost Share Types [DELETE /research-sys/api/v1/cost-share-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Cost Share Types with Matching [DELETE /research-sys/api/v1/cost-share-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + costShareTypeCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
