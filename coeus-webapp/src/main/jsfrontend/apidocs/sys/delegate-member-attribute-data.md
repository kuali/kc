## Delegate Member Attribute Data [/research-sys/api/v1/delegate-member-attribute-data/]

### Get Delegate Member Attribute Data by Key [GET /research-sys/api/v1/delegate-member-attribute-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}

### Get All Delegate Member Attribute Data [GET /research-sys/api/v1/delegate-member-attribute-data/]
	 
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

### Get All Delegate Member Attribute Data with Filtering [GET /research-sys/api/v1/delegate-member-attribute-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + assignedToId
            + attributeValue
            + kimTypeId
            + kimAttributeId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Delegate Member Attribute Data [GET /research-sys/api/v1/delegate-member-attribute-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Delegate Member Attribute Data [GET /research-sys/api/v1/delegate-member-attribute-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Delegate Member Attribute Data.md"
            transfer-encoding:chunked


### Update Delegate Member Attribute Data [PUT /research-sys/api/v1/delegate-member-attribute-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Delegate Member Attribute Data [PUT /research-sys/api/v1/delegate-member-attribute-data/]

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

### Insert Delegate Member Attribute Data [POST /research-sys/api/v1/delegate-member-attribute-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","assignedToId": "(val)","attributeValue": "(val)","kimTypeId": "(val)","kimAttributeId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Delegate Member Attribute Data [POST /research-sys/api/v1/delegate-member-attribute-data/]

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
            
### Delete Delegate Member Attribute Data by Key [DELETE /research-sys/api/v1/delegate-member-attribute-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Delegate Member Attribute Data [DELETE /research-sys/api/v1/delegate-member-attribute-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Delegate Member Attribute Data with Matching [DELETE /research-sys/api/v1/delegate-member-attribute-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + assignedToId
            + attributeValue
            + kimTypeId
            + kimAttributeId


+ Response 204
