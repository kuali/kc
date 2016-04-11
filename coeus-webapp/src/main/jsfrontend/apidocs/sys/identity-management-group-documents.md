## Identity Management Group Documents [/research-sys/api/v1/identity-management-group-documents/]

### Get Identity Management Group Documents by Key [GET /research-sys/api/v1/identity-management-group-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Identity Management Group Documents [GET /research-sys/api/v1/identity-management-group-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Identity Management Group Documents with Filtering [GET /research-sys/api/v1/identity-management-group-documents/]
    
+ Parameters

        + groupId
            + groupTypeId
            + groupNamespace
            + groupName
            + groupDescription
            + active
            + documentNumber

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Identity Management Group Documents [GET /research-sys/api/v1/identity-management-group-documents/]
	                                          
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
    
            {"columns":["groupId","groupTypeId","groupNamespace","groupName","groupDescription","active","documentNumber"],"primaryKey":"documentNumber"}
		
### Get Blueprint API specification for Identity Management Group Documents [GET /research-sys/api/v1/identity-management-group-documents/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Identity Management Group Documents.md"
            transfer-encoding:chunked


### Update Identity Management Group Documents [PUT /research-sys/api/v1/identity-management-group-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Identity Management Group Documents [PUT /research-sys/api/v1/identity-management-group-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Identity Management Group Documents [POST /research-sys/api/v1/identity-management-group-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Identity Management Group Documents [POST /research-sys/api/v1/identity-management-group-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"groupId": "(val)","groupTypeId": "(val)","groupNamespace": "(val)","groupName": "(val)","groupDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Identity Management Group Documents by Key [DELETE /research-sys/api/v1/identity-management-group-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Identity Management Group Documents [DELETE /research-sys/api/v1/identity-management-group-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Identity Management Group Documents with Matching [DELETE /research-sys/api/v1/identity-management-group-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + groupId
            + groupTypeId
            + groupNamespace
            + groupName
            + groupDescription
            + active
            + documentNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
