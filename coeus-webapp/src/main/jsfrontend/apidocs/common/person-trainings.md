## Person Trainings [/research-sys/api/v1/person-trainings/]

### Get Person Trainings by Key [GET /research-sys/api/v1/person-trainings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Trainings [GET /research-sys/api/v1/person-trainings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Trainings with Filtering [GET /research-sys/api/v1/person-trainings/]
    
+ Parameters

        + personTrainingId
            + personId
            + trainingNumber
            + trainingCode
            + dateRequested
            + dateSubmitted
            + dateAcknowledged
            + followupDate
            + score
            + comments
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Trainings [GET /research-sys/api/v1/person-trainings/]
	                                          
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
    
            {"columns":["personTrainingId","personId","trainingNumber","trainingCode","dateRequested","dateSubmitted","dateAcknowledged","followupDate","score","comments","active"],"primaryKey":"personTrainingId"}
		
### Get Blueprint API specification for Person Trainings [GET /research-sys/api/v1/person-trainings/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Trainings.md"
            transfer-encoding:chunked


### Update Person Trainings [PUT /research-sys/api/v1/person-trainings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Trainings [PUT /research-sys/api/v1/person-trainings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Trainings [POST /research-sys/api/v1/person-trainings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Trainings [POST /research-sys/api/v1/person-trainings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"personTrainingId": "(val)","personId": "(val)","trainingNumber": "(val)","trainingCode": "(val)","dateRequested": "(val)","dateSubmitted": "(val)","dateAcknowledged": "(val)","followupDate": "(val)","score": "(val)","comments": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Trainings by Key [DELETE /research-sys/api/v1/person-trainings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Trainings [DELETE /research-sys/api/v1/person-trainings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Trainings with Matching [DELETE /research-sys/api/v1/person-trainings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + personTrainingId
            + personId
            + trainingNumber
            + trainingCode
            + dateRequested
            + dateSubmitted
            + dateAcknowledged
            + followupDate
            + score
            + comments
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
