## Permission Attributes [/research-sys/api/v1/permission-attributes/]

### Get Permission Attributes by Key [GET /research-sys/api/v1/permission-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}

### Get All Permission Attributes [GET /research-sys/api/v1/permission-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Permission Attributes with Filtering [GET /research-sys/api/v1/permission-attributes/]
    
+ Parameters

    + id (optional) - Id.
    + assignedToId (optional) - Assigned To Id.
    + attributeValue (optional) - Attribute Value. Maximum length is 40.
    + kimTypeId (optional) - Kim Type Id.
    + kimAttributeId (optional) - Attribute Id. Maximum length is 40.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Permission Attributes [GET /research-sys/api/v1/permission-attributes/]
	                                          
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
    
            {"columns":["id","assignedToId","attributeValue","kimTypeId","kimAttributeId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Permission Attributes [GET /research-sys/api/v1/permission-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Permission Attributes.md"
            transfer-encoding:chunked
### Update Permission Attributes [PUT /research-sys/api/v1/permission-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Permission Attributes [PUT /research-sys/api/v1/permission-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Permission Attributes [POST /research-sys/api/v1/permission-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Permission Attributes [POST /research-sys/api/v1/permission-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Permission Attributes by Key [DELETE /research-sys/api/v1/permission-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Permission Attributes [DELETE /research-sys/api/v1/permission-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Permission Attributes with Matching [DELETE /research-sys/api/v1/permission-attributes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id.
    + assignedToId (optional) - Assigned To Id.
    + attributeValue (optional) - Attribute Value. Maximum length is 40.
    + kimTypeId (optional) - Kim Type Id.
    + kimAttributeId (optional) - Attribute Id. Maximum length is 40.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
