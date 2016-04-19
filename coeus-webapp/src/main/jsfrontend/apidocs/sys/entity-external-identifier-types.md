## Entity External Identifier Types [/research-sys/api/v1/entity-external-identifier-types/]

### Get Entity External Identifier Types by Key [GET /research-sys/api/v1/entity-external-identifier-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}

### Get All Entity External Identifier Types [GET /research-sys/api/v1/entity-external-identifier-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity External Identifier Types with Filtering [GET /research-sys/api/v1/entity-external-identifier-types/]
    
+ Parameters

    + encryptionRequired (optional) - Encryption Required.
    + code (optional) - The external identifier type code. Maximum length is 7.
    + name (optional) - Descriptive text. Maximum length is 50.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + sortCode (optional) - Descriptive text. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity External Identifier Types [GET /research-sys/api/v1/entity-external-identifier-types/]
	                                          
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
    
            {"columns":["encryptionRequired","code","name","active","sortCode"],"primaryKey":"code"}
		
### Get Blueprint API specification for Entity External Identifier Types [GET /research-sys/api/v1/entity-external-identifier-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity External Identifier Types.md"
            transfer-encoding:chunked


### Update Entity External Identifier Types [PUT /research-sys/api/v1/entity-external-identifier-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity External Identifier Types [PUT /research-sys/api/v1/entity-external-identifier-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity External Identifier Types [POST /research-sys/api/v1/entity-external-identifier-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity External Identifier Types [POST /research-sys/api/v1/entity-external-identifier-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"encryptionRequired": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity External Identifier Types by Key [DELETE /research-sys/api/v1/entity-external-identifier-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity External Identifier Types [DELETE /research-sys/api/v1/entity-external-identifier-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity External Identifier Types with Matching [DELETE /research-sys/api/v1/entity-external-identifier-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + encryptionRequired (optional) - Encryption Required.
    + code (optional) - The external identifier type code. Maximum length is 7.
    + name (optional) - Descriptive text. Maximum length is 50.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + sortCode (optional) - Descriptive text. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
