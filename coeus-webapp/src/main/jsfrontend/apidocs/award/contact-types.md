## Contact Types [/research-sys/api/v1/contact-types/]

### Get Contact Types by Key [GET /research-sys/api/v1/contact-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Contact Types [GET /research-sys/api/v1/contact-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Contact Types with Filtering [GET /research-sys/api/v1/contact-types/]
    
+ Parameters

        + contactTypeCode
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
              {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Contact Types [GET /research-sys/api/v1/contact-types/]
	                                          
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
    
            {"columns":["contactTypeCode","description"],"primaryKey":"contactTypeCode"}
		
### Get Blueprint API specification for Contact Types [GET /research-sys/api/v1/contact-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Contact Types.md"
            transfer-encoding:chunked


### Update Contact Types [PUT /research-sys/api/v1/contact-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Contact Types [PUT /research-sys/api/v1/contact-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Contact Types [POST /research-sys/api/v1/contact-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Contact Types [POST /research-sys/api/v1/contact-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"contactTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Contact Types by Key [DELETE /research-sys/api/v1/contact-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Contact Types [DELETE /research-sys/api/v1/contact-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Contact Types with Matching [DELETE /research-sys/api/v1/contact-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + contactTypeCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
