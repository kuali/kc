## Kim Attributes [/research-sys/api/v1/kim-attributes/]

### Get Kim Attributes by Key [GET /research-sys/api/v1/kim-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Kim Attributes [GET /research-sys/api/v1/kim-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kim Attributes with Filtering [GET /research-sys/api/v1/kim-attributes/]
    
+ Parameters

    + id (optional) - Attribute Id. Maximum length is 40.
    + componentName (optional) - Component Name. Maximum length is 40.
    + attributeName (optional) - Attribute Name. Maximum length is 40.
    + namespaceCode (optional) - Nmspc Cd. Maximum length is 40.
    + attributeLabel (optional) - Attribute Label. Maximum length is 40.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kim Attributes [GET /research-sys/api/v1/kim-attributes/]
	                                          
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
    
            {"columns":["id","componentName","attributeName","namespaceCode","attributeLabel","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Kim Attributes [GET /research-sys/api/v1/kim-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kim Attributes.md"
            transfer-encoding:chunked
### Update Kim Attributes [PUT /research-sys/api/v1/kim-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kim Attributes [PUT /research-sys/api/v1/kim-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Kim Attributes [POST /research-sys/api/v1/kim-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kim Attributes [POST /research-sys/api/v1/kim-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","componentName": "(val)","attributeName": "(val)","namespaceCode": "(val)","attributeLabel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Kim Attributes by Key [DELETE /research-sys/api/v1/kim-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Attributes [DELETE /research-sys/api/v1/kim-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Attributes with Matching [DELETE /research-sys/api/v1/kim-attributes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Attribute Id. Maximum length is 40.
    + componentName (optional) - Component Name. Maximum length is 40.
    + attributeName (optional) - Attribute Name. Maximum length is 40.
    + namespaceCode (optional) - Nmspc Cd. Maximum length is 40.
    + attributeLabel (optional) - Attribute Label. Maximum length is 40.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
