## Iacuc Committee Schedule Minutes [/iacuc/api/v1/iacuc-committee-schedule-minutes/]

### Get Iacuc Committee Schedule Minutes by Key [GET /iacuc/api/v1/iacuc-committee-schedule-minutes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committee Schedule Minutes [GET /iacuc/api/v1/iacuc-committee-schedule-minutes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Committee Schedule Minutes with Filtering [GET /iacuc/api/v1/iacuc-committee-schedule-minutes/]
    
+ Parameters

    + commScheduleMinutesId (optional) - Id. Maximum length is 12.
    + scheduleIdFk (optional) - Schedule Id Fk. Maximum length is 12.
    + entryNumber (optional) - Entry Number. Maximum length is 12.
    + minuteEntryTypeCode (optional) - Minute Entry Type Code. Maximum length is 3.
    + protocolIdFk (optional) - Protocol Id Fk. Maximum length is 12.
    + commScheduleActItemsIdFk (optional) - CommScheduleActItems Id Fk. Maximum length is 12.
    + submissionIdFk (optional) - Submission Id Fk. Maximum length is 12.
    + privateCommentFlag (optional) - Private Comment Flag. Maximum length is 1.
    + protocolContingencyCode (optional) - Protocol Contingency Code. Maximum length is 4.
    + minuteEntry (optional) - Minute Entry. Maximum length is 4000.
    + finalFlag (optional) - Final Flag. Maximum length is 1.
    + createTimestamp (optional) - Created Time. Maximum length is 21.
    + createUser (optional) - Create User. Maximum length is 80.
    + protocolReviewerIdFk (optional) - Reviewers. Maximum length is 1000.
    + protocolOnlineReviewIdFk (optional) - Protocol Online Review. Maximum length is 1000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Committee Schedule Minutes [GET /iacuc/api/v1/iacuc-committee-schedule-minutes/]
	                                          
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
    
            {"columns":["commScheduleMinutesId","scheduleIdFk","entryNumber","minuteEntryTypeCode","protocolIdFk","commScheduleActItemsIdFk","submissionIdFk","privateCommentFlag","protocolContingencyCode","minuteEntry","finalFlag","createTimestamp","createUser","protocolReviewerIdFk","protocolOnlineReviewIdFk"],"primaryKey":"commScheduleMinutesId"}
		
### Get Blueprint API specification for Iacuc Committee Schedule Minutes [GET /iacuc/api/v1/iacuc-committee-schedule-minutes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committee Schedule Minutes.md"
            transfer-encoding:chunked


### Update Iacuc Committee Schedule Minutes [PUT /iacuc/api/v1/iacuc-committee-schedule-minutes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committee Schedule Minutes [PUT /iacuc/api/v1/iacuc-committee-schedule-minutes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Committee Schedule Minutes [POST /iacuc/api/v1/iacuc-committee-schedule-minutes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committee Schedule Minutes [POST /iacuc/api/v1/iacuc-committee-schedule-minutes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Committee Schedule Minutes by Key [DELETE /iacuc/api/v1/iacuc-committee-schedule-minutes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Minutes [DELETE /iacuc/api/v1/iacuc-committee-schedule-minutes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Minutes with Matching [DELETE /iacuc/api/v1/iacuc-committee-schedule-minutes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + commScheduleMinutesId (optional) - Id. Maximum length is 12.
    + scheduleIdFk (optional) - Schedule Id Fk. Maximum length is 12.
    + entryNumber (optional) - Entry Number. Maximum length is 12.
    + minuteEntryTypeCode (optional) - Minute Entry Type Code. Maximum length is 3.
    + protocolIdFk (optional) - Protocol Id Fk. Maximum length is 12.
    + commScheduleActItemsIdFk (optional) - CommScheduleActItems Id Fk. Maximum length is 12.
    + submissionIdFk (optional) - Submission Id Fk. Maximum length is 12.
    + privateCommentFlag (optional) - Private Comment Flag. Maximum length is 1.
    + protocolContingencyCode (optional) - Protocol Contingency Code. Maximum length is 4.
    + minuteEntry (optional) - Minute Entry. Maximum length is 4000.
    + finalFlag (optional) - Final Flag. Maximum length is 1.
    + createTimestamp (optional) - Created Time. Maximum length is 21.
    + createUser (optional) - Create User. Maximum length is 80.
    + protocolReviewerIdFk (optional) - Reviewers. Maximum length is 1000.
    + protocolOnlineReviewIdFk (optional) - Protocol Online Review. Maximum length is 1000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
