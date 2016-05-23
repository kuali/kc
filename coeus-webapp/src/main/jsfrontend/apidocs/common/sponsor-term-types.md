## Sponsor Term Types [/research-common/api/v1/sponsor-term-types/]

### Get Sponsor Term Types by Key [GET /research-common/api/v1/sponsor-term-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Sponsor Term Types [GET /research-common/api/v1/sponsor-term-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sponsor Term Types with Filtering [GET /research-common/api/v1/sponsor-term-types/]
    
+ Parameters

    + sponsorTermTypeCode (optional) - Sponsor Term Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsor Term Types [GET /research-common/api/v1/sponsor-term-types/]
	                                          
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
    
            {"columns":["sponsorTermTypeCode","description"],"primaryKey":"sponsorTermTypeCode"}
		
### Get Blueprint API specification for Sponsor Term Types [GET /research-common/api/v1/sponsor-term-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsor Term Types.md"
            transfer-encoding:chunked
### Update Sponsor Term Types [PUT /research-common/api/v1/sponsor-term-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsor Term Types [PUT /research-common/api/v1/sponsor-term-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sponsor Term Types [POST /research-common/api/v1/sponsor-term-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsor Term Types [POST /research-common/api/v1/sponsor-term-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sponsor Term Types by Key [DELETE /research-common/api/v1/sponsor-term-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Term Types [DELETE /research-common/api/v1/sponsor-term-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Term Types with Matching [DELETE /research-common/api/v1/sponsor-term-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + sponsorTermTypeCode (optional) - Sponsor Term Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
