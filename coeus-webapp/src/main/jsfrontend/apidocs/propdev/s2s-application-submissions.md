## S2s Application Submissions [/research-sys/api/v1/s2s-application-submissions/]

### Get S2s Application Submissions by Key [GET /research-sys/api/v1/s2s-application-submissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}

### Get All S2s Application Submissions [GET /research-sys/api/v1/s2s-application-submissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s Application Submissions with Filtering [GET /research-sys/api/v1/s2s-application-submissions/]
    
+ Parameters

        + proposalNumber
            + submissionNumber
            + agencyTrackingId
            + comments
            + ggTrackingId
            + lastModifiedDate
            + lastNotifiedDate
            + receivedDate
            + status

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Application Submissions [GET /research-sys/api/v1/s2s-application-submissions/]
	                                          
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
    
            {"columns":["proposalNumber","submissionNumber","agencyTrackingId","comments","ggTrackingId","lastModifiedDate","lastNotifiedDate","receivedDate","status"],"primaryKey":"proposalNumber:submissionNumber"}
		
### Get Blueprint API specification for S2s Application Submissions [GET /research-sys/api/v1/s2s-application-submissions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Application Submissions.md"
            transfer-encoding:chunked


### Update S2s Application Submissions [PUT /research-sys/api/v1/s2s-application-submissions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Application Submissions [PUT /research-sys/api/v1/s2s-application-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s Application Submissions [POST /research-sys/api/v1/s2s-application-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Application Submissions [POST /research-sys/api/v1/s2s-application-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s Application Submissions by Key [DELETE /research-sys/api/v1/s2s-application-submissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Application Submissions [DELETE /research-sys/api/v1/s2s-application-submissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Application Submissions with Matching [DELETE /research-sys/api/v1/s2s-application-submissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + proposalNumber
            + submissionNumber
            + agencyTrackingId
            + comments
            + ggTrackingId
            + lastModifiedDate
            + lastNotifiedDate
            + receivedDate
            + status

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
