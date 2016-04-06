## Kim Document Role Responsibilities [/research-sys/api/v1/kim-document-role-responsibilities/]

### Get Kim Document Role Responsibilities by Key [GET /research-sys/api/v1/kim-document-role-responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Kim Document Role Responsibilities [GET /research-sys/api/v1/kim-document-role-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kim Document Role Responsibilities with Filtering [GET /research-sys/api/v1/kim-document-role-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + roleResponsibilityId
            + roleId
            + responsibilityId
            + documentNumber
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kim Document Role Responsibilities [GET /research-sys/api/v1/kim-document-role-responsibilities/]
	 
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
		
### Get Blueprint API specification for Kim Document Role Responsibilities [GET /research-sys/api/v1/kim-document-role-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kim Document Role Responsibilities.md"
            transfer-encoding:chunked


### Update Kim Document Role Responsibilities [PUT /research-sys/api/v1/kim-document-role-responsibilities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kim Document Role Responsibilities [PUT /research-sys/api/v1/kim-document-role-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kim Document Role Responsibilities [POST /research-sys/api/v1/kim-document-role-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kim Document Role Responsibilities [POST /research-sys/api/v1/kim-document-role-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kim Document Role Responsibilities by Key [DELETE /research-sys/api/v1/kim-document-role-responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Document Role Responsibilities [DELETE /research-sys/api/v1/kim-document-role-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Kim Document Role Responsibilities with Matching [DELETE /research-sys/api/v1/kim-document-role-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + roleResponsibilityId
            + roleId
            + responsibilityId
            + documentNumber
            + active


+ Response 204
