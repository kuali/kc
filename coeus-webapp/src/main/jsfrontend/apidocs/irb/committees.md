## Committees [/irb/api/v1/committees/]

### Get Committees by Key [GET /irb/api/v1/committees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","committeeDocument.documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Committees [GET /irb/api/v1/committees/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","committeeDocument.documentNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","committeeDocument.documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committees with Filtering [GET /irb/api/v1/committees/]
    
+ Parameters

    + id (optional) - 
    + committeeId (optional) - Committee ID. Maximum length is 15.
    + sequenceNumber (optional) - 
    + committeeName (optional) - Committee Name. Maximum length is 60.
    + homeUnitNumber (optional) - Home Unit. Maximum length is 8.
    + committeeDescription (optional) - Committee Description. Maximum length is 2000.
    + scheduleDescription (optional) - Schedule Description. Maximum length is 2000.
    + committeeTypeCode (optional) - The type of committee. Maximum length is 3.
    + minimumMembersRequired (optional) - Min Members for Quorum. Maximum length is 3.
    + maxProtocols (optional) - Maximum Protocols. Maximum length is 4.
    + advancedSubmissionDaysRequired (optional) - Adv Submission Days. Maximum length is 3.
    + reviewTypeCode (optional) - The type of review. Maximum length is 3.
    + coiReviewTypeCode (optional) - The type of COI review. Maximum length is 3.
    + committeeDocument.documentNumber (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","committeeDocument.documentNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","committeeDocument.documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committees [GET /irb/api/v1/committees/]
	                                          
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
    
            {"columns":["id","committeeId","sequenceNumber","committeeName","homeUnitNumber","committeeDescription","scheduleDescription","committeeTypeCode","minimumMembersRequired","maxProtocols","advancedSubmissionDaysRequired","reviewTypeCode","coiReviewTypeCode","committeeDocument.documentNumber"],"primaryKey":"id"}
		
### Get Blueprint API specification for Committees [GET /irb/api/v1/committees/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committees.md"
            transfer-encoding:chunked
