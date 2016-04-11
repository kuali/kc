## Award Sync Changes [/research-sys/api/v1/award-sync-changes/]

### Get Award Sync Changes by Key [GET /research-sys/api/v1/award-sync-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}

### Get All Award Sync Changes [GET /research-sys/api/v1/award-sync-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"},
              {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Sync Changes with Filtering [GET /research-sys/api/v1/award-sync-changes/]
    
+ Parameters

        + awardSyncChangeId
            + awardId
            + xml
            + className
            + attrName
            + objectDesc
            + dataDesc
            + syncType
            + syncDescendants
            + syncFabricated
            + syncCostSharing

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"},
              {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Sync Changes [GET /research-sys/api/v1/award-sync-changes/]
	                                          
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
    
            {"columns":["awardSyncChangeId","awardId","xml","className","attrName","objectDesc","dataDesc","syncType","syncDescendants","syncFabricated","syncCostSharing"],"primaryKey":"awardSyncChangeId"}
		
### Get Blueprint API specification for Award Sync Changes [GET /research-sys/api/v1/award-sync-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Sync Changes.md"
            transfer-encoding:chunked


### Update Award Sync Changes [PUT /research-sys/api/v1/award-sync-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Sync Changes [PUT /research-sys/api/v1/award-sync-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"},
              {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Sync Changes [POST /research-sys/api/v1/award-sync-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Sync Changes [POST /research-sys/api/v1/award-sync-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"},
              {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"},
              {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Sync Changes by Key [DELETE /research-sys/api/v1/award-sync-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sync Changes [DELETE /research-sys/api/v1/award-sync-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sync Changes with Matching [DELETE /research-sys/api/v1/award-sync-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardSyncChangeId
            + awardId
            + xml
            + className
            + attrName
            + objectDesc
            + dataDesc
            + syncType
            + syncDescendants
            + syncFabricated
            + syncCostSharing

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
