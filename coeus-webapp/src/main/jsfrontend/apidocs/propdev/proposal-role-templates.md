## Proposal Role Templates [/research-sys/api/v1/proposal-role-templates/]

### Get Proposal Role Templates by Key [GET /research-sys/api/v1/proposal-role-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Proposal Role Templates [GET /research-sys/api/v1/proposal-role-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Role Templates with Filtering [GET /research-sys/api/v1/proposal-role-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + personId
            + roleName
            + unitNumber
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Role Templates [GET /research-sys/api/v1/proposal-role-templates/]
	 
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
		
### Get Blueprint API specification for Proposal Role Templates [GET /research-sys/api/v1/proposal-role-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Role Templates.md"
            transfer-encoding:chunked


### Update Proposal Role Templates [PUT /research-sys/api/v1/proposal-role-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Role Templates [PUT /research-sys/api/v1/proposal-role-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Role Templates [POST /research-sys/api/v1/proposal-role-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Role Templates [POST /research-sys/api/v1/proposal-role-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Role Templates by Key [DELETE /research-sys/api/v1/proposal-role-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Role Templates [DELETE /research-sys/api/v1/proposal-role-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Role Templates with Matching [DELETE /research-sys/api/v1/proposal-role-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + personId
            + roleName
            + unitNumber
            + active


+ Response 204
