## Organization Ynqs [/research-common/api/v1/organization-ynqs/]

### Get Organization Ynqs by Key [GET /research-common/api/v1/organization-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}

### Get All Organization Ynqs [GET /research-common/api/v1/organization-ynqs/]
	 
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

### Get All Organization Ynqs with Filtering [GET /research-common/api/v1/organization-ynqs/]
    
+ Parameters

    + organizationId (optional) - Organization Id. Maximum length is 8.
    + questionId (optional) - Question Id. Maximum length is 4.
    + answer (optional) - Answer. Maximum length is 1.
    + explanation (optional) - Explanation. Maximum length is 400.
    + reviewDate (optional) - Review Date. Maximum length is 10.

            
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
			
### Get Schema for Organization Ynqs [GET /research-common/api/v1/organization-ynqs/]
	                                          
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
    
            {"columns":["organizationId","questionId","answer","explanation","reviewDate"],"primaryKey":"organizationId:questionId"}
		
### Get Blueprint API specification for Organization Ynqs [GET /research-common/api/v1/organization-ynqs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Organization Ynqs.md"
            transfer-encoding:chunked


### Update Organization Ynqs [PUT /research-common/api/v1/organization-ynqs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Organization Ynqs [PUT /research-common/api/v1/organization-ynqs/]

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

### Insert Organization Ynqs [POST /research-common/api/v1/organization-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"organizationId": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Organization Ynqs [POST /research-common/api/v1/organization-ynqs/]

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
            
### Delete Organization Ynqs by Key [DELETE /research-common/api/v1/organization-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Ynqs [DELETE /research-common/api/v1/organization-ynqs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Ynqs with Matching [DELETE /research-common/api/v1/organization-ynqs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + questionId (optional) - Question Id. Maximum length is 4.
    + answer (optional) - Answer. Maximum length is 1.
    + explanation (optional) - Explanation. Maximum length is 400.
    + reviewDate (optional) - Review Date. Maximum length is 10.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
