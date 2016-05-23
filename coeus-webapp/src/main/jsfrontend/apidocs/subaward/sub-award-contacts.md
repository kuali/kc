## Sub Award Contacts [/subaward/api/v1/sub-award-contacts/]

### Get Sub Award Contacts by Key [GET /subaward/api/v1/sub-award-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Contacts [GET /subaward/api/v1/sub-award-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Contacts with Filtering [GET /subaward/api/v1/sub-award-contacts/]
    
+ Parameters

    + subAwardContactId (optional) - Subaward Contact Id. Maximum length is 22.
    + subAwardId (optional) - Subaward Id. Maximum length is 22.
    + sequenceNumber (optional) - 
    + subAwardCode (optional) - 
    + contactTypeCode (optional) - Contact Type Code. Maximum length is 22.
    + rolodexId (optional) - Rolodex Id. Maximum length is 22.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Contacts [GET /subaward/api/v1/sub-award-contacts/]
	                                          
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
    
            {"columns":["subAwardContactId","subAwardId","sequenceNumber","subAwardCode","contactTypeCode","rolodexId"],"primaryKey":"subAwardContactId"}
		
### Get Blueprint API specification for Sub Award Contacts [GET /subaward/api/v1/sub-award-contacts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Contacts.md"
            transfer-encoding:chunked
### Update Sub Award Contacts [PUT /subaward/api/v1/sub-award-contacts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Contacts [PUT /subaward/api/v1/sub-award-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sub Award Contacts [POST /subaward/api/v1/sub-award-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Contacts [POST /subaward/api/v1/sub-award-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sub Award Contacts by Key [DELETE /subaward/api/v1/sub-award-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Contacts [DELETE /subaward/api/v1/sub-award-contacts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Contacts with Matching [DELETE /subaward/api/v1/sub-award-contacts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardContactId (optional) - Subaward Contact Id. Maximum length is 22.
    + subAwardId (optional) - Subaward Id. Maximum length is 22.
    + sequenceNumber (optional) - 
    + subAwardCode (optional) - 
    + contactTypeCode (optional) - Contact Type Code. Maximum length is 22.
    + rolodexId (optional) - Rolodex Id. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
