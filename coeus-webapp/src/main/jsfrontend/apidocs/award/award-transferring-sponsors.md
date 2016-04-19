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
    
            {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}

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
              {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"},
              {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Transferring Sponsors with Filtering [GET /award/api/v1/award-transferring-sponsors/]
    
+ Parameters

    + awardTransferringSponsorId (optional) - Award Transferring Sponsor Id. Maximum length is 22.
    + awardId (optional) - 
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
              {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"},
              {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}
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
    
            {"columns":["awardTransferringSponsorId","awardId","awardNumber","sequenceNumber","sponsorCode"],"primaryKey":"awardTransferringSponsorId"}
		
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


### Update Award Transferring Sponsors [PUT /award/api/v1/award-transferring-sponsors/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Transferring Sponsors [PUT /award/api/v1/award-transferring-sponsors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"},
              {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Transferring Sponsors [POST /award/api/v1/award-transferring-sponsors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Transferring Sponsors [POST /award/api/v1/award-transferring-sponsors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"},
              {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"},
              {"awardTransferringSponsorId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Transferring Sponsors by Key [DELETE /award/api/v1/award-transferring-sponsors/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Transferring Sponsors [DELETE /award/api/v1/award-transferring-sponsors/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Transferring Sponsors with Matching [DELETE /award/api/v1/award-transferring-sponsors/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardTransferringSponsorId (optional) - Award Transferring Sponsor Id. Maximum length is 22.
    + awardId (optional) - 
    + awardNumber (optional) - 
    + sequenceNumber (optional) - 
    + sponsorCode (optional) - Sponsor Code. Maximum length is 6.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
