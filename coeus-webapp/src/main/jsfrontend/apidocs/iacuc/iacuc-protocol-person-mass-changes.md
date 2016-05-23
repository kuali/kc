## Iacuc Protocol Person Mass Changes [/iacuc/api/v1/iacuc-protocol-person-mass-changes/]

### Get Iacuc Protocol Person Mass Changes by Key [GET /iacuc/api/v1/iacuc-protocol-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Person Mass Changes [GET /iacuc/api/v1/iacuc-protocol-person-mass-changes/]
	 
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

### Get All Iacuc Protocol Person Mass Changes with Filtering [GET /iacuc/api/v1/iacuc-protocol-person-mass-changes/]
    
+ Parameters

    + iacucProtocolPersonMassChangeId (optional) - IACUC Protocol Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + investigator (optional) - Investigator. Maximum length is 1.
    + keyStudyPerson (optional) - Key Study Person. Maximum length is 1.
    + correspondents (optional) - Correspondents. Maximum length is 1.

            
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
			
### Get Schema for Iacuc Protocol Person Mass Changes [GET /iacuc/api/v1/iacuc-protocol-person-mass-changes/]
	                                          
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
    
            {"columns":["iacucProtocolPersonMassChangeId","personMassChangeId","investigator","keyStudyPerson","correspondents"],"primaryKey":"iacucProtocolPersonMassChangeId"}
		
### Get Blueprint API specification for Iacuc Protocol Person Mass Changes [GET /iacuc/api/v1/iacuc-protocol-person-mass-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Person Mass Changes.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Person Mass Changes [PUT /iacuc/api/v1/iacuc-protocol-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Person Mass Changes [PUT /iacuc/api/v1/iacuc-protocol-person-mass-changes/]

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
### Insert Iacuc Protocol Person Mass Changes [POST /iacuc/api/v1/iacuc-protocol-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Person Mass Changes [POST /iacuc/api/v1/iacuc-protocol-person-mass-changes/]

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
### Delete Iacuc Protocol Person Mass Changes by Key [DELETE /iacuc/api/v1/iacuc-protocol-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Person Mass Changes [DELETE /iacuc/api/v1/iacuc-protocol-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Person Mass Changes with Matching [DELETE /iacuc/api/v1/iacuc-protocol-person-mass-changes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucProtocolPersonMassChangeId (optional) - IACUC Protocol Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + investigator (optional) - Investigator. Maximum length is 1.
    + keyStudyPerson (optional) - Key Study Person. Maximum length is 1.
    + correspondents (optional) - Correspondents. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
