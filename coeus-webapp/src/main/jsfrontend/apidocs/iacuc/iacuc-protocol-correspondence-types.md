## Iacuc Protocol Correspondence Types [/research-sys/api/v1/iacuc-protocol-correspondence-types/]

### Get Iacuc Protocol Correspondence Types by Key [GET /research-sys/api/v1/iacuc-protocol-correspondence-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Correspondence Types [GET /research-sys/api/v1/iacuc-protocol-correspondence-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Correspondence Types with Filtering [GET /research-sys/api/v1/iacuc-protocol-correspondence-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protoCorrespTypeCode
            + description
            + moduleId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Correspondence Types [GET /research-sys/api/v1/iacuc-protocol-correspondence-types/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Correspondence Types [GET /research-sys/api/v1/iacuc-protocol-correspondence-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Correspondence Types.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Correspondence Types [PUT /research-sys/api/v1/iacuc-protocol-correspondence-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Correspondence Types [PUT /research-sys/api/v1/iacuc-protocol-correspondence-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Correspondence Types [POST /research-sys/api/v1/iacuc-protocol-correspondence-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Correspondence Types [POST /research-sys/api/v1/iacuc-protocol-correspondence-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Correspondence Types by Key [DELETE /research-sys/api/v1/iacuc-protocol-correspondence-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Correspondence Types [DELETE /research-sys/api/v1/iacuc-protocol-correspondence-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Correspondence Types with Matching [DELETE /research-sys/api/v1/iacuc-protocol-correspondence-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protoCorrespTypeCode
            + description
            + moduleId


+ Response 204
