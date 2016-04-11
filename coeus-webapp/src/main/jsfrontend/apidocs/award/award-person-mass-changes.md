## Award Person Mass Changes [/research-sys/api/v1/award-person-mass-changes/]

### Get Award Person Mass Changes by Key [GET /research-sys/api/v1/award-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"}

### Get All Award Person Mass Changes [GET /research-sys/api/v1/award-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"},
              {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Person Mass Changes with Filtering [GET /research-sys/api/v1/award-person-mass-changes/]
    
+ Parameters

        + awardPersonMassChangeId
            + personMassChangeId
            + investigator
            + keyStudyPerson
            + unitContact
            + sponsorContact
            + approvedForeignTravel

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"},
              {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Person Mass Changes [GET /research-sys/api/v1/award-person-mass-changes/]
	                                          
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
    
            {"columns":["awardPersonMassChangeId","personMassChangeId","investigator","keyStudyPerson","unitContact","sponsorContact","approvedForeignTravel"],"primaryKey":"awardPersonMassChangeId"}
		
### Get Blueprint API specification for Award Person Mass Changes [GET /research-sys/api/v1/award-person-mass-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Person Mass Changes.md"
            transfer-encoding:chunked


### Update Award Person Mass Changes [PUT /research-sys/api/v1/award-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Person Mass Changes [PUT /research-sys/api/v1/award-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"},
              {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Person Mass Changes [POST /research-sys/api/v1/award-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Person Mass Changes [POST /research-sys/api/v1/award-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"},
              {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"},
              {"awardPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","sponsorContact": "(val)","approvedForeignTravel": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Person Mass Changes by Key [DELETE /research-sys/api/v1/award-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Person Mass Changes [DELETE /research-sys/api/v1/award-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Person Mass Changes with Matching [DELETE /research-sys/api/v1/award-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardPersonMassChangeId
            + personMassChangeId
            + investigator
            + keyStudyPerson
            + unitContact
            + sponsorContact
            + approvedForeignTravel

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
