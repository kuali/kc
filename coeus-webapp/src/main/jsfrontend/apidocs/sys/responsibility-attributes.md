## Responsibility Attributes [/research-sys/api/v1/responsibility-attributes/]

### Get Responsibility Attributes by Key [GET /research-sys/api/v1/responsibility-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}

### Get All Responsibility Attributes [GET /research-sys/api/v1/responsibility-attributes/]
	 
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

### Get All Responsibility Attributes with Filtering [GET /research-sys/api/v1/responsibility-attributes/]
    
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
			
### Get Schema for Responsibility Attributes [GET /research-sys/api/v1/responsibility-attributes/]
	                                          
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
		
### Get Blueprint API specification for Responsibility Attributes [GET /research-sys/api/v1/responsibility-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Responsibility Attributes.md"
            transfer-encoding:chunked


### Update Responsibility Attributes [PUT /research-sys/api/v1/responsibility-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Responsibility Attributes [PUT /research-sys/api/v1/responsibility-attributes/]

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

### Insert Responsibility Attributes [POST /research-sys/api/v1/responsibility-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Responsibility Attributes [POST /research-sys/api/v1/responsibility-attributes/]

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
            
### Delete Responsibility Attributes by Key [DELETE /research-sys/api/v1/responsibility-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Responsibility Attributes [DELETE /research-sys/api/v1/responsibility-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Responsibility Attributes with Matching [DELETE /research-sys/api/v1/responsibility-attributes/]

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
