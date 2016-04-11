## Entity Affiliation Types [/research-sys/api/v1/entity-affiliation-types/]

### Get Entity Affiliation Types by Key [GET /research-sys/api/v1/entity-affiliation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Affiliation Types [GET /research-sys/api/v1/entity-affiliation-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Affiliation Types with Filtering [GET /research-sys/api/v1/entity-affiliation-types/]
    
+ Parameters

        + employmentAffiliationType
            + code
            + name
            + active
            + sortCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Affiliation Types [GET /research-sys/api/v1/entity-affiliation-types/]
	                                          
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
    
            {"columns":["employmentAffiliationType","code","name","active","sortCode"],"primaryKey":"code"}
		
### Get Blueprint API specification for Entity Affiliation Types [GET /research-sys/api/v1/entity-affiliation-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Affiliation Types.md"
            transfer-encoding:chunked


### Update Entity Affiliation Types [PUT /research-sys/api/v1/entity-affiliation-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Affiliation Types [PUT /research-sys/api/v1/entity-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Affiliation Types [POST /research-sys/api/v1/entity-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Affiliation Types [POST /research-sys/api/v1/entity-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"},
              {"employmentAffiliationType": "(val)","code": "(val)","name": "(val)","active": "(val)","sortCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Affiliation Types by Key [DELETE /research-sys/api/v1/entity-affiliation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Affiliation Types [DELETE /research-sys/api/v1/entity-affiliation-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Affiliation Types with Matching [DELETE /research-sys/api/v1/entity-affiliation-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + employmentAffiliationType
            + code
            + name
            + active
            + sortCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
