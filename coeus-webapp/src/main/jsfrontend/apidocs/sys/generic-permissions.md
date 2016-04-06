## Generic Permissions [/research-sys/api/v1/generic-permissions/]

### Get Generic Permissions by Key [GET /research-sys/api/v1/generic-permissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"}

### Get All Generic Permissions [GET /research-sys/api/v1/generic-permissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"}
            ]

### Get All Generic Permissions with Filtering [GET /research-sys/api/v1/generic-permissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + namespaceCode
            + name
            + description
            + active
            + templateId
            + detailValues
            + details
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Generic Permissions [GET /research-sys/api/v1/generic-permissions/]
	 
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
		
### Get Blueprint API specification for Generic Permissions [GET /research-sys/api/v1/generic-permissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Generic Permissions.md"
            transfer-encoding:chunked


### Update Generic Permissions [PUT /research-sys/api/v1/generic-permissions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Generic Permissions [PUT /research-sys/api/v1/generic-permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Generic Permissions [POST /research-sys/api/v1/generic-permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Generic Permissions [POST /research-sys/api/v1/generic-permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","active": "(val)","templateId": "(val)","detailValues": "(val)","details": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Generic Permissions by Key [DELETE /research-sys/api/v1/generic-permissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Generic Permissions [DELETE /research-sys/api/v1/generic-permissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Generic Permissions with Matching [DELETE /research-sys/api/v1/generic-permissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + namespaceCode
            + name
            + description
            + active
            + templateId
            + detailValues
            + details


+ Response 204
