## Sponsor Hierarchies [/research-sys/api/v1/sponsor-hierarchies/]

### Get Sponsor Hierarchies by Key [GET /research-sys/api/v1/sponsor-hierarchies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}

### Get All Sponsor Hierarchies [GET /research-sys/api/v1/sponsor-hierarchies/]
	 
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

### Get All Sponsor Hierarchies with Filtering [GET /research-sys/api/v1/sponsor-hierarchies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + hierarchyName
            + sponsorCode
            + level1
            + level10
            + level10Sortid
            + level1Sortid
            + level2
            + level2Sortid
            + level3
            + level3Sortid
            + level4
            + level4Sortid
            + level5
            + level5Sortid
            + level6
            + level6Sortid
            + level7
            + level7Sortid
            + level8
            + level8Sortid
            + level9
            + level9Sortid
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"},
              {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsor Hierarchies [GET /research-sys/api/v1/sponsor-hierarchies/]
	 
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
		
### Get Blueprint API specification for Sponsor Hierarchies [GET /research-sys/api/v1/sponsor-hierarchies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsor Hierarchies.md"
            transfer-encoding:chunked


### Update Sponsor Hierarchies [PUT /research-sys/api/v1/sponsor-hierarchies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsor Hierarchies [PUT /research-sys/api/v1/sponsor-hierarchies/]

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

### Insert Sponsor Hierarchies [POST /research-sys/api/v1/sponsor-hierarchies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"hierarchyName": "(val)","sponsorCode": "(val)","level1": "(val)","level10": "(val)","level10Sortid": "(val)","level1Sortid": "(val)","level2": "(val)","level2Sortid": "(val)","level3": "(val)","level3Sortid": "(val)","level4": "(val)","level4Sortid": "(val)","level5": "(val)","level5Sortid": "(val)","level6": "(val)","level6Sortid": "(val)","level7": "(val)","level7Sortid": "(val)","level8": "(val)","level8Sortid": "(val)","level9": "(val)","level9Sortid": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsor Hierarchies [POST /research-sys/api/v1/sponsor-hierarchies/]

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
            
### Delete Sponsor Hierarchies by Key [DELETE /research-sys/api/v1/sponsor-hierarchies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Hierarchies [DELETE /research-sys/api/v1/sponsor-hierarchies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sponsor Hierarchies with Matching [DELETE /research-sys/api/v1/sponsor-hierarchies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + hierarchyName
            + sponsorCode
            + level1
            + level10
            + level10Sortid
            + level1Sortid
            + level2
            + level2Sortid
            + level3
            + level3Sortid
            + level4
            + level4Sortid
            + level5
            + level5Sortid
            + level6
            + level6Sortid
            + level7
            + level7Sortid
            + level8
            + level8Sortid
            + level9
            + level9Sortid


+ Response 204
