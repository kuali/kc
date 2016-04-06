## Proposal Person Degrees [/research-sys/api/v1/proposal-person-degrees/]

### Get Proposal Person Degrees by Key [GET /research-sys/api/v1/proposal-person-degrees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}

### Get All Proposal Person Degrees [GET /research-sys/api/v1/proposal-person-degrees/]
	 
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

### Get All Proposal Person Degrees with Filtering [GET /research-sys/api/v1/proposal-person-degrees/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + degreeSequenceNumber
            + graduationYear
            + degreeCode
            + degree
            + fieldOfStudy
            + specialization
            + school
            + schoolIdCode
            + schoolId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"},
              {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Person Degrees [GET /research-sys/api/v1/proposal-person-degrees/]
	 
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
		
### Get Blueprint API specification for Proposal Person Degrees [GET /research-sys/api/v1/proposal-person-degrees/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Person Degrees.md"
            transfer-encoding:chunked


### Update Proposal Person Degrees [PUT /research-sys/api/v1/proposal-person-degrees/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Person Degrees [PUT /research-sys/api/v1/proposal-person-degrees/]

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

### Insert Proposal Person Degrees [POST /research-sys/api/v1/proposal-person-degrees/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"degreeSequenceNumber": "(val)","graduationYear": "(val)","degreeCode": "(val)","degree": "(val)","fieldOfStudy": "(val)","specialization": "(val)","school": "(val)","schoolIdCode": "(val)","schoolId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Person Degrees [POST /research-sys/api/v1/proposal-person-degrees/]

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
            
### Delete Proposal Person Degrees by Key [DELETE /research-sys/api/v1/proposal-person-degrees/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Degrees [DELETE /research-sys/api/v1/proposal-person-degrees/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Person Degrees with Matching [DELETE /research-sys/api/v1/proposal-person-degrees/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + degreeSequenceNumber
            + graduationYear
            + degreeCode
            + degree
            + fieldOfStudy
            + specialization
            + school
            + schoolIdCode
            + schoolId


+ Response 204
