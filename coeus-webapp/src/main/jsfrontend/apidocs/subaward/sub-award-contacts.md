## Sub Award Contacts [/research-sys/api/v1/sub-award-contacts/]

### Get Sub Award Contacts by Key [GET /research-sys/api/v1/sub-award-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Contacts [GET /research-sys/api/v1/sub-award-contacts/]
	 
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

### Get All Sub Award Contacts with Filtering [GET /research-sys/api/v1/sub-award-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + subAwardContactId
            + subAwardId
            + sequenceNumber
            + subAwardCode
            + contactTypeCode
            + rolodexId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Contacts [GET /research-sys/api/v1/sub-award-contacts/]
	 
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
		
### Get Blueprint API specification for Sub Award Contacts [GET /research-sys/api/v1/sub-award-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Contacts.md"
            transfer-encoding:chunked


### Update Sub Award Contacts [PUT /research-sys/api/v1/sub-award-contacts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Contacts [PUT /research-sys/api/v1/sub-award-contacts/]

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

### Insert Sub Award Contacts [POST /research-sys/api/v1/sub-award-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardContactId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Contacts [POST /research-sys/api/v1/sub-award-contacts/]

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
            
### Delete Sub Award Contacts by Key [DELETE /research-sys/api/v1/sub-award-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Contacts [DELETE /research-sys/api/v1/sub-award-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sub Award Contacts with Matching [DELETE /research-sys/api/v1/sub-award-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + subAwardContactId
            + subAwardId
            + sequenceNumber
            + subAwardCode
            + contactTypeCode
            + rolodexId


+ Response 204
