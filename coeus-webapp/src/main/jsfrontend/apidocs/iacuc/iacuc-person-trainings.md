## Iacuc Person Trainings [/iacuc/api/v1/iacuc-person-trainings/]

### Get Iacuc Person Trainings by Key [GET /iacuc/api/v1/iacuc-person-trainings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Person Trainings [GET /iacuc/api/v1/iacuc-person-trainings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"},
              {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Person Trainings with Filtering [GET /iacuc/api/v1/iacuc-person-trainings/]
    
+ Parameters

    + iacucPersonTrainingId (optional) - IACUC Person Training Id. Maximum length is 5.
    + personTrainingId (optional) - Person Training Id. Maximum length is 22.
    + personId (optional) - Person Id. Maximum length is 40.
    + speciesCode (optional) - Species Code. Maximum length is 4.
    + procedureCode (optional) - Procedure Code. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"},
              {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Person Trainings [GET /iacuc/api/v1/iacuc-person-trainings/]
	                                          
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
    
            {"columns":["iacucPersonTrainingId","personTrainingId","personId","speciesCode","procedureCode"],"primaryKey":"iacucPersonTrainingId"}
		
### Get Blueprint API specification for Iacuc Person Trainings [GET /iacuc/api/v1/iacuc-person-trainings/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Person Trainings.md"
            transfer-encoding:chunked


### Update Iacuc Person Trainings [PUT /iacuc/api/v1/iacuc-person-trainings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Person Trainings [PUT /iacuc/api/v1/iacuc-person-trainings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"},
              {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Person Trainings [POST /iacuc/api/v1/iacuc-person-trainings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Person Trainings [POST /iacuc/api/v1/iacuc-person-trainings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"},
              {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"},
              {"iacucPersonTrainingId": "(val)","personTrainingId": "(val)","personId": "(val)","speciesCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Person Trainings by Key [DELETE /iacuc/api/v1/iacuc-person-trainings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Person Trainings [DELETE /iacuc/api/v1/iacuc-person-trainings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Person Trainings with Matching [DELETE /iacuc/api/v1/iacuc-person-trainings/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucPersonTrainingId (optional) - IACUC Person Training Id. Maximum length is 5.
    + personTrainingId (optional) - Person Training Id. Maximum length is 22.
    + personId (optional) - Person Id. Maximum length is 40.
    + speciesCode (optional) - Species Code. Maximum length is 4.
    + procedureCode (optional) - Procedure Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
