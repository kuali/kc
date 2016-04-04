## Award Sponsor Terms [/research-sys/api/v1/award-sponsor-terms/]

### Get Award Sponsor Terms by Key [GET /research-sys/api/v1/award-sponsor-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}

### Get All Award Sponsor Terms [GET /research-sys/api/v1/award-sponsor-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"},
              {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Sponsor Terms with Filtering [GET /research-sys/api/v1/award-sponsor-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardSponsorTermId
            + awardId
            + awardNumber
            + sequenceNumber
            + sponsorTermId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"},
              {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Sponsor Terms [GET /research-sys/api/v1/award-sponsor-terms/]
	 
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
		
### Get Blueprint API specification for Award Sponsor Terms [GET /research-sys/api/v1/award-sponsor-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Sponsor Terms.md"
            transfer-encoding:chunked


### Update Award Sponsor Terms [PUT /research-sys/api/v1/award-sponsor-terms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Sponsor Terms [PUT /research-sys/api/v1/award-sponsor-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"},
              {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Sponsor Terms [POST /research-sys/api/v1/award-sponsor-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Sponsor Terms [POST /research-sys/api/v1/award-sponsor-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"},
              {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"},
              {"awardSponsorTermId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Sponsor Terms by Key [DELETE /research-sys/api/v1/award-sponsor-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sponsor Terms [DELETE /research-sys/api/v1/award-sponsor-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Sponsor Terms with Matching [DELETE /research-sys/api/v1/award-sponsor-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardSponsorTermId
            + awardId
            + awardNumber
            + sequenceNumber
            + sponsorTermId


+ Response 204
