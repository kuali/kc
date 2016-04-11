## Iacuc Species [/research-sys/api/v1/iacuc-species/]

### Get Iacuc Species by Key [GET /research-sys/api/v1/iacuc-species/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Species [GET /research-sys/api/v1/iacuc-species/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"},
              {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Species with Filtering [GET /research-sys/api/v1/iacuc-species/]
    
+ Parameters

        + speciesCode
            + speciesName

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"},
              {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Species [GET /research-sys/api/v1/iacuc-species/]
	                                          
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
    
            {"columns":["speciesCode","speciesName"],"primaryKey":"speciesCode"}
		
### Get Blueprint API specification for Iacuc Species [GET /research-sys/api/v1/iacuc-species/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Species.md"
            transfer-encoding:chunked


### Update Iacuc Species [PUT /research-sys/api/v1/iacuc-species/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Species [PUT /research-sys/api/v1/iacuc-species/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"},
              {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Species [POST /research-sys/api/v1/iacuc-species/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Species [POST /research-sys/api/v1/iacuc-species/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"},
              {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"},
              {"speciesCode": "(val)","speciesName": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Species by Key [DELETE /research-sys/api/v1/iacuc-species/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Species [DELETE /research-sys/api/v1/iacuc-species/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Species with Matching [DELETE /research-sys/api/v1/iacuc-species/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + speciesCode
            + speciesName

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
