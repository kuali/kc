## Iacuc Unit Correspondents [/research-sys/api/v1/iacuc-unit-correspondents/]

### Get Iacuc Unit Correspondents by Key [GET /research-sys/api/v1/iacuc-unit-correspondents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Unit Correspondents [GET /research-sys/api/v1/iacuc-unit-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Unit Correspondents with Filtering [GET /research-sys/api/v1/iacuc-unit-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + correspondentId
            + unitNumber
            + correspondentTypeCode
            + personId
            + nonEmployeeFlag
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Unit Correspondents [GET /research-sys/api/v1/iacuc-unit-correspondents/]
	 
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
		
### Get Blueprint API specification for Iacuc Unit Correspondents [GET /research-sys/api/v1/iacuc-unit-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Unit Correspondents.md"
            transfer-encoding:chunked


### Update Iacuc Unit Correspondents [PUT /research-sys/api/v1/iacuc-unit-correspondents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Unit Correspondents [PUT /research-sys/api/v1/iacuc-unit-correspondents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Unit Correspondents [POST /research-sys/api/v1/iacuc-unit-correspondents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Unit Correspondents [POST /research-sys/api/v1/iacuc-unit-correspondents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Unit Correspondents by Key [DELETE /research-sys/api/v1/iacuc-unit-correspondents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Unit Correspondents [DELETE /research-sys/api/v1/iacuc-unit-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Unit Correspondents with Matching [DELETE /research-sys/api/v1/iacuc-unit-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + correspondentId
            + unitNumber
            + correspondentTypeCode
            + personId
            + nonEmployeeFlag
            + description


+ Response 204
