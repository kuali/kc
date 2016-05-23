## Budget Sub Award Files [/propdev/api/v1/budget-sub-award-files/]

### Get Budget Sub Award Files by Key [GET /propdev/api/v1/budget-sub-award-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}

### Get All Budget Sub Award Files [GET /propdev/api/v1/budget-sub-award-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"},
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Sub Award Files with Filtering [GET /propdev/api/v1/budget-sub-award-files/]
    
+ Parameters

    + subAwardXfdFileData (optional) - Sub Award Xfd File Data.
    + subAwardXfdFileName (optional) - Sub Award Xfd File Name.
    + subAwardXmlFileData (optional) - Sub Award Xml File Data.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"},
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Sub Award Files [GET /propdev/api/v1/budget-sub-award-files/]
	                                          
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
    
            {"columns":["subAwardXfdFileData","subAwardXfdFileName","subAwardXmlFileData"],"primaryKey":"budgetSubAward"}
		
### Get Blueprint API specification for Budget Sub Award Files [GET /propdev/api/v1/budget-sub-award-files/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Sub Award Files.md"
            transfer-encoding:chunked
### Update Budget Sub Award Files [PUT /propdev/api/v1/budget-sub-award-files/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Sub Award Files [PUT /propdev/api/v1/budget-sub-award-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"},
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Budget Sub Award Files [POST /propdev/api/v1/budget-sub-award-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Sub Award Files [POST /propdev/api/v1/budget-sub-award-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"},
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"},
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
            ]
### Delete Budget Sub Award Files by Key [DELETE /propdev/api/v1/budget-sub-award-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Sub Award Files [DELETE /propdev/api/v1/budget-sub-award-files/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Sub Award Files with Matching [DELETE /propdev/api/v1/budget-sub-award-files/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardXfdFileData (optional) - Sub Award Xfd File Data.
    + subAwardXfdFileName (optional) - Sub Award Xfd File Name.
    + subAwardXmlFileData (optional) - Sub Award Xml File Data.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
