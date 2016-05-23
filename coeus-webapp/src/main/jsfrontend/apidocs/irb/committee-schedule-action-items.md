## Committee Schedule Action Items [/irb/api/v1/committee-schedule-action-items/]

### Get Committee Schedule Action Items by Key [GET /irb/api/v1/committee-schedule-action-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}

### Get All Committee Schedule Action Items [GET /irb/api/v1/committee-schedule-action-items/]
	 
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

### Get All Committee Schedule Action Items with Filtering [GET /irb/api/v1/committee-schedule-action-items/]
    
+ Parameters

    + commScheduleActItemsId (optional) - Comm Schedule Act Items Id. Maximum length is 12.
    + scheduleIdFk (optional) - Schedule Id. Maximum length is 12.
    + actionItemNumber (optional) - Action Item Number. Maximum length is 22.
    + scheduleActItemTypeCode (optional) - Schedule Act Item Type Code. Maximum length is 3.
    + itemDescription (optional) - Item Description. Maximum length is 2000.

            
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
			
### Get Schema for Committee Schedule Action Items [GET /irb/api/v1/committee-schedule-action-items/]
	                                          
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
		
### Get Blueprint API specification for Committee Schedule Action Items [GET /irb/api/v1/committee-schedule-action-items/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Schedule Action Items.md"
            transfer-encoding:chunked
### Update Committee Schedule Action Items [PUT /irb/api/v1/committee-schedule-action-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Schedule Action Items [PUT /irb/api/v1/committee-schedule-action-items/]

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
### Insert Committee Schedule Action Items [POST /irb/api/v1/committee-schedule-action-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"commScheduleActItemsId": "(val)","scheduleIdFk": "(val)","actionItemNumber": "(val)","scheduleActItemTypeCode": "(val)","itemDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Schedule Action Items [POST /irb/api/v1/committee-schedule-action-items/]

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
### Delete Committee Schedule Action Items by Key [DELETE /irb/api/v1/committee-schedule-action-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedule Action Items [DELETE /irb/api/v1/committee-schedule-action-items/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Schedule Action Items with Matching [DELETE /irb/api/v1/committee-schedule-action-items/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + commScheduleActItemsId (optional) - Comm Schedule Act Items Id. Maximum length is 12.
    + scheduleIdFk (optional) - Schedule Id. Maximum length is 12.
    + actionItemNumber (optional) - Action Item Number. Maximum length is 22.
    + scheduleActItemTypeCode (optional) - Schedule Act Item Type Code. Maximum length is 3.
    + itemDescription (optional) - Item Description. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
