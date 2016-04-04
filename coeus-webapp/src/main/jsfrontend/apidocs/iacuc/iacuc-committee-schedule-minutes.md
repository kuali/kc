## Iacuc Committee Schedule Minutes [/research-sys/api/v1/iacuc-committee-schedule-minutes/]

### Get Iacuc Committee Schedule Minutes by Key [GET /research-sys/api/v1/iacuc-committee-schedule-minutes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committee Schedule Minutes [GET /research-sys/api/v1/iacuc-committee-schedule-minutes/]
	 
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

### Get All Iacuc Committee Schedule Minutes with Filtering [GET /research-sys/api/v1/iacuc-committee-schedule-minutes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + commScheduleMinutesId
            + scheduleIdFk
            + entryNumber
            + minuteEntryTypeCode
            + protocolIdFk
            + commScheduleActItemsIdFk
            + submissionIdFk
            + privateCommentFlag
            + protocolContingencyCode
            + minuteEntry
            + finalFlag
            + createTimestamp
            + createUser
            + protocolReviewerIdFk
            + protocolOnlineReviewIdFk
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Committee Schedule Minutes [GET /research-sys/api/v1/iacuc-committee-schedule-minutes/]
	 
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
		
### Get Blueprint API specification for Iacuc Committee Schedule Minutes [GET /research-sys/api/v1/iacuc-committee-schedule-minutes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committee Schedule Minutes.md"
            transfer-encoding:chunked


### Update Iacuc Committee Schedule Minutes [PUT /research-sys/api/v1/iacuc-committee-schedule-minutes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committee Schedule Minutes [PUT /research-sys/api/v1/iacuc-committee-schedule-minutes/]

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

### Insert Iacuc Committee Schedule Minutes [POST /research-sys/api/v1/iacuc-committee-schedule-minutes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"commScheduleMinutesId": "(val)","scheduleIdFk": "(val)","entryNumber": "(val)","minuteEntryTypeCode": "(val)","protocolIdFk": "(val)","commScheduleActItemsIdFk": "(val)","submissionIdFk": "(val)","privateCommentFlag": "(val)","protocolContingencyCode": "(val)","minuteEntry": "(val)","finalFlag": "(val)","createTimestamp": "(val)","createUser": "(val)","protocolReviewerIdFk": "(val)","protocolOnlineReviewIdFk": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committee Schedule Minutes [POST /research-sys/api/v1/iacuc-committee-schedule-minutes/]

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
            
### Delete Iacuc Committee Schedule Minutes by Key [DELETE /research-sys/api/v1/iacuc-committee-schedule-minutes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Minutes [DELETE /research-sys/api/v1/iacuc-committee-schedule-minutes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Committee Schedule Minutes with Matching [DELETE /research-sys/api/v1/iacuc-committee-schedule-minutes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + commScheduleMinutesId
            + scheduleIdFk
            + entryNumber
            + minuteEntryTypeCode
            + protocolIdFk
            + commScheduleActItemsIdFk
            + submissionIdFk
            + privateCommentFlag
            + protocolContingencyCode
            + minuteEntry
            + finalFlag
            + createTimestamp
            + createUser
            + protocolReviewerIdFk
            + protocolOnlineReviewIdFk


+ Response 204
