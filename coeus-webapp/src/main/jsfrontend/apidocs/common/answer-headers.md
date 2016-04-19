## Answer Headers [/research-common/api/v1/answer-headers/]

### Get Answer Headers by Key [GET /research-common/api/v1/answer-headers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"}

### Get All Answer Headers [GET /research-common/api/v1/answer-headers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"}
            ]

### Get All Answer Headers with Filtering [GET /research-common/api/v1/answer-headers/]
    
+ Parameters

    + id (optional) - 
    + moduleItemCode (optional) - 
    + moduleItemKey (optional) - 
    + moduleSubItemCode (optional) - 
    + moduleSubItemKey (optional) - 
    + questionnaireId (optional) - 
    + completed (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Answer Headers [GET /research-common/api/v1/answer-headers/]
	                                          
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
    
            {"columns":["id","moduleItemCode","moduleItemKey","moduleSubItemCode","moduleSubItemKey","questionnaireId","completed"],"primaryKey":"id"}
		
### Get Blueprint API specification for Answer Headers [GET /research-common/api/v1/answer-headers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Answer Headers.md"
            transfer-encoding:chunked


### Update Answer Headers [PUT /research-common/api/v1/answer-headers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Answer Headers [PUT /research-common/api/v1/answer-headers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Answer Headers [POST /research-common/api/v1/answer-headers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Answer Headers [POST /research-common/api/v1/answer-headers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","moduleItemCode": "(val)","moduleItemKey": "(val)","moduleSubItemCode": "(val)","moduleSubItemKey": "(val)","questionnaireId": "(val)","completed": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Answer Headers by Key [DELETE /research-common/api/v1/answer-headers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Answer Headers [DELETE /research-common/api/v1/answer-headers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Answer Headers with Matching [DELETE /research-common/api/v1/answer-headers/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + moduleItemCode (optional) - 
    + moduleItemKey (optional) - 
    + moduleSubItemCode (optional) - 
    + moduleSubItemKey (optional) - 
    + questionnaireId (optional) - 
    + completed (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
