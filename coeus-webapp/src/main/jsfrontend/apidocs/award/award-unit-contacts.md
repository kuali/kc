## Award Unit Contacts [/award/api/v1/award-unit-contacts/]

### Get Award Unit Contacts by Key [GET /award/api/v1/award-unit-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}

### Get All Award Unit Contacts [GET /award/api/v1/award-unit-contacts/]
	 
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

### Get All Award Unit Contacts with Filtering [GET /award/api/v1/award-unit-contacts/]
    
+ Parameters

    + awardContactId (optional) - 
    + personId (optional) - 
    + fullName (optional) - Full Name. Maximum length is 90.
    + unitContactType (optional) - 
    + unitAdministratorTypeCode (optional) - Project Role. Maximum length is 3.
    + awardId (optional) - 
    + awardNumber (optional) - 
    + sequenceNumber (optional) - 
    + unitAdministratorUnitNumber (optional) - 
    + defaultUnitContact (optional) - 

            
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
			
### Get Schema for Award Unit Contacts [GET /award/api/v1/award-unit-contacts/]
	                                          
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
		
### Get Blueprint API specification for Award Unit Contacts [GET /award/api/v1/award-unit-contacts/]
	 
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


### Update Award Unit Contacts [PUT /award/api/v1/award-unit-contacts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Unit Contacts [PUT /award/api/v1/award-unit-contacts/]

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

### Insert Award Unit Contacts [POST /award/api/v1/award-unit-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Unit Contacts [POST /award/api/v1/award-unit-contacts/]

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
            
### Delete Award Unit Contacts by Key [DELETE /award/api/v1/award-unit-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Unit Contacts [DELETE /award/api/v1/award-unit-contacts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Unit Contacts with Matching [DELETE /award/api/v1/award-unit-contacts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardContactId (optional) - 
    + personId (optional) - 
    + fullName (optional) - Full Name. Maximum length is 90.
    + unitContactType (optional) - 
    + unitAdministratorTypeCode (optional) - Project Role. Maximum length is 3.
    + awardId (optional) - 
    + awardNumber (optional) - 
    + sequenceNumber (optional) - 
    + unitAdministratorUnitNumber (optional) - 
    + defaultUnitContact (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
