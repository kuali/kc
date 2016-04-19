## Iacuc Committee Schedule Action Items [/iacuc/api/v1/iacuc-committee-schedule-action-items/]

### Get Iacuc Committee Schedule Action Items by Key [GET /iacuc/api/v1/iacuc-committee-schedule-action-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committee Schedule Action Items [GET /iacuc/api/v1/iacuc-committee-schedule-action-items/]
	 
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

### Get All Iacuc Committee Schedule Action Items with Filtering [GET /iacuc/api/v1/iacuc-committee-schedule-action-items/]
    
+ Parameters

    + commScheduleActItemsId (optional) - 
    + scheduleIdFk (optional) - 
    + actionItemNumber (optional) - 
    + scheduleActItemTypeCode (optional) - 
    + itemDescription (optional) - 

            
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
			
### Get Schema for Iacuc Committee Schedule Action Items [GET /iacuc/api/v1/iacuc-committee-schedule-action-items/]
	                                          
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
    
            {"columns":["commScheduleActItemsId","scheduleIdFk","actionItemNumber","scheduleActItemTypeCode","itemDescription"],"primaryKey":"commScheduleActItemsId"}
		
### Get Blueprint API specification for Iacuc Committee Schedule Action Items [GET /iacuc/api/v1/iacuc-committee-schedule-action-items/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committee Schedule Action Items.md"
            transfer-encoding:chunked


### Update Iacuc Committee Schedule Action Items [PUT /iacuc/api/v1/iacuc-committee-schedule-action-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committee Schedule Action Items [PUT /iacuc/api/v1/iacuc-committee-schedule-action-items/]

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

### Insert Iacuc Committee Schedule Action Items [POST /iacuc/api/v1/iacuc-committee-schedule-action-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committee Schedule Action Items [POST /iacuc/api/v1/iacuc-committee-schedule-action-items/]

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
            
### Delete Iacuc Committee Schedule Action Items by Key [DELETE /iacuc/api/v1/iacuc-committee-schedule-action-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Action Items [DELETE /iacuc/api/v1/iacuc-committee-schedule-action-items/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Action Items with Matching [DELETE /iacuc/api/v1/iacuc-committee-schedule-action-items/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + commScheduleActItemsId (optional) - 
    + scheduleIdFk (optional) - 
    + actionItemNumber (optional) - 
    + scheduleActItemTypeCode (optional) - 
    + itemDescription (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
