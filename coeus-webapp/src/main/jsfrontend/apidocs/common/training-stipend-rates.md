## Training Stipend Rates [/research-common/api/v1/training-stipend-rates/]

### Get Training Stipend Rates by Key [GET /research-common/api/v1/training-stipend-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Training Stipend Rates [GET /research-common/api/v1/training-stipend-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Training Stipend Rates with Filtering [GET /research-common/api/v1/training-stipend-rates/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 12.
    + careerLevel (optional) - Career Level. Maximum length is 20.
    + experienceLevel (optional) - Experience Level. Maximum length is 3.
    + stipendRate (optional) - Stipend Rate. Maximum length is 10.
    + effectiveDate (optional) - Effective Date. Maximum length is 21.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Training Stipend Rates [GET /research-common/api/v1/training-stipend-rates/]
	                                          
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
    
            {"columns":["id","careerLevel","experienceLevel","stipendRate","effectiveDate","description"],"primaryKey":"id"}
		
### Get Blueprint API specification for Training Stipend Rates [GET /research-common/api/v1/training-stipend-rates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Training Stipend Rates.md"
            transfer-encoding:chunked
### Update Training Stipend Rates [PUT /research-common/api/v1/training-stipend-rates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Training Stipend Rates [PUT /research-common/api/v1/training-stipend-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Training Stipend Rates [POST /research-common/api/v1/training-stipend-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Training Stipend Rates [POST /research-common/api/v1/training-stipend-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Training Stipend Rates by Key [DELETE /research-common/api/v1/training-stipend-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Training Stipend Rates [DELETE /research-common/api/v1/training-stipend-rates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Training Stipend Rates with Matching [DELETE /research-common/api/v1/training-stipend-rates/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 12.
    + careerLevel (optional) - Career Level. Maximum length is 20.
    + experienceLevel (optional) - Experience Level. Maximum length is 3.
    + stipendRate (optional) - Stipend Rate. Maximum length is 10.
    + effectiveDate (optional) - Effective Date. Maximum length is 21.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
