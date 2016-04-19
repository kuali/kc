## Sponsor Terms [/research-common/api/v1/sponsor-terms/]

### Get Sponsor Terms by Key [GET /research-common/api/v1/sponsor-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Sponsor Terms [GET /research-common/api/v1/sponsor-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sponsor Terms with Filtering [GET /research-common/api/v1/sponsor-terms/]
    
+ Parameters

    + sponsorTermId (optional) - Sponsor Term Id. Maximum length is 22.
    + sponsorTermCode (optional) - Sponsor Term Code. Maximum length is 3.
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
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsor Terms [GET /research-common/api/v1/sponsor-terms/]
	                                          
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
    
            {"columns":["sponsorTermId","sponsorTermCode","sponsorTermTypeCode","description"],"primaryKey":"sponsorTermId"}
		
### Get Blueprint API specification for Sponsor Terms [GET /research-common/api/v1/sponsor-terms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsor Terms.md"
            transfer-encoding:chunked


### Update Sponsor Terms [PUT /research-common/api/v1/sponsor-terms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsor Terms [PUT /research-common/api/v1/sponsor-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sponsor Terms [POST /research-common/api/v1/sponsor-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsor Terms [POST /research-common/api/v1/sponsor-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sponsor Terms by Key [DELETE /research-common/api/v1/sponsor-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Terms [DELETE /research-common/api/v1/sponsor-terms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Terms with Matching [DELETE /research-common/api/v1/sponsor-terms/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + sponsorTermId (optional) - Sponsor Term Id. Maximum length is 22.
    + sponsorTermCode (optional) - Sponsor Term Code. Maximum length is 3.
    + sponsorTermTypeCode (optional) - Sponsor Term Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
