## Cfdas [/research-sys/api/v1/cfdas/]

### Get Cfdas by Key [GET /research-sys/api/v1/cfdas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}

### Get All Cfdas [GET /research-sys/api/v1/cfdas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"},
              {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Cfdas with Filtering [GET /research-sys/api/v1/cfdas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + cfdaNumber
            + cfdaProgramTitleName
            + active
            + cfdaMaintenanceTypeId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"},
              {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Cfdas [GET /research-sys/api/v1/cfdas/]
	 
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
		
### Get Blueprint API specification for Cfdas [GET /research-sys/api/v1/cfdas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Cfdas.md"
            transfer-encoding:chunked


### Update Cfdas [PUT /research-sys/api/v1/cfdas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Cfdas [PUT /research-sys/api/v1/cfdas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"},
              {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Cfdas [POST /research-sys/api/v1/cfdas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Cfdas [POST /research-sys/api/v1/cfdas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"},
              {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"},
              {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Cfdas by Key [DELETE /research-sys/api/v1/cfdas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Cfdas [DELETE /research-sys/api/v1/cfdas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Cfdas with Matching [DELETE /research-sys/api/v1/cfdas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + cfdaNumber
            + cfdaProgramTitleName
            + active
            + cfdaMaintenanceTypeId


+ Response 204
