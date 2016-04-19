## Group Attributes [/research-sys/api/v1/group-attributes/]

### Get Group Attributes by Key [GET /research-sys/api/v1/group-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}

### Get All Group Attributes [GET /research-sys/api/v1/group-attributes/]
	 
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

### Get All Group Attributes with Filtering [GET /research-sys/api/v1/group-attributes/]
    
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
			
### Get Schema for Group Attributes [GET /research-sys/api/v1/group-attributes/]
	                                          
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
		
### Get Blueprint API specification for Group Attributes [GET /research-sys/api/v1/group-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Group Attributes.md"
            transfer-encoding:chunked


### Update Group Attributes [PUT /research-sys/api/v1/group-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Group Attributes [PUT /research-sys/api/v1/group-attributes/]

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

### Insert Group Attributes [POST /research-sys/api/v1/group-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Group Attributes [POST /research-sys/api/v1/group-attributes/]

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
            
### Delete Group Attributes by Key [DELETE /research-sys/api/v1/group-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Group Attributes [DELETE /research-sys/api/v1/group-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Group Attributes with Matching [DELETE /research-sys/api/v1/group-attributes/]

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
