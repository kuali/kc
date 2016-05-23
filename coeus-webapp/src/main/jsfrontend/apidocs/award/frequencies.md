## Frequencies [/award/api/v1/frequencies/]

### Get Frequencies by Key [GET /award/api/v1/frequencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Frequencies [GET /award/api/v1/frequencies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Frequencies with Filtering [GET /award/api/v1/frequencies/]
    
+ Parameters

    + frequencyCode (optional) - Frequency Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + numberOfDays (optional) - Number Of Days. Maximum length is 22.
    + numberOfMonths (optional) - Number Of Months. Maximum length is 22.
    + repeatFlag (optional) - Repeat Flag. Maximum length is 1.
    + advanceNumberOfDays (optional) - Advance Number Of Days. Maximum length is 22.
    + advanceNumberOfMonths (optional) - Advance Number Of Months. Maximum length is 22.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Frequencies [GET /award/api/v1/frequencies/]
	                                          
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
    
            {"columns":["frequencyCode","description","numberOfDays","numberOfMonths","repeatFlag","advanceNumberOfDays","advanceNumberOfMonths","active"],"primaryKey":"frequencyCode"}
		
### Get Blueprint API specification for Frequencies [GET /award/api/v1/frequencies/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Frequencies.md"
            transfer-encoding:chunked
### Update Frequencies [PUT /award/api/v1/frequencies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Frequencies [PUT /award/api/v1/frequencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Frequencies [POST /award/api/v1/frequencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Frequencies [POST /award/api/v1/frequencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyCode": "(val)","description": "(val)","numberOfDays": "(val)","numberOfMonths": "(val)","repeatFlag": "(val)","advanceNumberOfDays": "(val)","advanceNumberOfMonths": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Frequencies by Key [DELETE /award/api/v1/frequencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Frequencies [DELETE /award/api/v1/frequencies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Frequencies with Matching [DELETE /award/api/v1/frequencies/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + frequencyCode (optional) - Frequency Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + numberOfDays (optional) - Number Of Days. Maximum length is 22.
    + numberOfMonths (optional) - Number Of Months. Maximum length is 22.
    + repeatFlag (optional) - Repeat Flag. Maximum length is 1.
    + advanceNumberOfDays (optional) - Advance Number Of Days. Maximum length is 22.
    + advanceNumberOfMonths (optional) - Advance Number Of Months. Maximum length is 22.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
