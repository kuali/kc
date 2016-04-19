## S2s Application Submissions [/propdev/api/v1/s2s-application-submissions/]

### Get S2s Application Submissions by Key [GET /propdev/api/v1/s2s-application-submissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}

### Get All S2s Application Submissions [GET /propdev/api/v1/s2s-application-submissions/]
	 
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

### Get All S2s Application Submissions with Filtering [GET /propdev/api/v1/s2s-application-submissions/]
    
+ Parameters

    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + submissionNumber (optional) - Submission Number. Maximum length is 3.
    + agencyTrackingId (optional) - Agency Tracking Id. Maximum length is 50.
    + comments (optional) - Comments. Maximum length is 2000.
    + ggTrackingId (optional) - S2S Tracking Id. Maximum length is 50.
    + lastModifiedDate (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + lastNotifiedDate (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + receivedDate (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + status (optional) - Status. Maximum length is 50.

            
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
			
### Get Schema for S2s Application Submissions [GET /propdev/api/v1/s2s-application-submissions/]
	                                          
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
		
### Get Blueprint API specification for S2s Application Submissions [GET /propdev/api/v1/s2s-application-submissions/]
	 
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


### Update S2s Application Submissions [PUT /propdev/api/v1/s2s-application-submissions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Application Submissions [PUT /propdev/api/v1/s2s-application-submissions/]

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

### Insert S2s Application Submissions [POST /propdev/api/v1/s2s-application-submissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalNumber": "(val)","submissionNumber": "(val)","agencyTrackingId": "(val)","comments": "(val)","ggTrackingId": "(val)","lastModifiedDate": "(val)","lastNotifiedDate": "(val)","receivedDate": "(val)","status": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Application Submissions [POST /propdev/api/v1/s2s-application-submissions/]

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
            
### Delete S2s Application Submissions by Key [DELETE /propdev/api/v1/s2s-application-submissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Application Submissions [DELETE /propdev/api/v1/s2s-application-submissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Application Submissions with Matching [DELETE /propdev/api/v1/s2s-application-submissions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + submissionNumber (optional) - Submission Number. Maximum length is 3.
    + agencyTrackingId (optional) - Agency Tracking Id. Maximum length is 50.
    + comments (optional) - Comments. Maximum length is 2000.
    + ggTrackingId (optional) - S2S Tracking Id. Maximum length is 50.
    + lastModifiedDate (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + lastNotifiedDate (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + receivedDate (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + status (optional) - Status. Maximum length is 50.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
