## Award Subcontracting Budgeted Goals [/research-sys/api/v1/award-subcontracting-budgeted-goals/]

### Get Award Subcontracting Budgeted Goals by Key [GET /research-sys/api/v1/award-subcontracting-budgeted-goals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Award Subcontracting Budgeted Goals [GET /research-sys/api/v1/award-subcontracting-budgeted-goals/]
	 
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

### Get All Award Subcontracting Budgeted Goals with Filtering [GET /research-sys/api/v1/award-subcontracting-budgeted-goals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardNumber
            + largeBusinessGoalAmount
            + smallBusinessGoalAmount
            + womanOwnedGoalAmount
            + eightADisadvantageGoalAmount
            + hubZoneGoalAmount
            + veteranOwnedGoalAmount
            + serviceDisabledVeteranOwnedGoalAmount
            + historicalBlackCollegeGoalAmount
            + comments
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Subcontracting Budgeted Goals [GET /research-sys/api/v1/award-subcontracting-budgeted-goals/]
	 
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
		
### Get Blueprint API specification for Award Subcontracting Budgeted Goals [GET /research-sys/api/v1/award-subcontracting-budgeted-goals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Subcontracting Budgeted Goals.md"
            transfer-encoding:chunked


### Update Award Subcontracting Budgeted Goals [PUT /research-sys/api/v1/award-subcontracting-budgeted-goals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Subcontracting Budgeted Goals [PUT /research-sys/api/v1/award-subcontracting-budgeted-goals/]

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

### Insert Award Subcontracting Budgeted Goals [POST /research-sys/api/v1/award-subcontracting-budgeted-goals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardNumber": "(val)","largeBusinessGoalAmount": "(val)","smallBusinessGoalAmount": "(val)","womanOwnedGoalAmount": "(val)","eightADisadvantageGoalAmount": "(val)","hubZoneGoalAmount": "(val)","veteranOwnedGoalAmount": "(val)","serviceDisabledVeteranOwnedGoalAmount": "(val)","historicalBlackCollegeGoalAmount": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Subcontracting Budgeted Goals [POST /research-sys/api/v1/award-subcontracting-budgeted-goals/]

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
            
### Delete Award Subcontracting Budgeted Goals by Key [DELETE /research-sys/api/v1/award-subcontracting-budgeted-goals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Subcontracting Budgeted Goals [DELETE /research-sys/api/v1/award-subcontracting-budgeted-goals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Subcontracting Budgeted Goals with Matching [DELETE /research-sys/api/v1/award-subcontracting-budgeted-goals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardNumber
            + largeBusinessGoalAmount
            + smallBusinessGoalAmount
            + womanOwnedGoalAmount
            + eightADisadvantageGoalAmount
            + hubZoneGoalAmount
            + veteranOwnedGoalAmount
            + serviceDisabledVeteranOwnedGoalAmount
            + historicalBlackCollegeGoalAmount
            + comments


+ Response 204
