## Document Links [/research-sys/api/v1/document-links/]

### Get Document Links by Key [GET /research-sys/api/v1/document-links/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"}

### Get All Document Links [GET /research-sys/api/v1/document-links/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"},
              {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Links with Filtering [GET /research-sys/api/v1/document-links/]
    
+ Parameters

    + docLinkId (optional) - 
    + orgnDocId (optional) - 
    + destDocId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"},
              {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Links [GET /research-sys/api/v1/document-links/]
	                                          
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
    
            {"columns":["docLinkId","orgnDocId","destDocId"],"primaryKey":"docLinkId"}
		
### Get Blueprint API specification for Document Links [GET /research-sys/api/v1/document-links/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Links.md"
            transfer-encoding:chunked


### Update Document Links [PUT /research-sys/api/v1/document-links/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Links [PUT /research-sys/api/v1/document-links/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"},
              {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Document Links [POST /research-sys/api/v1/document-links/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Links [POST /research-sys/api/v1/document-links/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"},
              {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"},
              {"docLinkId": "(val)","orgnDocId": "(val)","destDocId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Document Links by Key [DELETE /research-sys/api/v1/document-links/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Links [DELETE /research-sys/api/v1/document-links/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Links with Matching [DELETE /research-sys/api/v1/document-links/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + docLinkId (optional) - 
    + orgnDocId (optional) - 
    + destDocId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
