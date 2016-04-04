## Derived Components [/research-sys/api/v1/derived-components/]

### Get Derived Components by Key [GET /research-sys/api/v1/derived-components/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"}

### Get All Derived Components [GET /research-sys/api/v1/derived-components/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Derived Components with Filtering [GET /research-sys/api/v1/derived-components/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + namespaceCode
            + code
            + name
            + componentSetId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Derived Components [GET /research-sys/api/v1/derived-components/]
	 
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
		
### Get Blueprint API specification for Derived Components [GET /research-sys/api/v1/derived-components/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Derived Components.md"
            transfer-encoding:chunked


### Update Derived Components [PUT /research-sys/api/v1/derived-components/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Derived Components [PUT /research-sys/api/v1/derived-components/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Derived Components [POST /research-sys/api/v1/derived-components/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Derived Components [POST /research-sys/api/v1/derived-components/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","componentSetId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Derived Components by Key [DELETE /research-sys/api/v1/derived-components/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Derived Components [DELETE /research-sys/api/v1/derived-components/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Derived Components with Matching [DELETE /research-sys/api/v1/derived-components/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + namespaceCode
            + code
            + name
            + componentSetId


+ Response 204
