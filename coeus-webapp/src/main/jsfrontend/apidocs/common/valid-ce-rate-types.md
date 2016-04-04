## Valid Ce Rate Types [/research-sys/api/v1/valid-ce-rate-types/]

### Get Valid Ce Rate Types by Key [GET /research-sys/api/v1/valid-ce-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Valid Ce Rate Types [GET /research-sys/api/v1/valid-ce-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Ce Rate Types with Filtering [GET /research-sys/api/v1/valid-ce-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + costElement
            + rateClassCode
            + rateTypeCode
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Ce Rate Types [GET /research-sys/api/v1/valid-ce-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Valid Ce Rate Types [GET /research-sys/api/v1/valid-ce-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Ce Rate Types.md"
            transfer-encoding:chunked


### Update Valid Ce Rate Types [PUT /research-sys/api/v1/valid-ce-rate-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Ce Rate Types [PUT /research-sys/api/v1/valid-ce-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Valid Ce Rate Types [POST /research-sys/api/v1/valid-ce-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Ce Rate Types [POST /research-sys/api/v1/valid-ce-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Valid Ce Rate Types by Key [DELETE /research-sys/api/v1/valid-ce-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Ce Rate Types [DELETE /research-sys/api/v1/valid-ce-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Valid Ce Rate Types with Matching [DELETE /research-sys/api/v1/valid-ce-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + costElement
            + rateClassCode
            + rateTypeCode
            + active


+ Response 204
