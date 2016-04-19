## Searchable Attribute String Values [/research-sys/api/v1/searchable-attribute-string-values/]

### Get Searchable Attribute String Values by Key [GET /research-sys/api/v1/searchable-attribute-string-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"}

### Get All Searchable Attribute String Values [GET /research-sys/api/v1/searchable-attribute-string-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"},
              {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"}
            ]

### Get All Searchable Attribute String Values with Filtering [GET /research-sys/api/v1/searchable-attribute-string-values/]
    
+ Parameters

    + searchableAttributeValue (optional) - 
    + documentId (optional) - 
    + searchableAttributeValueId (optional) - 
    + searchableAttributeKey (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"},
              {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Searchable Attribute String Values [GET /research-sys/api/v1/searchable-attribute-string-values/]
	                                          
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
    
            {"columns":["searchableAttributeValue","documentId","searchableAttributeValueId","searchableAttributeKey"],"primaryKey":"searchableAttributeValueId"}
		
### Get Blueprint API specification for Searchable Attribute String Values [GET /research-sys/api/v1/searchable-attribute-string-values/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Searchable Attribute String Values.md"
            transfer-encoding:chunked


### Update Searchable Attribute String Values [PUT /research-sys/api/v1/searchable-attribute-string-values/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Searchable Attribute String Values [PUT /research-sys/api/v1/searchable-attribute-string-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"},
              {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Searchable Attribute String Values [POST /research-sys/api/v1/searchable-attribute-string-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Searchable Attribute String Values [POST /research-sys/api/v1/searchable-attribute-string-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"},
              {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"},
              {"searchableAttributeValue": "(val)","documentId": "(val)","searchableAttributeValueId": "(val)","searchableAttributeKey": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Searchable Attribute String Values by Key [DELETE /research-sys/api/v1/searchable-attribute-string-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Searchable Attribute String Values [DELETE /research-sys/api/v1/searchable-attribute-string-values/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Searchable Attribute String Values with Matching [DELETE /research-sys/api/v1/searchable-attribute-string-values/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + searchableAttributeValue (optional) - 
    + documentId (optional) - 
    + searchableAttributeValueId (optional) - 
    + searchableAttributeKey (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
