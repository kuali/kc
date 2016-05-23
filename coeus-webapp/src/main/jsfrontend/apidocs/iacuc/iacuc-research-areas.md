## Iacuc Research Areas [/iacuc/api/v1/iacuc-research-areas/]

### Get Iacuc Research Areas by Key [GET /iacuc/api/v1/iacuc-research-areas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Research Areas [GET /iacuc/api/v1/iacuc-research-areas/]
	 
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

### Get All Iacuc Research Areas with Filtering [GET /iacuc/api/v1/iacuc-research-areas/]
    
+ Parameters

    + researchAreaCode (optional) - Research Area Code. Maximum length is 8.
    + description (optional) - Description. Maximum length is 200.
    + hasChildrenFlag (optional) - Has Children Flag. Maximum length is 1.
    + parentResearchAreaCode (optional) - Parent Research Area Code. Maximum length is 8.
    + active (optional) - Active. Maximum length is 1.

            
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
			
### Get Schema for Iacuc Research Areas [GET /iacuc/api/v1/iacuc-research-areas/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Research Areas [GET /iacuc/api/v1/iacuc-research-areas/]
	 
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
### Update Iacuc Research Areas [PUT /iacuc/api/v1/iacuc-research-areas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Research Areas [PUT /iacuc/api/v1/iacuc-research-areas/]

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
### Insert Iacuc Research Areas [POST /iacuc/api/v1/iacuc-research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Research Areas [POST /iacuc/api/v1/iacuc-research-areas/]

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
### Delete Iacuc Research Areas by Key [DELETE /iacuc/api/v1/iacuc-research-areas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Research Areas [DELETE /iacuc/api/v1/iacuc-research-areas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Research Areas with Matching [DELETE /iacuc/api/v1/iacuc-research-areas/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + researchAreaCode (optional) - Research Area Code. Maximum length is 8.
    + description (optional) - Description. Maximum length is 200.
    + hasChildrenFlag (optional) - Has Children Flag. Maximum length is 1.
    + parentResearchAreaCode (optional) - Parent Research Area Code. Maximum length is 8.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
