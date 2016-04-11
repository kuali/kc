## Iacuc Research Areas [/research-sys/api/v1/iacuc-research-areas/]

### Get Iacuc Research Areas by Key [GET /research-sys/api/v1/iacuc-research-areas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Research Areas [GET /research-sys/api/v1/iacuc-research-areas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Research Areas with Filtering [GET /research-sys/api/v1/iacuc-research-areas/]
    
+ Parameters

        + researchAreaCode
            + description
            + hasChildrenFlag
            + parentResearchAreaCode
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Research Areas [GET /research-sys/api/v1/iacuc-research-areas/]
	                                          
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
    
            {"columns":["researchAreaCode","description","hasChildrenFlag","parentResearchAreaCode","active"],"primaryKey":"researchAreaCode"}
		
### Get Blueprint API specification for Iacuc Research Areas [GET /research-sys/api/v1/iacuc-research-areas/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Research Areas.md"
            transfer-encoding:chunked


### Update Iacuc Research Areas [PUT /research-sys/api/v1/iacuc-research-areas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Research Areas [PUT /research-sys/api/v1/iacuc-research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Research Areas [POST /research-sys/api/v1/iacuc-research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Research Areas [POST /research-sys/api/v1/iacuc-research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Research Areas by Key [DELETE /research-sys/api/v1/iacuc-research-areas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Research Areas [DELETE /research-sys/api/v1/iacuc-research-areas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Research Areas with Matching [DELETE /research-sys/api/v1/iacuc-research-areas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + researchAreaCode
            + description
            + hasChildrenFlag
            + parentResearchAreaCode
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
