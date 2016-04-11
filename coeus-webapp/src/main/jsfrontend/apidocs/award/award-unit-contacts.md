## Award Unit Contacts [/research-sys/api/v1/award-unit-contacts/]

### Get Award Unit Contacts by Key [GET /research-sys/api/v1/award-unit-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}

### Get All Award Unit Contacts [GET /research-sys/api/v1/award-unit-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Unit Contacts with Filtering [GET /research-sys/api/v1/award-unit-contacts/]
    
+ Parameters

        + awardContactId
            + personId
            + fullName
            + unitContactType
            + unitAdministratorTypeCode
            + awardId
            + awardNumber
            + sequenceNumber
            + unitAdministratorUnitNumber
            + defaultUnitContact

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Unit Contacts [GET /research-sys/api/v1/award-unit-contacts/]
	                                          
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
    
            {"columns":["awardContactId","personId","fullName","unitContactType","unitAdministratorTypeCode","awardId","awardNumber","sequenceNumber","unitAdministratorUnitNumber","defaultUnitContact"],"primaryKey":"awardContactId"}
		
### Get Blueprint API specification for Award Unit Contacts [GET /research-sys/api/v1/award-unit-contacts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Unit Contacts.md"
            transfer-encoding:chunked


### Update Award Unit Contacts [PUT /research-sys/api/v1/award-unit-contacts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Unit Contacts [PUT /research-sys/api/v1/award-unit-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Unit Contacts [POST /research-sys/api/v1/award-unit-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Unit Contacts [POST /research-sys/api/v1/award-unit-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Unit Contacts by Key [DELETE /research-sys/api/v1/award-unit-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Unit Contacts [DELETE /research-sys/api/v1/award-unit-contacts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Unit Contacts with Matching [DELETE /research-sys/api/v1/award-unit-contacts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardContactId
            + personId
            + fullName
            + unitContactType
            + unitAdministratorTypeCode
            + awardId
            + awardNumber
            + sequenceNumber
            + unitAdministratorUnitNumber
            + defaultUnitContact

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
