## Custom Attribute Documents [/research-common/api/v1/custom-attribute-documents/]

### Get Custom Attribute Documents by Key [GET /research-common/api/v1/custom-attribute-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Custom Attribute Documents [GET /research-common/api/v1/custom-attribute-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Custom Attribute Documents with Filtering [GET /research-common/api/v1/custom-attribute-documents/]
    
+ Parameters

    + id (optional) - Custom Attribute ID. Maximum length is 12.
    + documentTypeName (optional) - Document Type Code. Maximum length is 4.
    + required (optional) - Required. Maximum length is 1.
    + typeName (optional) - Type Name. Maximum length is 100.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Custom Attribute Documents [GET /research-common/api/v1/custom-attribute-documents/]
	                                          
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
    
            {"columns":["id","documentTypeName","required","typeName","active"],"primaryKey":"documentTypeName:id"}
		
### Get Blueprint API specification for Custom Attribute Documents [GET /research-common/api/v1/custom-attribute-documents/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Attribute Documents.md"
            transfer-encoding:chunked


### Update Custom Attribute Documents [PUT /research-common/api/v1/custom-attribute-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Attribute Documents [PUT /research-common/api/v1/custom-attribute-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Custom Attribute Documents [POST /research-common/api/v1/custom-attribute-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Attribute Documents [POST /research-common/api/v1/custom-attribute-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentTypeName": "(val)","required": "(val)","typeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Custom Attribute Documents by Key [DELETE /research-common/api/v1/custom-attribute-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Attribute Documents [DELETE /research-common/api/v1/custom-attribute-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Attribute Documents with Matching [DELETE /research-common/api/v1/custom-attribute-documents/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Custom Attribute ID. Maximum length is 12.
    + documentTypeName (optional) - Document Type Code. Maximum length is 4.
    + required (optional) - Required. Maximum length is 1.
    + typeName (optional) - Type Name. Maximum length is 100.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
