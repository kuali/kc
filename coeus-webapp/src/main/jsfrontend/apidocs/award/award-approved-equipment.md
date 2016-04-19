## Award Approved Equipment [/award/api/v1/award-approved-equipment/]

### Get Award Approved Equipment by Key [GET /award/api/v1/award-approved-equipment/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Award Approved Equipment [GET /award/api/v1/award-approved-equipment/]
	 
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

### Get All Award Approved Equipment with Filtering [GET /award/api/v1/award-approved-equipment/]
    
+ Parameters

    + approvedEquipmentId (optional) - Approved Equipment ID. Maximum length is 8.
    + awardId (optional) - 
    + item (optional) - Item. Maximum length is 100.
    + model (optional) - Model. Maximum length is 50.
    + vendor (optional) - Vendor. Maximum length is 50.
    + amount (optional) - Amount. Maximum length is 15.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.

            
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
			
### Get Schema for Award Approved Equipment [GET /award/api/v1/award-approved-equipment/]
	                                          
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
    
            {"columns":["approvedEquipmentId","awardId","item","model","vendor","amount","awardNumber","sequenceNumber"],"primaryKey":"approvedEquipmentId"}
		
### Get Blueprint API specification for Award Approved Equipment [GET /award/api/v1/award-approved-equipment/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Approved Equipment.md"
            transfer-encoding:chunked


### Update Award Approved Equipment [PUT /award/api/v1/award-approved-equipment/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Approved Equipment [PUT /award/api/v1/award-approved-equipment/]

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

### Insert Award Approved Equipment [POST /award/api/v1/award-approved-equipment/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"approvedEquipmentId": "(val)","awardId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Approved Equipment [POST /award/api/v1/award-approved-equipment/]

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
            
### Delete Award Approved Equipment by Key [DELETE /award/api/v1/award-approved-equipment/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Approved Equipment [DELETE /award/api/v1/award-approved-equipment/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Approved Equipment with Matching [DELETE /award/api/v1/award-approved-equipment/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + approvedEquipmentId (optional) - Approved Equipment ID. Maximum length is 8.
    + awardId (optional) - 
    + item (optional) - Item. Maximum length is 100.
    + model (optional) - Model. Maximum length is 50.
    + vendor (optional) - Vendor. Maximum length is 50.
    + amount (optional) - Amount. Maximum length is 15.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
