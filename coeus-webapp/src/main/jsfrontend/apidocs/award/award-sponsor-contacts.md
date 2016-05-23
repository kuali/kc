## Award Sponsor Contacts [/award/api/v1/award-sponsor-contacts/]

### Get Award Sponsor Contacts by Key [GET /award/api/v1/award-sponsor-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}

### Get All Award Sponsor Contacts [GET /award/api/v1/award-sponsor-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Sponsor Contacts with Filtering [GET /award/api/v1/award-sponsor-contacts/]
    
+ Parameters

    + awardContactId (optional) - 
    + rolodexId (optional) - 
    + fullName (optional) - 
    + roleCode (optional) - 
    + awardNumber (optional) - 
    + sequenceNumber (optional) - 
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
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Sponsor Contacts [GET /award/api/v1/award-sponsor-contacts/]
	                                          
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
    
            {"columns":["awardContactId","rolodexId","fullName","roleCode","awardNumber","sequenceNumber","award.awardId"],"primaryKey":"awardContactId"}
		
### Get Blueprint API specification for Award Sponsor Contacts [GET /award/api/v1/award-sponsor-contacts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Sponsor Contacts.md"
            transfer-encoding:chunked
