## Sponsor Hierarchies [/research-common/api/v1/sponsor-hierarchies/]

### Get Sponsor Hierarchies by Key [GET /research-common/api/v1/sponsor-hierarchies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}

### Get All Sponsor Hierarchies [GET /research-common/api/v1/sponsor-hierarchies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"},
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sponsor Hierarchies with Filtering [GET /research-common/api/v1/sponsor-hierarchies/]
    
+ Parameters

    + hierarchyName (optional) - Hierarchy Name. Maximum length is 100.
    + sponsorCode (optional) - Sponsor Code. Maximum length is 6.
    + level1 (optional) - Level1. Maximum length is 50.
    + level10 (optional) - Level10. Maximum length is 50.
    + level10Sortid (optional) - Level10 Sortid. Maximum length is 4.
    + level1Sortid (optional) - Level1 Sortid. Maximum length is 4.
    + level2 (optional) - Level2. Maximum length is 50.
    + level2Sortid (optional) - Level2 Sortid. Maximum length is 4.
    + level3 (optional) - Level3. Maximum length is 50.
    + level3Sortid (optional) - Level3 Sortid. Maximum length is 4.
    + level4 (optional) - Level4. Maximum length is 50.
    + level4Sortid (optional) - Level4 Sortid. Maximum length is 4.
    + level5 (optional) - Level5. Maximum length is 50.
    + level5Sortid (optional) - Level5 Sortid. Maximum length is 4.
    + level6 (optional) - Level6. Maximum length is 50.
    + level6Sortid (optional) - Level6 Sortid. Maximum length is 4.
    + level7 (optional) - Level7. Maximum length is 50.
    + level7Sortid (optional) - Level7 Sortid. Maximum length is 4.
    + level8 (optional) - Level8. Maximum length is 50.
    + level8Sortid (optional) - Level8 Sortid. Maximum length is 4.
    + level9 (optional) - Level9. Maximum length is 50.
    + level9Sortid (optional) - Level9 Sortid. Maximum length is 4.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"},
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsor Hierarchies [GET /research-common/api/v1/sponsor-hierarchies/]
	                                          
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
    
            {"columns":["hierarchyName","sponsorCode","level1","level10","level10Sortid","level1Sortid","level2","level2Sortid","level3","level3Sortid","level4","level4Sortid","level5","level5Sortid","level6","level6Sortid","level7","level7Sortid","level8","level8Sortid","level9","level9Sortid"],"primaryKey":"hierarchyName:sponsorCode"}
		
### Get Blueprint API specification for Sponsor Hierarchies [GET /research-common/api/v1/sponsor-hierarchies/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsor Hierarchies.md"
            transfer-encoding:chunked
### Update Sponsor Hierarchies [PUT /research-common/api/v1/sponsor-hierarchies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsor Hierarchies [PUT /research-common/api/v1/sponsor-hierarchies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"},
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sponsor Hierarchies [POST /research-common/api/v1/sponsor-hierarchies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsor Hierarchies [POST /research-common/api/v1/sponsor-hierarchies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"},
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"},
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sponsor Hierarchies by Key [DELETE /research-common/api/v1/sponsor-hierarchies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Hierarchies [DELETE /research-common/api/v1/sponsor-hierarchies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Hierarchies with Matching [DELETE /research-common/api/v1/sponsor-hierarchies/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + hierarchyName (optional) - Hierarchy Name. Maximum length is 100.
    + sponsorCode (optional) - Sponsor Code. Maximum length is 6.
    + level1 (optional) - Level1. Maximum length is 50.
    + level10 (optional) - Level10. Maximum length is 50.
    + level10Sortid (optional) - Level10 Sortid. Maximum length is 4.
    + level1Sortid (optional) - Level1 Sortid. Maximum length is 4.
    + level2 (optional) - Level2. Maximum length is 50.
    + level2Sortid (optional) - Level2 Sortid. Maximum length is 4.
    + level3 (optional) - Level3. Maximum length is 50.
    + level3Sortid (optional) - Level3 Sortid. Maximum length is 4.
    + level4 (optional) - Level4. Maximum length is 50.
    + level4Sortid (optional) - Level4 Sortid. Maximum length is 4.
    + level5 (optional) - Level5. Maximum length is 50.
    + level5Sortid (optional) - Level5 Sortid. Maximum length is 4.
    + level6 (optional) - Level6. Maximum length is 50.
    + level6Sortid (optional) - Level6 Sortid. Maximum length is 4.
    + level7 (optional) - Level7. Maximum length is 50.
    + level7Sortid (optional) - Level7 Sortid. Maximum length is 4.
    + level8 (optional) - Level8. Maximum length is 50.
    + level8Sortid (optional) - Level8 Sortid. Maximum length is 4.
    + level9 (optional) - Level9. Maximum length is 50.
    + level9Sortid (optional) - Level9 Sortid. Maximum length is 4.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
