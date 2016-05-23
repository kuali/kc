## Award Subcontracting Budgeted Goals [/award/api/v1/award-subcontracting-budgeted-goals/]

### Get Award Subcontracting Budgeted Goals by Key [GET /award/api/v1/award-subcontracting-budgeted-goals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Award Subcontracting Budgeted Goals [GET /award/api/v1/award-subcontracting-budgeted-goals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Subcontracting Budgeted Goals with Filtering [GET /award/api/v1/award-subcontracting-budgeted-goals/]
    
+ Parameters

    + awardNumber (optional) - Award ID. Maximum length is 12.
    + largeBusinessGoalAmount (optional) - Large Business Goal Amount. Maximum length is 22.
    + smallBusinessGoalAmount (optional) - Small Business Goal Amount. Maximum length is 22.
    + womanOwnedGoalAmount (optional) - Woman Owned Goal Amount. Maximum length is 22.
    + eightADisadvantageGoalAmount (optional) - 8A Disadvantage Goal Amount. Maximum length is 22.
    + hubZoneGoalAmount (optional) - Hub Zone Goal Amount. Maximum length is 22.
    + veteranOwnedGoalAmount (optional) - Veteran Owned Goal Amount. Maximum length is 22.
    + serviceDisabledVeteranOwnedGoalAmount (optional) - Service Disabled Veteran Owned Goal Amount. Maximum length is 22.
    + historicalBlackCollegeGoalAmount (optional) - Historical Black College Goal Amount. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 800.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Subcontracting Budgeted Goals [GET /award/api/v1/award-subcontracting-budgeted-goals/]
	                                          
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
    
            {"columns":["awardNumber","largeBusinessGoalAmount","smallBusinessGoalAmount","womanOwnedGoalAmount","eightADisadvantageGoalAmount","hubZoneGoalAmount","veteranOwnedGoalAmount","serviceDisabledVeteranOwnedGoalAmount","historicalBlackCollegeGoalAmount","comments"],"primaryKey":"awardNumber"}
		
### Get Blueprint API specification for Award Subcontracting Budgeted Goals [GET /award/api/v1/award-subcontracting-budgeted-goals/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Subcontracting Budgeted Goals.md"
            transfer-encoding:chunked
### Update Award Subcontracting Budgeted Goals [PUT /award/api/v1/award-subcontracting-budgeted-goals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Subcontracting Budgeted Goals [PUT /award/api/v1/award-subcontracting-budgeted-goals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Award Subcontracting Budgeted Goals [POST /award/api/v1/award-subcontracting-budgeted-goals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Subcontracting Budgeted Goals [POST /award/api/v1/award-subcontracting-budgeted-goals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
### Delete Award Subcontracting Budgeted Goals by Key [DELETE /award/api/v1/award-subcontracting-budgeted-goals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Subcontracting Budgeted Goals [DELETE /award/api/v1/award-subcontracting-budgeted-goals/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Subcontracting Budgeted Goals with Matching [DELETE /award/api/v1/award-subcontracting-budgeted-goals/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + largeBusinessGoalAmount (optional) - Large Business Goal Amount. Maximum length is 22.
    + smallBusinessGoalAmount (optional) - Small Business Goal Amount. Maximum length is 22.
    + womanOwnedGoalAmount (optional) - Woman Owned Goal Amount. Maximum length is 22.
    + eightADisadvantageGoalAmount (optional) - 8A Disadvantage Goal Amount. Maximum length is 22.
    + hubZoneGoalAmount (optional) - Hub Zone Goal Amount. Maximum length is 22.
    + veteranOwnedGoalAmount (optional) - Veteran Owned Goal Amount. Maximum length is 22.
    + serviceDisabledVeteranOwnedGoalAmount (optional) - Service Disabled Veteran Owned Goal Amount. Maximum length is 22.
    + historicalBlackCollegeGoalAmount (optional) - Historical Black College Goal Amount. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 800.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
