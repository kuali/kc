## Person Degrees [/research-common/api/v1/person-degrees/]

### Get Person Degrees by Key [GET /research-common/api/v1/person-degrees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}

### Get All Person Degrees [GET /research-common/api/v1/person-degrees/]
	 
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

### Get All Person Degrees with Filtering [GET /research-common/api/v1/person-degrees/]
    
+ Parameters

    + degreeId (optional) - 
    + personId (optional) - KcPersonExtendedAttributes Id. Maximum length is 40.
    + degreeCode (optional) - Degree Code. Maximum length is 6.
    + degree (optional) - Degree. Maximum length is 80.
    + graduationYear (optional) - Graduation Year. Maximum length is 4.
    + fieldOfStudy (optional) - Field of Study. Maximum length is 80.
    + specialization (optional) - Specialization. Maximum length is 80.
    + school (optional) - School. Maximum length is 50.
    + schoolIdCode (optional) - School Id Code. Maximum length is 3.
    + schoolId (optional) - School Id. Maximum length is 20.

            
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
			
### Get Schema for Person Degrees [GET /research-common/api/v1/person-degrees/]
	                                          
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
		
### Get Blueprint API specification for Person Degrees [GET /research-common/api/v1/person-degrees/]
	 
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
### Update Person Degrees [PUT /research-common/api/v1/person-degrees/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Degrees [PUT /research-common/api/v1/person-degrees/]

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
### Insert Person Degrees [POST /research-common/api/v1/person-degrees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"degreeId": "(val)","personId": "(val)","degreeCode": "(val)","degree": "(val)","graduationYear": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Degrees [POST /research-common/api/v1/person-degrees/]

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
### Delete Person Degrees by Key [DELETE /research-common/api/v1/person-degrees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Degrees [DELETE /research-common/api/v1/person-degrees/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Degrees with Matching [DELETE /research-common/api/v1/person-degrees/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + degreeId (optional) - 
    + personId (optional) - KcPersonExtendedAttributes Id. Maximum length is 40.
    + degreeCode (optional) - Degree Code. Maximum length is 6.
    + degree (optional) - Degree. Maximum length is 80.
    + graduationYear (optional) - Graduation Year. Maximum length is 4.
    + fieldOfStudy (optional) - Field of Study. Maximum length is 80.
    + specialization (optional) - Specialization. Maximum length is 80.
    + school (optional) - School. Maximum length is 50.
    + schoolIdCode (optional) - School Id Code. Maximum length is 3.
    + schoolId (optional) - School Id. Maximum length is 20.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
