## Budget Sub Award Files [/research-sys/api/v1/budget-sub-award-files/]

### Get Budget Sub Award Files by Key [GET /research-sys/api/v1/budget-sub-award-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}

### Get All Budget Sub Award Files [GET /research-sys/api/v1/budget-sub-award-files/]
	 
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

### Get All Budget Sub Award Files with Filtering [GET /research-sys/api/v1/budget-sub-award-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + subAwardXfdFileData
            + subAwardXfdFileName
            + subAwardXmlFileData
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"},
              {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Sub Award Files [GET /research-sys/api/v1/budget-sub-award-files/]
	 
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
		
### Get Blueprint API specification for Budget Sub Award Files [GET /research-sys/api/v1/budget-sub-award-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Sub Award Files.md"
            transfer-encoding:chunked


### Update Budget Sub Award Files [PUT /research-sys/api/v1/budget-sub-award-files/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Sub Award Files [PUT /research-sys/api/v1/budget-sub-award-files/]

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

### Insert Budget Sub Award Files [POST /research-sys/api/v1/budget-sub-award-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardXfdFileData": "(val)","subAwardXfdFileName": "(val)","subAwardXmlFileData": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Sub Award Files [POST /research-sys/api/v1/budget-sub-award-files/]

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
            
### Delete Budget Sub Award Files by Key [DELETE /research-sys/api/v1/budget-sub-award-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Sub Award Files [DELETE /research-sys/api/v1/budget-sub-award-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Budget Sub Award Files with Matching [DELETE /research-sys/api/v1/budget-sub-award-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + subAwardXfdFileData
            + subAwardXfdFileName
            + subAwardXmlFileData


+ Response 204
