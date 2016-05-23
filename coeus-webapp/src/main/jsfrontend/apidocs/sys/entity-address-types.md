## Entity Address Types [/research-sys/api/v1/entity-address-types/]

### Get Entity Address Types by Key [GET /research-sys/api/v1/entity-address-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Address Types [GET /research-sys/api/v1/entity-address-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Address Types with Filtering [GET /research-sys/api/v1/entity-address-types/]
    
+ Parameters

    + name (optional) - Descriptive text. Maximum length is 10.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + code (optional) - The address type code. Maximum length is 2.
    + sortCode (optional) - Descriptive text. Maximum length is 10.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Address Types [GET /research-sys/api/v1/entity-address-types/]
	                                          
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
    
            {"columns":["name","active","code","sortCode"],"primaryKey":"code"}
		
### Get Blueprint API specification for Entity Address Types [GET /research-sys/api/v1/entity-address-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Address Types.md"
            transfer-encoding:chunked
### Update Entity Address Types [PUT /research-sys/api/v1/entity-address-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Address Types [PUT /research-sys/api/v1/entity-address-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Entity Address Types [POST /research-sys/api/v1/entity-address-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Address Types [POST /research-sys/api/v1/entity-address-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"name": "(val)","active": "(val)","code": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Entity Address Types by Key [DELETE /research-sys/api/v1/entity-address-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Address Types [DELETE /research-sys/api/v1/entity-address-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Address Types with Matching [DELETE /research-sys/api/v1/entity-address-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + name (optional) - Descriptive text. Maximum length is 10.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + code (optional) - The address type code. Maximum length is 2.
    + sortCode (optional) - Descriptive text. Maximum length is 10.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
