## Intellectual Property Review Activities [/instprop/api/v1/intellectual-property-review-activities/]

### Get Intellectual Property Review Activities by Key [GET /instprop/api/v1/intellectual-property-review-activities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}

### Get All Intellectual Property Review Activities [GET /instprop/api/v1/intellectual-property-review-activities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Intellectual Property Review Activities with Filtering [GET /instprop/api/v1/intellectual-property-review-activities/]
    
+ Parameters

    + ipReviewActivityTypeCode (optional) - IP Review Activity Type Code. Maximum length is 22.
    + activityDate (optional) - Activity Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 150.
    + proposalIpReviewActivityId (optional) - Proposal Ip Rev Activity Id. Maximum length is 22.
    + ipReviewId (optional) - 
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + activityNumber (optional) - Activity Number. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Intellectual Property Review Activities [GET /instprop/api/v1/intellectual-property-review-activities/]
	                                          
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
    
            {"columns":["ipReviewActivityTypeCode","activityDate","comments","proposalIpReviewActivityId","ipReviewId","proposalNumber","sequenceNumber","activityNumber"],"primaryKey":"proposalIpReviewActivityId"}
		
### Get Blueprint API specification for Intellectual Property Review Activities [GET /instprop/api/v1/intellectual-property-review-activities/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Intellectual Property Review Activities.md"
            transfer-encoding:chunked
### Update Intellectual Property Review Activities [PUT /instprop/api/v1/intellectual-property-review-activities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Intellectual Property Review Activities [PUT /instprop/api/v1/intellectual-property-review-activities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Intellectual Property Review Activities [POST /instprop/api/v1/intellectual-property-review-activities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Intellectual Property Review Activities [POST /instprop/api/v1/intellectual-property-review-activities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
            ]
### Delete Intellectual Property Review Activities by Key [DELETE /instprop/api/v1/intellectual-property-review-activities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Review Activities [DELETE /instprop/api/v1/intellectual-property-review-activities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Review Activities with Matching [DELETE /instprop/api/v1/intellectual-property-review-activities/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + ipReviewActivityTypeCode (optional) - IP Review Activity Type Code. Maximum length is 22.
    + activityDate (optional) - Activity Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 150.
    + proposalIpReviewActivityId (optional) - Proposal Ip Rev Activity Id. Maximum length is 22.
    + ipReviewId (optional) - 
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + activityNumber (optional) - Activity Number. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
