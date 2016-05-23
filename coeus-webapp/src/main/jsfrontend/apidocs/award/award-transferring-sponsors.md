## Award Transferring Sponsors [/award/api/v1/award-transferring-sponsors/]

### Get Award Transferring Sponsors by Key [GET /award/api/v1/award-transferring-sponsors/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardTransferringSponsorId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}

### Get All Award Transferring Sponsors [GET /award/api/v1/award-transferring-sponsors/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardTransferringSponsorId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"},
              {"awardTransferringSponsorId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Transferring Sponsors with Filtering [GET /award/api/v1/award-transferring-sponsors/]
    
+ Parameters

    + awardTransferringSponsorId (optional) - Award Transferring Sponsor Id. Maximum length is 22.
    + awardNumber (optional) - 
    + sequenceNumber (optional) - 
    + sponsorCode (optional) - Sponsor Code. Maximum length is 6.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardTransferringSponsorId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"},
              {"awardTransferringSponsorId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Transferring Sponsors [GET /award/api/v1/award-transferring-sponsors/]
	                                          
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
    
            {"columns":["awardTransferringSponsorId","awardNumber","sequenceNumber","sponsorCode"],"primaryKey":"awardTransferringSponsorId"}
		
### Get Blueprint API specification for Award Transferring Sponsors [GET /award/api/v1/award-transferring-sponsors/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Transferring Sponsors.md"
            transfer-encoding:chunked
