## Document Accesses [/research-sys/api/v1/document-accesses/]

### Get Document Accesses by Key [GET /research-sys/api/v1/document-accesses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}

### Get All Document Accesses [GET /research-sys/api/v1/document-accesses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Accesses with Filtering [GET /research-sys/api/v1/document-accesses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + documentNumber
            + principalId
            + roleName
            + namespaceCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Accesses [GET /research-sys/api/v1/document-accesses/]
	 
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
		
### Get Blueprint API specification for Document Accesses [GET /research-sys/api/v1/document-accesses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Accesses.md"
            transfer-encoding:chunked


### Update Document Accesses [PUT /research-sys/api/v1/document-accesses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Accesses [PUT /research-sys/api/v1/document-accesses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Document Accesses [POST /research-sys/api/v1/document-accesses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Accesses [POST /research-sys/api/v1/document-accesses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Document Accesses by Key [DELETE /research-sys/api/v1/document-accesses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Accesses [DELETE /research-sys/api/v1/document-accesses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Document Accesses with Matching [DELETE /research-sys/api/v1/document-accesses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + documentNumber
            + principalId
            + roleName
            + namespaceCode


+ Response 204
