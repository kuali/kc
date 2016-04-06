## Organization Ynqs [/research-sys/api/v1/organization-ynqs/]

### Get Organization Ynqs by Key [GET /research-sys/api/v1/organization-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}

### Get All Organization Ynqs [GET /research-sys/api/v1/organization-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Organization Ynqs with Filtering [GET /research-sys/api/v1/organization-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + organizationId
            + questionId
            + answer
            + explanation
            + reviewDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Organization Ynqs [GET /research-sys/api/v1/organization-ynqs/]
	 
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
		
### Get Blueprint API specification for Organization Ynqs [GET /research-sys/api/v1/organization-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Organization Ynqs.md"
            transfer-encoding:chunked


### Update Organization Ynqs [PUT /research-sys/api/v1/organization-ynqs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Organization Ynqs [PUT /research-sys/api/v1/organization-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Organization Ynqs [POST /research-sys/api/v1/organization-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Organization Ynqs [POST /research-sys/api/v1/organization-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Organization Ynqs by Key [DELETE /research-sys/api/v1/organization-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Ynqs [DELETE /research-sys/api/v1/organization-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Organization Ynqs with Matching [DELETE /research-sys/api/v1/organization-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + organizationId
            + questionId
            + answer
            + explanation
            + reviewDate


+ Response 204
