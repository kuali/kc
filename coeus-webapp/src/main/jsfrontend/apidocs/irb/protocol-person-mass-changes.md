## Protocol Person Mass Changes [/irb/api/v1/protocol-person-mass-changes/]

### Get Protocol Person Mass Changes by Key [GET /irb/api/v1/protocol-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"}

### Get All Protocol Person Mass Changes [GET /irb/api/v1/protocol-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"},
              {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Person Mass Changes with Filtering [GET /irb/api/v1/protocol-person-mass-changes/]
    
+ Parameters

    + protocolPersonMassChangeId (optional) - Protocol Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + investigator (optional) - Investigator. Maximum length is 1.
    + keyStudyPerson (optional) - Key Study Person. Maximum length is 1.
    + correspondents (optional) - Correspondents. Maximum length is 1.
    + reviewer (optional) - Reviewer. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"},
              {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Person Mass Changes [GET /irb/api/v1/protocol-person-mass-changes/]
	                                          
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
    
            {"columns":["protocolPersonMassChangeId","personMassChangeId","investigator","keyStudyPerson","correspondents","reviewer"],"primaryKey":"protocolPersonMassChangeId"}
		
### Get Blueprint API specification for Protocol Person Mass Changes [GET /irb/api/v1/protocol-person-mass-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Person Mass Changes.md"
            transfer-encoding:chunked
### Update Protocol Person Mass Changes [PUT /irb/api/v1/protocol-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Person Mass Changes [PUT /irb/api/v1/protocol-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"},
              {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Person Mass Changes [POST /irb/api/v1/protocol-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Person Mass Changes [POST /irb/api/v1/protocol-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"},
              {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"},
              {"protocolPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","correspondents": "(val)","reviewer": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Person Mass Changes by Key [DELETE /irb/api/v1/protocol-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Person Mass Changes [DELETE /irb/api/v1/protocol-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Person Mass Changes with Matching [DELETE /irb/api/v1/protocol-person-mass-changes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolPersonMassChangeId (optional) - Protocol Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + investigator (optional) - Investigator. Maximum length is 1.
    + keyStudyPerson (optional) - Key Study Person. Maximum length is 1.
    + correspondents (optional) - Correspondents. Maximum length is 1.
    + reviewer (optional) - Reviewer. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
