## Award Sync Logs [/research-sys/api/v1/award-sync-logs/]

### Get Award Sync Logs by Key [GET /research-sys/api/v1/award-sync-logs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}

### Get All Award Sync Logs [GET /research-sys/api/v1/award-sync-logs/]
	 
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

### Get All Award Sync Logs with Filtering [GET /research-sys/api/v1/award-sync-logs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardSyncLogId
            + awardSyncStatusId
            + awardSyncChangeId
            + success
            + logTypeCode
            + status
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Sync Logs [GET /research-sys/api/v1/award-sync-logs/]
	 
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
		
### Get Blueprint API specification for Award Sync Logs [GET /research-sys/api/v1/award-sync-logs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Sync Logs.md"
            transfer-encoding:chunked


### Update Award Sync Logs [PUT /research-sys/api/v1/award-sync-logs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Sync Logs [PUT /research-sys/api/v1/award-sync-logs/]

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

### Insert Award Sync Logs [POST /research-sys/api/v1/award-sync-logs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardSyncLogId": "(val)","awardSyncStatusId": "(val)","awardSyncChangeId": "(val)","success": "(val)","logTypeCode": "(val)","status": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Sync Logs [POST /research-sys/api/v1/award-sync-logs/]

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
            
### Delete Award Sync Logs by Key [DELETE /research-sys/api/v1/award-sync-logs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sync Logs [DELETE /research-sys/api/v1/award-sync-logs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Sync Logs with Matching [DELETE /research-sys/api/v1/award-sync-logs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardSyncLogId
            + awardSyncStatusId
            + awardSyncChangeId
            + success
            + logTypeCode
            + status


+ Response 204
