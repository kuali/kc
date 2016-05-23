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
    
            {"approvedEquipmentId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

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
              {"approvedEquipmentId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedEquipmentId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Approved Equipment with Filtering [GET /award/api/v1/award-approved-equipment/]
    
+ Parameters

    + approvedEquipmentId (optional) - Approved Equipment ID. Maximum length is 8.
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
              {"approvedEquipmentId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedEquipmentId": "(val)","item": "(val)","model": "(val)","vendor": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
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
    
            {"columns":["approvedEquipmentId","item","model","vendor","amount","awardNumber","sequenceNumber"],"primaryKey":"approvedEquipmentId"}
		
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
