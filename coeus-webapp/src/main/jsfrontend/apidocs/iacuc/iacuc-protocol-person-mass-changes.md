## Iacuc Protocol Person Mass Changes [/research-sys/api/v1/iacuc-protocol-person-mass-changes/]

### Get Iacuc Protocol Person Mass Changes by Key [GET /research-sys/api/v1/iacuc-protocol-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Person Mass Changes [GET /research-sys/api/v1/iacuc-protocol-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Person Mass Changes with Filtering [GET /research-sys/api/v1/iacuc-protocol-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + iacucProtocolPersonMassChangeId
            + personMassChangeId
            + investigator
            + keyStudyPerson
            + correspondents
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Person Mass Changes [GET /research-sys/api/v1/iacuc-protocol-person-mass-changes/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Person Mass Changes [GET /research-sys/api/v1/iacuc-protocol-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Person Mass Changes.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Person Mass Changes [PUT /research-sys/api/v1/iacuc-protocol-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Person Mass Changes [PUT /research-sys/api/v1/iacuc-protocol-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Person Mass Changes [POST /research-sys/api/v1/iacuc-protocol-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Person Mass Changes [POST /research-sys/api/v1/iacuc-protocol-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Person Mass Changes by Key [DELETE /research-sys/api/v1/iacuc-protocol-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Person Mass Changes [DELETE /research-sys/api/v1/iacuc-protocol-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Person Mass Changes with Matching [DELETE /research-sys/api/v1/iacuc-protocol-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + iacucProtocolPersonMassChangeId
            + personMassChangeId
            + investigator
            + keyStudyPerson
            + correspondents


+ Response 204
