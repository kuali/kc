## Congressional Districts [/propdev/api/v1/congressional-districts/]

### Get Congressional Districts by Key [GET /propdev/api/v1/congressional-districts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}

### Get All Congressional Districts [GET /propdev/api/v1/congressional-districts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
            ]

### Get All Congressional Districts with Filtering [GET /propdev/api/v1/congressional-districts/]
    
+ Parameters

    + id (optional) - Id.
    + congressionalDistrict (optional) - State code plus congressional district number. Maximum length is 50.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Congressional Districts [GET /propdev/api/v1/congressional-districts/]
	                                          
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
    
            {"columns":["id","congressionalDistrict"],"primaryKey":"id"}
		
### Get Blueprint API specification for Congressional Districts [GET /propdev/api/v1/congressional-districts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Congressional Districts.md"
            transfer-encoding:chunked
### Update Congressional Districts [PUT /propdev/api/v1/congressional-districts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Congressional Districts [PUT /propdev/api/v1/congressional-districts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Congressional Districts [POST /propdev/api/v1/congressional-districts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Congressional Districts [POST /propdev/api/v1/congressional-districts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
            ]
### Delete Congressional Districts by Key [DELETE /propdev/api/v1/congressional-districts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Congressional Districts [DELETE /propdev/api/v1/congressional-districts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Congressional Districts with Matching [DELETE /propdev/api/v1/congressional-districts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id.
    + congressionalDistrict (optional) - State code plus congressional district number. Maximum length is 50.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
