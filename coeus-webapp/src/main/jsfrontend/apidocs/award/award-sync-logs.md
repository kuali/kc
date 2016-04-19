## Award Sync Logs [/award/api/v1/award-sync-logs/]

### Get Award Sync Logs by Key [GET /award/api/v1/award-sync-logs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}

### Get All Award Sync Logs [GET /award/api/v1/award-sync-logs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Sync Logs with Filtering [GET /award/api/v1/award-sync-logs/]
    
+ Parameters

    + awardSyncLogId (optional) - Award Sync Log Id. Maximum length is 40.
    + awardSyncStatusId (optional) - Award Sync Status Id. Maximum length is 40.
    + awardSyncChangeId (optional) - Award Sync Change Id. Maximum length is 40.
    + success (optional) - Success. Maximum length is 1.
    + logTypeCode (optional) - Log Type. Maximum length is 2.
    + status (optional) - Result Message. Maximum length is 4000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Sync Logs [GET /award/api/v1/award-sync-logs/]
	                                          
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
    
            {"columns":["awardSyncLogId","awardSyncStatusId","awardSyncChangeId","success","logTypeCode","status"],"primaryKey":"awardSyncLogId"}
		
### Get Blueprint API specification for Award Sync Logs [GET /award/api/v1/award-sync-logs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Sync Logs.md"
            transfer-encoding:chunked


### Update Award Sync Logs [PUT /award/api/v1/award-sync-logs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Sync Logs [PUT /award/api/v1/award-sync-logs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Sync Logs [POST /award/api/v1/award-sync-logs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Sync Logs [POST /award/api/v1/award-sync-logs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Sync Logs by Key [DELETE /award/api/v1/award-sync-logs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sync Logs [DELETE /award/api/v1/award-sync-logs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sync Logs with Matching [DELETE /award/api/v1/award-sync-logs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardSyncLogId (optional) - Award Sync Log Id. Maximum length is 40.
    + awardSyncStatusId (optional) - Award Sync Status Id. Maximum length is 40.
    + awardSyncChangeId (optional) - Award Sync Change Id. Maximum length is 40.
    + success (optional) - Success. Maximum length is 1.
    + logTypeCode (optional) - Log Type. Maximum length is 2.
    + status (optional) - Result Message. Maximum length is 4000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
