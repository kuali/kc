## Document Nextvalues [/research-common/api/v1/document-nextvalues/]

### Get Document Nextvalues by Key [GET /research-common/api/v1/document-nextvalues/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}

### Get All Document Nextvalues [GET /research-common/api/v1/document-nextvalues/]
	 
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

### Get All Document Nextvalues with Filtering [GET /research-common/api/v1/document-nextvalues/]
    
+ Parameters

    + propertyName (optional) - Property Name. Maximum length is 200.
    + documentKey (optional) - Document Number. Maximum length is 40.
    + nextValue (optional) - Next Value. Maximum length is 12.

            
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
			
### Get Schema for Document Nextvalues [GET /research-common/api/v1/document-nextvalues/]
	                                          
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
		
### Get Blueprint API specification for Document Nextvalues [GET /research-common/api/v1/document-nextvalues/]
	 
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
### Update Document Nextvalues [PUT /research-common/api/v1/document-nextvalues/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Nextvalues [PUT /research-common/api/v1/document-nextvalues/]

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
### Insert Document Nextvalues [POST /research-common/api/v1/document-nextvalues/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"propertyName": "(val)","documentKey": "(val)","nextValue": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Nextvalues [POST /research-common/api/v1/document-nextvalues/]

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
### Delete Document Nextvalues by Key [DELETE /research-common/api/v1/document-nextvalues/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Nextvalues [DELETE /research-common/api/v1/document-nextvalues/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Nextvalues with Matching [DELETE /research-common/api/v1/document-nextvalues/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + propertyName (optional) - Property Name. Maximum length is 200.
    + documentKey (optional) - Document Number. Maximum length is 40.
    + nextValue (optional) - Next Value. Maximum length is 12.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
