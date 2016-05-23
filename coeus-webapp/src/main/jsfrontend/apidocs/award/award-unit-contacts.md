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
    
            {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}

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
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Unit Contacts with Filtering [GET /award/api/v1/award-unit-contacts/]
    
+ Parameters

    + awardContactId (optional) - 
    + personId (optional) - 
    + fullName (optional) - Full Name. Maximum length is 90.
    + unitContactType (optional) - 
    + unitAdministratorTypeCode (optional) - Project Role. Maximum length is 3.
    + awardNumber (optional) - 
    + sequenceNumber (optional) - 
    + unitAdministratorUnitNumber (optional) - 
    + defaultUnitContact (optional) - 
    + award.awardId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","unitAdministratorUnitNumber": "(val)","defaultUnitContact": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
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
    
            {"columns":["awardContactId","personId","fullName","unitContactType","unitAdministratorTypeCode","awardNumber","sequenceNumber","unitAdministratorUnitNumber","defaultUnitContact","award.awardId"],"primaryKey":"awardContactId"}
		
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
