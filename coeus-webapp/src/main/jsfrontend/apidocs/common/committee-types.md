## Committee Types [/research-sys/api/v1/committee-types/]

### Get Committee Types by Key [GET /research-sys/api/v1/committee-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Committee Types [GET /research-sys/api/v1/committee-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committee Types with Filtering [GET /research-sys/api/v1/committee-types/]
    
+ Parameters

        + committeeTypeCode
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
              {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Types [GET /research-sys/api/v1/committee-types/]
	                                          
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
    
            {"columns":["committeeTypeCode","description"],"primaryKey":"committeeTypeCode"}
		
### Get Blueprint API specification for Committee Types [GET /research-sys/api/v1/committee-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Types.md"
            transfer-encoding:chunked


### Update Committee Types [PUT /research-sys/api/v1/committee-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Types [PUT /research-sys/api/v1/committee-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Committee Types [POST /research-sys/api/v1/committee-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Types [POST /research-sys/api/v1/committee-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"committeeTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Committee Types by Key [DELETE /research-sys/api/v1/committee-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Types [DELETE /research-sys/api/v1/committee-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Types with Matching [DELETE /research-sys/api/v1/committee-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + committeeTypeCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
