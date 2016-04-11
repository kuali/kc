## Award Budget Documents [/research-sys/api/v1/award-budget-documents/]

### Get Award Budget Documents by Key [GET /research-sys/api/v1/award-budget-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Documents [GET /research-sys/api/v1/award-budget-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Documents with Filtering [GET /research-sys/api/v1/award-budget-documents/]
    
+ Parameters

        + documentNumber

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Documents [GET /research-sys/api/v1/award-budget-documents/]
	                                          
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
    
            {"columns":["documentNumber"],"primaryKey":"documentNumber"}
		
### Get Blueprint API specification for Award Budget Documents [GET /research-sys/api/v1/award-budget-documents/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Documents.md"
            transfer-encoding:chunked


### Update Award Budget Documents [PUT /research-sys/api/v1/award-budget-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Documents [PUT /research-sys/api/v1/award-budget-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budget Documents [POST /research-sys/api/v1/award-budget-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Documents [POST /research-sys/api/v1/award-budget-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentNumber": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budget Documents by Key [DELETE /research-sys/api/v1/award-budget-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Documents [DELETE /research-sys/api/v1/award-budget-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Documents with Matching [DELETE /research-sys/api/v1/award-budget-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + documentNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
