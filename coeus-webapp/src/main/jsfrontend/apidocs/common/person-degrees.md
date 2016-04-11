## Person Degrees [/research-sys/api/v1/person-degrees/]

### Get Person Degrees by Key [GET /research-sys/api/v1/person-degrees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}

### Get All Person Degrees [GET /research-sys/api/v1/person-degrees/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Degrees with Filtering [GET /research-sys/api/v1/person-degrees/]
    
+ Parameters

        + degreeId
            + personId
            + degreeCode
            + degree
            + graduationYear
            + fieldOfStudy
            + specialization
            + school
            + schoolIdCode
            + schoolId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Degrees [GET /research-sys/api/v1/person-degrees/]
	                                          
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
    
            {"columns":["degreeId","personId","degreeCode","degree","graduationYear","fieldOfStudy","specialization","school","schoolIdCode","schoolId"],"primaryKey":"degreeId"}
		
### Get Blueprint API specification for Person Degrees [GET /research-sys/api/v1/person-degrees/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Degrees.md"
            transfer-encoding:chunked


### Update Person Degrees [PUT /research-sys/api/v1/person-degrees/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Degrees [PUT /research-sys/api/v1/person-degrees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Degrees [POST /research-sys/api/v1/person-degrees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Degrees [POST /research-sys/api/v1/person-degrees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Degrees by Key [DELETE /research-sys/api/v1/person-degrees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Degrees [DELETE /research-sys/api/v1/person-degrees/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Degrees with Matching [DELETE /research-sys/api/v1/person-degrees/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + degreeId
            + personId
            + degreeCode
            + degree
            + graduationYear
            + fieldOfStudy
            + specialization
            + school
            + schoolIdCode
            + schoolId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
