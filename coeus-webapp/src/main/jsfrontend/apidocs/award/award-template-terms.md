## Award Template Terms [/award/api/v1/award-template-terms/]

### Get Award Template Terms by Key [GET /award/api/v1/award-template-terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardTemplateTermId": "(val)","sponsorTermId": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"}

### Get All Award Template Terms [GET /award/api/v1/award-template-terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardTemplateTermId": "(val)","sponsorTermId": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"},
              {"awardTemplateTermId": "(val)","sponsorTermId": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Template Terms with Filtering [GET /award/api/v1/award-template-terms/]
    
+ Parameters

    + awardTemplateTermId (optional) - Award Template Terms Id. Maximum length is 22.
    + sponsorTermId (optional) - Sponsor Term.
    + awardTemplate.templateCode (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardTemplateTermId": "(val)","sponsorTermId": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"},
              {"awardTemplateTermId": "(val)","sponsorTermId": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Template Terms [GET /award/api/v1/award-template-terms/]
	                                          
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
    
            {"columns":["awardTemplateTermId","sponsorTermId","awardTemplate.templateCode"],"primaryKey":"awardTemplateTermId"}
		
### Get Blueprint API specification for Award Template Terms [GET /award/api/v1/award-template-terms/]
	 
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
