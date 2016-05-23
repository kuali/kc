## Award Science Keywords [/award/api/v1/award-science-keywords/]

### Get Award Science Keywords by Key [GET /award/api/v1/award-science-keywords/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardScienceKeywordId": "(val)","scienceKeywordCode": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}

### Get All Award Science Keywords [GET /award/api/v1/award-science-keywords/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardScienceKeywordId": "(val)","scienceKeywordCode": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardScienceKeywordId": "(val)","scienceKeywordCode": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Science Keywords with Filtering [GET /award/api/v1/award-science-keywords/]
    
+ Parameters

    + awardScienceKeywordId (optional) - Award Science Code Id. Maximum length is 22.
    + scienceKeywordCode (optional) - Science Code. Maximum length is 15.
    + award.awardId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardScienceKeywordId": "(val)","scienceKeywordCode": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardScienceKeywordId": "(val)","scienceKeywordCode": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Science Keywords [GET /award/api/v1/award-science-keywords/]
	                                          
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
    
            {"columns":["awardScienceKeywordId","scienceKeywordCode","award.awardId"],"primaryKey":"awardScienceKeywordId"}
		
### Get Blueprint API specification for Award Science Keywords [GET /award/api/v1/award-science-keywords/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Science Keywords.md"
            transfer-encoding:chunked
