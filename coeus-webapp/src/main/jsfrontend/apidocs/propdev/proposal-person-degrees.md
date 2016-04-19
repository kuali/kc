## Proposal Person Degrees [/propdev/api/v1/proposal-person-degrees/]

### Get Proposal Person Degrees by Key [GET /propdev/api/v1/proposal-person-degrees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}

### Get All Proposal Person Degrees [GET /propdev/api/v1/proposal-person-degrees/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Person Degrees with Filtering [GET /propdev/api/v1/proposal-person-degrees/]
    
+ Parameters

    + degreeSequenceNumber (optional) - Degree Sequence Number. Maximum length is 3.
    + graduationYear (optional) - Graduation Year. Maximum length is 4.
    + degreeCode (optional) - Degree Code. Maximum length is 6.
    + degree (optional) - Degree. Maximum length is 80.
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
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Person Degrees [GET /propdev/api/v1/proposal-person-degrees/]
	                                          
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
    
            {"columns":["degreeSequenceNumber","graduationYear","degreeCode","degree","fieldOfStudy","specialization","school","schoolIdCode","schoolId"],"primaryKey":"degreeSequenceNumber:proposalPerson"}
		
### Get Blueprint API specification for Proposal Person Degrees [GET /propdev/api/v1/proposal-person-degrees/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Person Degrees.md"
            transfer-encoding:chunked


### Update Proposal Person Degrees [PUT /propdev/api/v1/proposal-person-degrees/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Person Degrees [PUT /propdev/api/v1/proposal-person-degrees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Person Degrees [POST /propdev/api/v1/proposal-person-degrees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Person Degrees [POST /propdev/api/v1/proposal-person-degrees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Person Degrees by Key [DELETE /propdev/api/v1/proposal-person-degrees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Degrees [DELETE /propdev/api/v1/proposal-person-degrees/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Degrees with Matching [DELETE /propdev/api/v1/proposal-person-degrees/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + degreeSequenceNumber (optional) - Degree Sequence Number. Maximum length is 3.
    + graduationYear (optional) - Graduation Year. Maximum length is 4.
    + degreeCode (optional) - Degree Code. Maximum length is 6.
    + degree (optional) - Degree. Maximum length is 80.
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
