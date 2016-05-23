## Research Areas [/irb/api/v1/research-areas/]

### Get Research Areas by Key [GET /irb/api/v1/research-areas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Research Areas [GET /irb/api/v1/research-areas/]
	 
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

### Get All Research Areas with Filtering [GET /irb/api/v1/research-areas/]
    
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
			
### Get Schema for Research Areas [GET /irb/api/v1/research-areas/]
	                                          
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
		
### Get Blueprint API specification for Research Areas [GET /irb/api/v1/research-areas/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Research Areas.md"
            transfer-encoding:chunked
### Update Research Areas [PUT /irb/api/v1/research-areas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Research Areas [PUT /irb/api/v1/research-areas/]

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
### Insert Research Areas [POST /irb/api/v1/research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"researchAreaCode": "(val)","description": "(val)","hasChildrenFlag": "(val)","parentResearchAreaCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Research Areas [POST /irb/api/v1/research-areas/]

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
### Delete Research Areas by Key [DELETE /irb/api/v1/research-areas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Research Areas [DELETE /irb/api/v1/research-areas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Research Areas with Matching [DELETE /irb/api/v1/research-areas/]

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
