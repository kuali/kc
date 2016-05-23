## Award Sync Changes [/award/api/v1/award-sync-changes/]

### Get Award Sync Changes by Key [GET /award/api/v1/award-sync-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}

### Get All Award Sync Changes [GET /award/api/v1/award-sync-changes/]
	 
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

### Get All Award Sync Changes with Filtering [GET /award/api/v1/award-sync-changes/]
    
+ Parameters

    + awardSyncChangeId (optional) - Award Sync Change Id. Maximum length is 40.
    + awardId (optional) - Award Id. Maximum length is 12.
    + xml (optional) - XML describing the change for persistence. Maximum length is 999999999.
    + className (optional) - Class Name. Maximum length is 100.
    + attrName (optional) - Attribute Name. Maximum length is 50.
    + objectDesc (optional) - Text description of the object changed. Maximum length is 500.
    + dataDesc (optional) - Text description of the data changed. Maximum length is 500.
    + syncType (optional) - Type of syncronization. Maximum length is 1.
    + syncDescendants (optional) - Syncronize Descendents. Maximum length is 6.
    + syncFabricated (optional) - Syncronize Fabricated Descendents. Maximum length is 1.
    + syncCostSharing (optional) - Syncronize Cost Sharing Accounts. Maximum length is 1.

            
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
			
### Get Schema for Award Sync Changes [GET /award/api/v1/award-sync-changes/]
	                                          
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
		
### Get Blueprint API specification for Award Sync Changes [GET /award/api/v1/award-sync-changes/]
	 
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
### Update Award Sync Changes [PUT /award/api/v1/award-sync-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Sync Changes [PUT /award/api/v1/award-sync-changes/]

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
### Insert Award Sync Changes [POST /award/api/v1/award-sync-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardSyncChangeId": "(val)","awardId": "(val)","xml": "(val)","className": "(val)","attrName": "(val)","objectDesc": "(val)","dataDesc": "(val)","syncType": "(val)","syncDescendants": "(val)","syncFabricated": "(val)","syncCostSharing": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Sync Changes [POST /award/api/v1/award-sync-changes/]

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
### Delete Award Sync Changes by Key [DELETE /award/api/v1/award-sync-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sync Changes [DELETE /award/api/v1/award-sync-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sync Changes with Matching [DELETE /award/api/v1/award-sync-changes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardSyncChangeId (optional) - Award Sync Change Id. Maximum length is 40.
    + awardId (optional) - Award Id. Maximum length is 12.
    + xml (optional) - XML describing the change for persistence. Maximum length is 999999999.
    + className (optional) - Class Name. Maximum length is 100.
    + attrName (optional) - Attribute Name. Maximum length is 50.
    + objectDesc (optional) - Text description of the object changed. Maximum length is 500.
    + dataDesc (optional) - Text description of the data changed. Maximum length is 500.
    + syncType (optional) - Type of syncronization. Maximum length is 1.
    + syncDescendants (optional) - Syncronize Descendents. Maximum length is 6.
    + syncFabricated (optional) - Syncronize Fabricated Descendents. Maximum length is 1.
    + syncCostSharing (optional) - Syncronize Cost Sharing Accounts. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
