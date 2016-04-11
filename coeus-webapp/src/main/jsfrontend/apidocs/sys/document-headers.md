## Document Headers [/research-sys/api/v1/document-headers/]

### Get Document Headers by Key [GET /research-sys/api/v1/document-headers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"}

### Get All Document Headers [GET /research-sys/api/v1/document-headers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Headers with Filtering [GET /research-sys/api/v1/document-headers/]
    
+ Parameters

        + documentNumber
            + documentDescription
            + organizationDocumentNumber
            + documentTemplateNumber
            + explanation

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Headers [GET /research-sys/api/v1/document-headers/]
	                                          
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
    
            {"columns":["documentNumber","documentDescription","organizationDocumentNumber","documentTemplateNumber","explanation"],"primaryKey":"documentNumber"}
		
### Get Blueprint API specification for Document Headers [GET /research-sys/api/v1/document-headers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Headers.md"
            transfer-encoding:chunked


### Update Document Headers [PUT /research-sys/api/v1/document-headers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Headers [PUT /research-sys/api/v1/document-headers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Document Headers [POST /research-sys/api/v1/document-headers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Headers [POST /research-sys/api/v1/document-headers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","documentDescription": "(val)","organizationDocumentNumber": "(val)","documentTemplateNumber": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Document Headers by Key [DELETE /research-sys/api/v1/document-headers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Headers [DELETE /research-sys/api/v1/document-headers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Headers with Matching [DELETE /research-sys/api/v1/document-headers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + documentNumber
            + documentDescription
            + organizationDocumentNumber
            + documentTemplateNumber
            + explanation

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
