## Sub Award Copyright Types [/research-sys/api/v1/sub-award-copyright-types/]

### Get Sub Award Copyright Types by Key [GET /research-sys/api/v1/sub-award-copyright-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Copyright Types [GET /research-sys/api/v1/sub-award-copyright-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"},
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Copyright Types with Filtering [GET /research-sys/api/v1/sub-award-copyright-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + copyRightTypeCode
            + copyRightTypeDescription
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"},
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Copyright Types [GET /research-sys/api/v1/sub-award-copyright-types/]
	 
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
		
### Get Blueprint API specification for Sub Award Copyright Types [GET /research-sys/api/v1/sub-award-copyright-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Copyright Types.md"
            transfer-encoding:chunked


### Update Sub Award Copyright Types [PUT /research-sys/api/v1/sub-award-copyright-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Copyright Types [PUT /research-sys/api/v1/sub-award-copyright-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"},
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Copyright Types [POST /research-sys/api/v1/sub-award-copyright-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Copyright Types [POST /research-sys/api/v1/sub-award-copyright-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"},
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"},
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Copyright Types by Key [DELETE /research-sys/api/v1/sub-award-copyright-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Copyright Types [DELETE /research-sys/api/v1/sub-award-copyright-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sub Award Copyright Types with Matching [DELETE /research-sys/api/v1/sub-award-copyright-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + copyRightTypeCode
            + copyRightTypeDescription


+ Response 204
