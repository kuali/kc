## Intellectual Property Review Activities [/research-sys/api/v1/intellectual-property-review-activities/]

### Get Intellectual Property Review Activities by Key [GET /research-sys/api/v1/intellectual-property-review-activities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}

### Get All Intellectual Property Review Activities [GET /research-sys/api/v1/intellectual-property-review-activities/]
	 
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

### Get All Intellectual Property Review Activities with Filtering [GET /research-sys/api/v1/intellectual-property-review-activities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + ipReviewActivityTypeCode
            + activityDate
            + comments
            + proposalIpReviewActivityId
            + ipReviewId
            + proposalNumber
            + sequenceNumber
            + activityNumber
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"},
              {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Intellectual Property Review Activities [GET /research-sys/api/v1/intellectual-property-review-activities/]
	 
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
		
### Get Blueprint API specification for Intellectual Property Review Activities [GET /research-sys/api/v1/intellectual-property-review-activities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Intellectual Property Review Activities.md"
            transfer-encoding:chunked


### Update Intellectual Property Review Activities [PUT /research-sys/api/v1/intellectual-property-review-activities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Intellectual Property Review Activities [PUT /research-sys/api/v1/intellectual-property-review-activities/]

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

### Insert Intellectual Property Review Activities [POST /research-sys/api/v1/intellectual-property-review-activities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"ipReviewActivityTypeCode": "(val)","activityDate": "(val)","comments": "(val)","proposalIpReviewActivityId": "(val)","ipReviewId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","activityNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Intellectual Property Review Activities [POST /research-sys/api/v1/intellectual-property-review-activities/]

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
            
### Delete Intellectual Property Review Activities by Key [DELETE /research-sys/api/v1/intellectual-property-review-activities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Review Activities [DELETE /research-sys/api/v1/intellectual-property-review-activities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Intellectual Property Review Activities with Matching [DELETE /research-sys/api/v1/intellectual-property-review-activities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + ipReviewActivityTypeCode
            + activityDate
            + comments
            + proposalIpReviewActivityId
            + ipReviewId
            + proposalNumber
            + sequenceNumber
            + activityNumber


+ Response 204
