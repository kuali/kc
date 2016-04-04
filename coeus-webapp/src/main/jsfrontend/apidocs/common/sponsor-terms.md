## Sponsor Terms [/research-sys/api/v1/sponsor-terms/]

### Get Sponsor Terms by Key [GET /research-sys/api/v1/sponsor-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Sponsor Terms [GET /research-sys/api/v1/sponsor-terms/]
	 
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

### Get All Sponsor Terms with Filtering [GET /research-sys/api/v1/sponsor-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + sponsorTermId
            + sponsorTermCode
            + sponsorTermTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsor Terms [GET /research-sys/api/v1/sponsor-terms/]
	 
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
		
### Get Blueprint API specification for Sponsor Terms [GET /research-sys/api/v1/sponsor-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsor Terms.md"
            transfer-encoding:chunked


### Update Sponsor Terms [PUT /research-sys/api/v1/sponsor-terms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsor Terms [PUT /research-sys/api/v1/sponsor-terms/]

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

### Insert Sponsor Terms [POST /research-sys/api/v1/sponsor-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sponsorTermId": "(val)","sponsorTermCode": "(val)","sponsorTermTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsor Terms [POST /research-sys/api/v1/sponsor-terms/]

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
            
### Delete Sponsor Terms by Key [DELETE /research-sys/api/v1/sponsor-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Terms [DELETE /research-sys/api/v1/sponsor-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sponsor Terms with Matching [DELETE /research-sys/api/v1/sponsor-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + sponsorTermId
            + sponsorTermCode
            + sponsorTermTypeCode
            + description


+ Response 204
