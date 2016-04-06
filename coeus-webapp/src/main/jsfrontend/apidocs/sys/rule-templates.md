## Rule Templates [/research-sys/api/v1/rule-templates/]

### Get Rule Templates by Key [GET /research-sys/api/v1/rule-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"}

### Get All Rule Templates [GET /research-sys/api/v1/rule-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rule Templates with Filtering [GET /research-sys/api/v1/rule-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + name
            + description
            + delegationTemplateId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rule Templates [GET /research-sys/api/v1/rule-templates/]
	 
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
		
### Get Blueprint API specification for Rule Templates [GET /research-sys/api/v1/rule-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rule Templates.md"
            transfer-encoding:chunked


### Update Rule Templates [PUT /research-sys/api/v1/rule-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rule Templates [PUT /research-sys/api/v1/rule-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rule Templates [POST /research-sys/api/v1/rule-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rule Templates [POST /research-sys/api/v1/rule-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","delegationTemplateId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rule Templates by Key [DELETE /research-sys/api/v1/rule-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Templates [DELETE /research-sys/api/v1/rule-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Rule Templates with Matching [DELETE /research-sys/api/v1/rule-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + name
            + description
            + delegationTemplateId


+ Response 204
