## Award Sponsor Contacts [/research-sys/api/v1/award-sponsor-contacts/]

### Get Award Sponsor Contacts by Key [GET /research-sys/api/v1/award-sponsor-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Award Sponsor Contacts [GET /research-sys/api/v1/award-sponsor-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Sponsor Contacts with Filtering [GET /research-sys/api/v1/award-sponsor-contacts/]
    
+ Parameters

        + awardContactId
            + rolodexId
            + fullName
            + roleCode
            + awardId
            + awardNumber
            + sequenceNumber

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Sponsor Contacts [GET /research-sys/api/v1/award-sponsor-contacts/]
	                                          
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
    
            {"columns":["awardContactId","rolodexId","fullName","roleCode","awardId","awardNumber","sequenceNumber"],"primaryKey":"awardContactId"}
		
### Get Blueprint API specification for Award Sponsor Contacts [GET /research-sys/api/v1/award-sponsor-contacts/]
	 
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


### Update Award Sponsor Contacts [PUT /research-sys/api/v1/award-sponsor-contacts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Sponsor Contacts [PUT /research-sys/api/v1/award-sponsor-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Sponsor Contacts [POST /research-sys/api/v1/award-sponsor-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Sponsor Contacts [POST /research-sys/api/v1/award-sponsor-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","rolodexId": "(val)","fullName": "(val)","roleCode": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Sponsor Contacts by Key [DELETE /research-sys/api/v1/award-sponsor-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sponsor Contacts [DELETE /research-sys/api/v1/award-sponsor-contacts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sponsor Contacts with Matching [DELETE /research-sys/api/v1/award-sponsor-contacts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardContactId
            + rolodexId
            + fullName
            + roleCode
            + awardId
            + awardNumber
            + sequenceNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
