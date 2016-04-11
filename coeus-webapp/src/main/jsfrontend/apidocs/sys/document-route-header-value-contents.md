## Document Route Header Value Contents [/research-sys/api/v1/document-route-header-value-contents/]

### Get Document Route Header Value Contents by Key [GET /research-sys/api/v1/document-route-header-value-contents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"}

### Get All Document Route Header Value Contents [GET /research-sys/api/v1/document-route-header-value-contents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"},
              {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Route Header Value Contents with Filtering [GET /research-sys/api/v1/document-route-header-value-contents/]
    
+ Parameters

        + documentId
            + documentContent

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"},
              {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Route Header Value Contents [GET /research-sys/api/v1/document-route-header-value-contents/]
	                                          
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
    
            {"columns":["documentId","documentContent"],"primaryKey":"documentId"}
		
### Get Blueprint API specification for Document Route Header Value Contents [GET /research-sys/api/v1/document-route-header-value-contents/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Route Header Value Contents.md"
            transfer-encoding:chunked


### Update Document Route Header Value Contents [PUT /research-sys/api/v1/document-route-header-value-contents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Route Header Value Contents [PUT /research-sys/api/v1/document-route-header-value-contents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"},
              {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Document Route Header Value Contents [POST /research-sys/api/v1/document-route-header-value-contents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Route Header Value Contents [POST /research-sys/api/v1/document-route-header-value-contents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"},
              {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"},
              {"documentId": "(val)","documentContent": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Document Route Header Value Contents by Key [DELETE /research-sys/api/v1/document-route-header-value-contents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Route Header Value Contents [DELETE /research-sys/api/v1/document-route-header-value-contents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Route Header Value Contents with Matching [DELETE /research-sys/api/v1/document-route-header-value-contents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + documentId
            + documentContent

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
