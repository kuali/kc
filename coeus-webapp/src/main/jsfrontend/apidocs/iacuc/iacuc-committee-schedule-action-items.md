## Iacuc Committee Schedule Action Items [/research-sys/api/v1/iacuc-committee-schedule-action-items/]

### Get Iacuc Committee Schedule Action Items by Key [GET /research-sys/api/v1/iacuc-committee-schedule-action-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committee Schedule Action Items [GET /research-sys/api/v1/iacuc-committee-schedule-action-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"},
              {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Committee Schedule Action Items with Filtering [GET /research-sys/api/v1/iacuc-committee-schedule-action-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + commScheduleActItemsId
            + scheduleIdFk
            + actionItemNumber
            + scheduleActItemTypeCode
            + itemDescription
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"},
              {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Committee Schedule Action Items [GET /research-sys/api/v1/iacuc-committee-schedule-action-items/]
	 
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
		
### Get Blueprint API specification for Iacuc Committee Schedule Action Items [GET /research-sys/api/v1/iacuc-committee-schedule-action-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committee Schedule Action Items.md"
            transfer-encoding:chunked


### Update Iacuc Committee Schedule Action Items [PUT /research-sys/api/v1/iacuc-committee-schedule-action-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committee Schedule Action Items [PUT /research-sys/api/v1/iacuc-committee-schedule-action-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"},
              {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Committee Schedule Action Items [POST /research-sys/api/v1/iacuc-committee-schedule-action-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committee Schedule Action Items [POST /research-sys/api/v1/iacuc-committee-schedule-action-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"},
              {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"},
              {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Committee Schedule Action Items by Key [DELETE /research-sys/api/v1/iacuc-committee-schedule-action-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Action Items [DELETE /research-sys/api/v1/iacuc-committee-schedule-action-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Committee Schedule Action Items with Matching [DELETE /research-sys/api/v1/iacuc-committee-schedule-action-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + commScheduleActItemsId
            + scheduleIdFk
            + actionItemNumber
            + scheduleActItemTypeCode
            + itemDescription


+ Response 204
