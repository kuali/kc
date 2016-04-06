## Congressional Districts [/research-sys/api/v1/congressional-districts/]

### Get Congressional Districts by Key [GET /research-sys/api/v1/congressional-districts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}

### Get All Congressional Districts [GET /research-sys/api/v1/congressional-districts/]
	 
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

### Get All Congressional Districts with Filtering [GET /research-sys/api/v1/congressional-districts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + congressionalDistrict
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Congressional Districts [GET /research-sys/api/v1/congressional-districts/]
	 
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
		
### Get Blueprint API specification for Congressional Districts [GET /research-sys/api/v1/congressional-districts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Congressional Districts.md"
            transfer-encoding:chunked


### Update Congressional Districts [PUT /research-sys/api/v1/congressional-districts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Congressional Districts [PUT /research-sys/api/v1/congressional-districts/]

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

### Insert Congressional Districts [POST /research-sys/api/v1/congressional-districts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","congressionalDistrict": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Congressional Districts [POST /research-sys/api/v1/congressional-districts/]

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
            
### Delete Congressional Districts by Key [DELETE /research-sys/api/v1/congressional-districts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Congressional Districts [DELETE /research-sys/api/v1/congressional-districts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Congressional Districts with Matching [DELETE /research-sys/api/v1/congressional-districts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + congressionalDistrict


+ Response 204
