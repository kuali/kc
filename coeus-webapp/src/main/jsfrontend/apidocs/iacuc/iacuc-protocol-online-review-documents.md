## Iacuc Protocol Online Review Documents [/research-sys/api/v1/iacuc-protocol-online-review-documents/]

### Get Iacuc Protocol Online Review Documents by Key [GET /research-sys/api/v1/iacuc-protocol-online-review-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Online Review Documents [GET /research-sys/api/v1/iacuc-protocol-online-review-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Online Review Documents with Filtering [GET /research-sys/api/v1/iacuc-protocol-online-review-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + documentNumber
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Online Review Documents [GET /research-sys/api/v1/iacuc-protocol-online-review-documents/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Online Review Documents [GET /research-sys/api/v1/iacuc-protocol-online-review-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Online Review Documents.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Online Review Documents [PUT /research-sys/api/v1/iacuc-protocol-online-review-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Online Review Documents [PUT /research-sys/api/v1/iacuc-protocol-online-review-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Online Review Documents [POST /research-sys/api/v1/iacuc-protocol-online-review-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Online Review Documents [POST /research-sys/api/v1/iacuc-protocol-online-review-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentNumber": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Online Review Documents by Key [DELETE /research-sys/api/v1/iacuc-protocol-online-review-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Online Review Documents [DELETE /research-sys/api/v1/iacuc-protocol-online-review-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Online Review Documents with Matching [DELETE /research-sys/api/v1/iacuc-protocol-online-review-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + documentNumber


+ Response 204
