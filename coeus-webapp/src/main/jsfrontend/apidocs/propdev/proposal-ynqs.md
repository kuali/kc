## Proposal Ynqs [/propdev/api/v1/proposal-ynqs/]

### Get Proposal Ynqs by Key [GET /propdev/api/v1/proposal-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Proposal Ynqs [GET /propdev/api/v1/proposal-ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Ynqs with Filtering [GET /propdev/api/v1/proposal-ynqs/]
    
+ Parameters

    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + questionId (optional) - Question Id. Maximum length is 4.
    + answer (optional) - Answer. Maximum length is 1.
    + explanation (optional) - Explanation. Maximum length is 400.
    + reviewDate (optional) - Review Date. Maximum length is 21.
    + sortId (optional) - Sort Id.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Ynqs [GET /propdev/api/v1/proposal-ynqs/]
	                                          
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
    
            {"columns":["proposalNumber","questionId","answer","explanation","reviewDate","sortId"],"primaryKey":"proposalNumber:questionId"}
		
### Get Blueprint API specification for Proposal Ynqs [GET /propdev/api/v1/proposal-ynqs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Ynqs.md"
            transfer-encoding:chunked


### Update Proposal Ynqs [PUT /propdev/api/v1/proposal-ynqs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Ynqs [PUT /propdev/api/v1/proposal-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Ynqs [POST /propdev/api/v1/proposal-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Ynqs [POST /propdev/api/v1/proposal-ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","questionId": "(val)","answer": "(val)","explanation": "(val)","reviewDate": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Ynqs by Key [DELETE /propdev/api/v1/proposal-ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Ynqs [DELETE /propdev/api/v1/proposal-ynqs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Ynqs with Matching [DELETE /propdev/api/v1/proposal-ynqs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + questionId (optional) - Question Id. Maximum length is 4.
    + answer (optional) - Answer. Maximum length is 1.
    + explanation (optional) - Explanation. Maximum length is 400.
    + reviewDate (optional) - Review Date. Maximum length is 21.
    + sortId (optional) - Sort Id.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
