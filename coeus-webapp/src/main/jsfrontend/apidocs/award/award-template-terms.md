## Award Template Terms [/research-sys/api/v1/award-template-terms/]

### Get Award Template Terms by Key [GET /research-sys/api/v1/award-template-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}

### Get All Award Template Terms [GET /research-sys/api/v1/award-template-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"},
              {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Template Terms with Filtering [GET /research-sys/api/v1/award-template-terms/]
    
+ Parameters

        + awardTemplateTermId
            + templateCode
            + sponsorTermId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"},
              {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Template Terms [GET /research-sys/api/v1/award-template-terms/]
	                                          
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
    
            {"columns":["awardTemplateTermId","templateCode","sponsorTermId"],"primaryKey":"awardTemplateTermId"}
		
### Get Blueprint API specification for Award Template Terms [GET /research-sys/api/v1/award-template-terms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Template Terms.md"
            transfer-encoding:chunked


### Update Award Template Terms [PUT /research-sys/api/v1/award-template-terms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Template Terms [PUT /research-sys/api/v1/award-template-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"},
              {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Template Terms [POST /research-sys/api/v1/award-template-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Template Terms [POST /research-sys/api/v1/award-template-terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"},
              {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"},
              {"awardTemplateTermId": "(val)","templateCode": "(val)","sponsorTermId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Template Terms by Key [DELETE /research-sys/api/v1/award-template-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Terms [DELETE /research-sys/api/v1/award-template-terms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Terms with Matching [DELETE /research-sys/api/v1/award-template-terms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardTemplateTermId
            + templateCode
            + sponsorTermId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
