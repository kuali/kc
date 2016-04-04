## Training Stipend Rates [/research-sys/api/v1/training-stipend-rates/]

### Get Training Stipend Rates by Key [GET /research-sys/api/v1/training-stipend-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Training Stipend Rates [GET /research-sys/api/v1/training-stipend-rates/]
	 
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

### Get All Training Stipend Rates with Filtering [GET /research-sys/api/v1/training-stipend-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + careerLevel
            + experienceLevel
            + stipendRate
            + effectiveDate
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Training Stipend Rates [GET /research-sys/api/v1/training-stipend-rates/]
	 
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
		
### Get Blueprint API specification for Training Stipend Rates [GET /research-sys/api/v1/training-stipend-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Training Stipend Rates.md"
            transfer-encoding:chunked


### Update Training Stipend Rates [PUT /research-sys/api/v1/training-stipend-rates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Training Stipend Rates [PUT /research-sys/api/v1/training-stipend-rates/]

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

### Insert Training Stipend Rates [POST /research-sys/api/v1/training-stipend-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","careerLevel": "(val)","experienceLevel": "(val)","stipendRate": "(val)","effectiveDate": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Training Stipend Rates [POST /research-sys/api/v1/training-stipend-rates/]

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
            
### Delete Training Stipend Rates by Key [DELETE /research-sys/api/v1/training-stipend-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Training Stipend Rates [DELETE /research-sys/api/v1/training-stipend-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Training Stipend Rates with Matching [DELETE /research-sys/api/v1/training-stipend-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + careerLevel
            + experienceLevel
            + stipendRate
            + effectiveDate
            + description


+ Response 204
