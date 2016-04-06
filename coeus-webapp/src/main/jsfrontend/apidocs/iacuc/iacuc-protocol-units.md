## Iacuc Protocol Units [/research-sys/api/v1/iacuc-protocol-units/]

### Get Iacuc Protocol Units by Key [GET /research-sys/api/v1/iacuc-protocol-units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Units [GET /research-sys/api/v1/iacuc-protocol-units/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Units with Filtering [GET /research-sys/api/v1/iacuc-protocol-units/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolUnitsId
            + protocolPersonId
            + protocolNumber
            + sequenceNumber
            + unitNumber
            + leadUnitFlag
            + personId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Units [GET /research-sys/api/v1/iacuc-protocol-units/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Units [GET /research-sys/api/v1/iacuc-protocol-units/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Units.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Units [PUT /research-sys/api/v1/iacuc-protocol-units/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Units [PUT /research-sys/api/v1/iacuc-protocol-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Units [POST /research-sys/api/v1/iacuc-protocol-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Units [POST /research-sys/api/v1/iacuc-protocol-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Units by Key [DELETE /research-sys/api/v1/iacuc-protocol-units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Units [DELETE /research-sys/api/v1/iacuc-protocol-units/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Units with Matching [DELETE /research-sys/api/v1/iacuc-protocol-units/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolUnitsId
            + protocolPersonId
            + protocolNumber
            + sequenceNumber
            + unitNumber
            + leadUnitFlag
            + personId


+ Response 204
