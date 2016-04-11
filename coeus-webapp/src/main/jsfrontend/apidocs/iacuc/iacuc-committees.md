## Iacuc Committees [/research-sys/api/v1/iacuc-committees/]

### Get Iacuc Committees by Key [GET /research-sys/api/v1/iacuc-committees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committees [GET /research-sys/api/v1/iacuc-committees/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Committees with Filtering [GET /research-sys/api/v1/iacuc-committees/]
    
+ Parameters

        + id
            + documentNumber
            + committeeId
            + sequenceNumber
            + committeeName
            + homeUnitNumber
            + committeeDescription
            + scheduleDescription
            + committeeTypeCode
            + minimumMembersRequired
            + maxProtocols
            + advancedSubmissionDaysRequired
            + reviewTypeCode
            + coiReviewTypeCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Committees [GET /research-sys/api/v1/iacuc-committees/]
	                                          
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
    
            {"columns":["id","documentNumber","committeeId","sequenceNumber","committeeName","homeUnitNumber","committeeDescription","scheduleDescription","committeeTypeCode","minimumMembersRequired","maxProtocols","advancedSubmissionDaysRequired","reviewTypeCode","coiReviewTypeCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Iacuc Committees [GET /research-sys/api/v1/iacuc-committees/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committees.md"
            transfer-encoding:chunked


### Update Iacuc Committees [PUT /research-sys/api/v1/iacuc-committees/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committees [PUT /research-sys/api/v1/iacuc-committees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Committees [POST /research-sys/api/v1/iacuc-committees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committees [POST /research-sys/api/v1/iacuc-committees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","committeeId": "(val)","sequenceNumber": "(val)","committeeName": "(val)","homeUnitNumber": "(val)","committeeDescription": "(val)","scheduleDescription": "(val)","committeeTypeCode": "(val)","minimumMembersRequired": "(val)","maxProtocols": "(val)","advancedSubmissionDaysRequired": "(val)","reviewTypeCode": "(val)","coiReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Committees by Key [DELETE /research-sys/api/v1/iacuc-committees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committees [DELETE /research-sys/api/v1/iacuc-committees/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committees with Matching [DELETE /research-sys/api/v1/iacuc-committees/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + documentNumber
            + committeeId
            + sequenceNumber
            + committeeName
            + homeUnitNumber
            + committeeDescription
            + scheduleDescription
            + committeeTypeCode
            + minimumMembersRequired
            + maxProtocols
            + advancedSubmissionDaysRequired
            + reviewTypeCode
            + coiReviewTypeCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
