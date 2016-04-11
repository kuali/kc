## Identity Management Person Documents [/research-sys/api/v1/identity-management-person-documents/]

### Get Identity Management Person Documents by Key [GET /research-sys/api/v1/identity-management-person-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Identity Management Person Documents [GET /research-sys/api/v1/identity-management-person-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Identity Management Person Documents with Filtering [GET /research-sys/api/v1/identity-management-person-documents/]
    
+ Parameters

        + principalId
            + principalName
            + entityId
            + password
            + univId
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
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Identity Management Person Documents [GET /research-sys/api/v1/identity-management-person-documents/]
	                                          
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
    
            {"columns":["principalId","principalName","entityId","password","univId","active","documentNumber"],"primaryKey":"documentNumber"}
		
### Get Blueprint API specification for Identity Management Person Documents [GET /research-sys/api/v1/identity-management-person-documents/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Identity Management Person Documents.md"
            transfer-encoding:chunked


### Update Identity Management Person Documents [PUT /research-sys/api/v1/identity-management-person-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Identity Management Person Documents [PUT /research-sys/api/v1/identity-management-person-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Identity Management Person Documents [POST /research-sys/api/v1/identity-management-person-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Identity Management Person Documents [POST /research-sys/api/v1/identity-management-person-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","univId": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Identity Management Person Documents by Key [DELETE /research-sys/api/v1/identity-management-person-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Identity Management Person Documents [DELETE /research-sys/api/v1/identity-management-person-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Identity Management Person Documents with Matching [DELETE /research-sys/api/v1/identity-management-person-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + principalId
            + principalName
            + entityId
            + password
            + univId
            + active
            + documentNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
