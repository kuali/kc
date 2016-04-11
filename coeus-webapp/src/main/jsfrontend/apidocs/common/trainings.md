## Trainings [/research-sys/api/v1/trainings/]

### Get Trainings by Key [GET /research-sys/api/v1/trainings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Trainings [GET /research-sys/api/v1/trainings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Trainings with Filtering [GET /research-sys/api/v1/trainings/]
    
+ Parameters

        + trainingCode
            + description

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Trainings [GET /research-sys/api/v1/trainings/]
	                                          
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
    
            {"columns":["trainingCode","description"],"primaryKey":"trainingCode"}
		
### Get Blueprint API specification for Trainings [GET /research-sys/api/v1/trainings/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Trainings.md"
            transfer-encoding:chunked


### Update Trainings [PUT /research-sys/api/v1/trainings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Trainings [PUT /research-sys/api/v1/trainings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Trainings [POST /research-sys/api/v1/trainings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Trainings [POST /research-sys/api/v1/trainings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"trainingCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Trainings by Key [DELETE /research-sys/api/v1/trainings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Trainings [DELETE /research-sys/api/v1/trainings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Trainings with Matching [DELETE /research-sys/api/v1/trainings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + trainingCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
