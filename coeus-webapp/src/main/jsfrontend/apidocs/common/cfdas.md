## Cfdas [/research-common/api/v1/cfdas/]

### Get Cfdas by Key [GET /research-common/api/v1/cfdas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}

### Get All Cfdas [GET /research-common/api/v1/cfdas/]
	 
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

### Get All Cfdas with Filtering [GET /research-common/api/v1/cfdas/]
    
+ Parameters

    + cfdaNumber (optional) - A unique identifier for the sponsor and the funding opportunity announcement.  AKA "Catalogue of Federal Domestic Assistance Number." The format for this CFDA Number is XX.XXX. Maximum length is 7.
    + cfdaProgramTitleName (optional) - CFDA Program Title Name. Maximum length is 300.
    + active (optional) - Active. Maximum length is 1.
    + cfdaMaintenanceTypeId (optional) - CFDA Maintenance Type Id. Maximum length is 10.

            
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
			
### Get Schema for Cfdas [GET /research-common/api/v1/cfdas/]
	                                          
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
    
            {"columns":["cfdaNumber","cfdaProgramTitleName","active","cfdaMaintenanceTypeId"],"primaryKey":"cfdaNumber"}
		
### Get Blueprint API specification for Cfdas [GET /research-common/api/v1/cfdas/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Cfdas.md"
            transfer-encoding:chunked


### Update Cfdas [PUT /research-common/api/v1/cfdas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Cfdas [PUT /research-common/api/v1/cfdas/]

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

### Insert Cfdas [POST /research-common/api/v1/cfdas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"cfdaNumber": "(val)","cfdaProgramTitleName": "(val)","active": "(val)","cfdaMaintenanceTypeId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Cfdas [POST /research-common/api/v1/cfdas/]

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
            
### Delete Cfdas by Key [DELETE /research-common/api/v1/cfdas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Cfdas [DELETE /research-common/api/v1/cfdas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Cfdas with Matching [DELETE /research-common/api/v1/cfdas/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + cfdaNumber (optional) - A unique identifier for the sponsor and the funding opportunity announcement.  AKA "Catalogue of Federal Domestic Assistance Number." The format for this CFDA Number is XX.XXX. Maximum length is 7.
    + cfdaProgramTitleName (optional) - CFDA Program Title Name. Maximum length is 300.
    + active (optional) - Active. Maximum length is 1.
    + cfdaMaintenanceTypeId (optional) - CFDA Maintenance Type Id. Maximum length is 10.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
