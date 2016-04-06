## Award Approved Equipment [/research-sys/api/v1/award-approved-equipment/]

### Get Award Approved Equipment by Key [GET /research-sys/api/v1/award-approved-equipment/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Award Approved Equipment [GET /research-sys/api/v1/award-approved-equipment/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Approved Equipment with Filtering [GET /research-sys/api/v1/award-approved-equipment/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + approvedEquipmentId
            + awardId
            + item
            + model
            + vendor
            + amount
            + awardNumber
            + sequenceNumber
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Approved Equipment [GET /research-sys/api/v1/award-approved-equipment/]
	 
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
		
### Get Blueprint API specification for Award Approved Equipment [GET /research-sys/api/v1/award-approved-equipment/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Approved Equipment.md"
            transfer-encoding:chunked


### Update Award Approved Equipment [PUT /research-sys/api/v1/award-approved-equipment/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Approved Equipment [PUT /research-sys/api/v1/award-approved-equipment/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Approved Equipment [POST /research-sys/api/v1/award-approved-equipment/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Approved Equipment [POST /research-sys/api/v1/award-approved-equipment/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Approved Equipment by Key [DELETE /research-sys/api/v1/award-approved-equipment/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Approved Equipment [DELETE /research-sys/api/v1/award-approved-equipment/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Approved Equipment with Matching [DELETE /research-sys/api/v1/award-approved-equipment/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + approvedEquipmentId
            + awardId
            + item
            + model
            + vendor
            + amount
            + awardNumber
            + sequenceNumber


+ Response 204
