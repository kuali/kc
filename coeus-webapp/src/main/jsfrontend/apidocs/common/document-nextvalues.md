## Document Nextvalues [/research-sys/api/v1/document-nextvalues/]

### Get Document Nextvalues by Key [GET /research-sys/api/v1/document-nextvalues/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}

### Get All Document Nextvalues [GET /research-sys/api/v1/document-nextvalues/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"},
              {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Nextvalues with Filtering [GET /research-sys/api/v1/document-nextvalues/]
    
+ Parameters

        + propertyName
            + documentKey
            + nextValue

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"},
              {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Nextvalues [GET /research-sys/api/v1/document-nextvalues/]
	                                          
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
    
            {"columns":["propertyName","documentKey","nextValue"],"primaryKey":"documentKey:propertyName"}
		
### Get Blueprint API specification for Document Nextvalues [GET /research-sys/api/v1/document-nextvalues/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Nextvalues.md"
            transfer-encoding:chunked


### Update Document Nextvalues [PUT /research-sys/api/v1/document-nextvalues/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Nextvalues [PUT /research-sys/api/v1/document-nextvalues/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"},
              {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Document Nextvalues [POST /research-sys/api/v1/document-nextvalues/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Nextvalues [POST /research-sys/api/v1/document-nextvalues/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"},
              {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"},
              {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Document Nextvalues by Key [DELETE /research-sys/api/v1/document-nextvalues/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Nextvalues [DELETE /research-sys/api/v1/document-nextvalues/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Nextvalues with Matching [DELETE /research-sys/api/v1/document-nextvalues/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + propertyName
            + documentKey
            + nextValue

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
