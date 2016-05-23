## Role Member Attribute Data [/research-sys/api/v1/role-member-attribute-data/]

### Get Role Member Attribute Data by Key [GET /research-sys/api/v1/role-member-attribute-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}

### Get All Role Member Attribute Data [GET /research-sys/api/v1/role-member-attribute-data/]
	 
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

### Get All Role Member Attribute Data with Filtering [GET /research-sys/api/v1/role-member-attribute-data/]
    
+ Parameters

    + id (optional) - 
    + assignedToId (optional) - 
    + attributeValue (optional) - 
    + kimTypeId (optional) - 
    + kimAttributeId (optional) - 

            
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
			
### Get Schema for Role Member Attribute Data [GET /research-sys/api/v1/role-member-attribute-data/]
	                                          
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
		
### Get Blueprint API specification for Role Member Attribute Data [GET /research-sys/api/v1/role-member-attribute-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Role Member Attribute Data.md"
            transfer-encoding:chunked
### Update Role Member Attribute Data [PUT /research-sys/api/v1/role-member-attribute-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Role Member Attribute Data [PUT /research-sys/api/v1/role-member-attribute-data/]

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
### Insert Role Member Attribute Data [POST /research-sys/api/v1/role-member-attribute-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Role Member Attribute Data [POST /research-sys/api/v1/role-member-attribute-data/]

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
### Delete Role Member Attribute Data by Key [DELETE /research-sys/api/v1/role-member-attribute-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Member Attribute Data [DELETE /research-sys/api/v1/role-member-attribute-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Member Attribute Data with Matching [DELETE /research-sys/api/v1/role-member-attribute-data/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + assignedToId (optional) - 
    + attributeValue (optional) - 
    + kimTypeId (optional) - 
    + kimAttributeId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
