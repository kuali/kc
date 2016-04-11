## Maintenance Document Bases [/research-sys/api/v1/maintenance-document-bases/]

### Get Maintenance Document Bases by Key [GET /research-sys/api/v1/maintenance-document-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Maintenance Document Bases [GET /research-sys/api/v1/maintenance-document-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Maintenance Document Bases with Filtering [GET /research-sys/api/v1/maintenance-document-bases/]
    
+ Parameters

        + xmlDocumentContents
            + documentNumber

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Maintenance Document Bases [GET /research-sys/api/v1/maintenance-document-bases/]
	                                          
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
    
            {"columns":["xmlDocumentContents","documentNumber"],"primaryKey":"documentNumber"}
		
### Get Blueprint API specification for Maintenance Document Bases [GET /research-sys/api/v1/maintenance-document-bases/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Maintenance Document Bases.md"
            transfer-encoding:chunked


### Update Maintenance Document Bases [PUT /research-sys/api/v1/maintenance-document-bases/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Maintenance Document Bases [PUT /research-sys/api/v1/maintenance-document-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Maintenance Document Bases [POST /research-sys/api/v1/maintenance-document-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Maintenance Document Bases [POST /research-sys/api/v1/maintenance-document-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"xmlDocumentContents": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Maintenance Document Bases by Key [DELETE /research-sys/api/v1/maintenance-document-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Maintenance Document Bases [DELETE /research-sys/api/v1/maintenance-document-bases/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Maintenance Document Bases with Matching [DELETE /research-sys/api/v1/maintenance-document-bases/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + xmlDocumentContents
            + documentNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
