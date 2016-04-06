## Subcontracting Expenditure Category In Date Range Amounts [/research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]

### Get Subcontracting Expenditure Category In Date Range Amounts by Key [GET /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}

### Get All Subcontracting Expenditure Category In Date Range Amounts [GET /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
            ]

### Get All Subcontracting Expenditure Category In Date Range Amounts with Filtering [GET /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardNumber
            + rangeStartDate
            + rangeEndDate
            + largeBusinessExpenditureAmount
            + smallBusinessExpenditureAmount
            + womanOwnedExpenditureAmount
            + eightADisadvantageExpenditureAmount
            + hubZoneExpenditureAmount
            + veteranOwnedExpenditureAmount
            + serviceDisabledVeteranOwnedExpenditureAmount
            + historicalBlackCollegeExpenditureAmount
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Subcontracting Expenditure Category In Date Range Amounts [GET /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]
	 
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
		
### Get Blueprint API specification for Subcontracting Expenditure Category In Date Range Amounts [GET /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Subcontracting Expenditure Category In Date Range Amounts.md"
            transfer-encoding:chunked


### Update Subcontracting Expenditure Category In Date Range Amounts [PUT /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Subcontracting Expenditure Category In Date Range Amounts [PUT /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Subcontracting Expenditure Category In Date Range Amounts [POST /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Subcontracting Expenditure Category In Date Range Amounts [POST /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Subcontracting Expenditure Category In Date Range Amounts by Key [DELETE /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Subcontracting Expenditure Category In Date Range Amounts [DELETE /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Subcontracting Expenditure Category In Date Range Amounts with Matching [DELETE /research-sys/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardNumber
            + rangeStartDate
            + rangeEndDate
            + largeBusinessExpenditureAmount
            + smallBusinessExpenditureAmount
            + womanOwnedExpenditureAmount
            + eightADisadvantageExpenditureAmount
            + hubZoneExpenditureAmount
            + veteranOwnedExpenditureAmount
            + serviceDisabledVeteranOwnedExpenditureAmount
            + historicalBlackCollegeExpenditureAmount


+ Response 204
