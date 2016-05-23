## Subcontracting Expenditure Category In Date Range Amounts [/award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]

### Get Subcontracting Expenditure Category In Date Range Amounts by Key [GET /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}

### Get All Subcontracting Expenditure Category In Date Range Amounts [GET /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]
	 
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

### Get All Subcontracting Expenditure Category In Date Range Amounts with Filtering [GET /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]
    
+ Parameters

    + awardNumber (optional) - 
    + rangeStartDate (optional) - Range Start Date. Maximum length is 21.
    + rangeEndDate (optional) - Range End Date. Maximum length is 21.
    + largeBusinessExpenditureAmount (optional) - 
    + smallBusinessExpenditureAmount (optional) - 
    + womanOwnedExpenditureAmount (optional) - 
    + eightADisadvantageExpenditureAmount (optional) - 
    + hubZoneExpenditureAmount (optional) - 
    + veteranOwnedExpenditureAmount (optional) - 
    + serviceDisabledVeteranOwnedExpenditureAmount (optional) - 
    + historicalBlackCollegeExpenditureAmount (optional) - 

            
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
			
### Get Schema for Subcontracting Expenditure Category In Date Range Amounts [GET /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]
	                                          
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
    
            {"columns":["awardNumber","rangeStartDate","rangeEndDate","largeBusinessExpenditureAmount","smallBusinessExpenditureAmount","womanOwnedExpenditureAmount","eightADisadvantageExpenditureAmount","hubZoneExpenditureAmount","veteranOwnedExpenditureAmount","serviceDisabledVeteranOwnedExpenditureAmount","historicalBlackCollegeExpenditureAmount"],"primaryKey":"awardNumber"}
		
### Get Blueprint API specification for Subcontracting Expenditure Category In Date Range Amounts [GET /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Subcontracting Expenditure Category In Date Range Amounts.md"
            transfer-encoding:chunked
### Update Subcontracting Expenditure Category In Date Range Amounts [PUT /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Subcontracting Expenditure Category In Date Range Amounts [PUT /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]

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
### Insert Subcontracting Expenditure Category In Date Range Amounts [POST /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardNumber": "(val)","rangeStartDate": "(val)","rangeEndDate": "(val)","largeBusinessExpenditureAmount": "(val)","smallBusinessExpenditureAmount": "(val)","womanOwnedExpenditureAmount": "(val)","eightADisadvantageExpenditureAmount": "(val)","hubZoneExpenditureAmount": "(val)","veteranOwnedExpenditureAmount": "(val)","serviceDisabledVeteranOwnedExpenditureAmount": "(val)","historicalBlackCollegeExpenditureAmount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Subcontracting Expenditure Category In Date Range Amounts [POST /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]

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
### Delete Subcontracting Expenditure Category In Date Range Amounts by Key [DELETE /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Subcontracting Expenditure Category In Date Range Amounts [DELETE /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Subcontracting Expenditure Category In Date Range Amounts with Matching [DELETE /award/api/v1/subcontracting-expenditure-category-in-date-range-amounts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardNumber (optional) - 
    + rangeStartDate (optional) - Range Start Date. Maximum length is 21.
    + rangeEndDate (optional) - Range End Date. Maximum length is 21.
    + largeBusinessExpenditureAmount (optional) - 
    + smallBusinessExpenditureAmount (optional) - 
    + womanOwnedExpenditureAmount (optional) - 
    + eightADisadvantageExpenditureAmount (optional) - 
    + hubZoneExpenditureAmount (optional) - 
    + veteranOwnedExpenditureAmount (optional) - 
    + serviceDisabledVeteranOwnedExpenditureAmount (optional) - 
    + historicalBlackCollegeExpenditureAmount (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
