## Award Science Keywords [/research-sys/api/v1/award-science-keywords/]

### Get Award Science Keywords by Key [GET /research-sys/api/v1/award-science-keywords/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}

### Get All Award Science Keywords [GET /research-sys/api/v1/award-science-keywords/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"},
              {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Science Keywords with Filtering [GET /research-sys/api/v1/award-science-keywords/]
    
+ Parameters

        + awardScienceKeywordId
            + awardId
            + scienceKeywordCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"},
              {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Science Keywords [GET /research-sys/api/v1/award-science-keywords/]
	                                          
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
    
            {"columns":["awardScienceKeywordId","awardId","scienceKeywordCode"],"primaryKey":"awardScienceKeywordId"}
		
### Get Blueprint API specification for Award Science Keywords [GET /research-sys/api/v1/award-science-keywords/]
	 
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


### Update Award Science Keywords [PUT /research-sys/api/v1/award-science-keywords/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Science Keywords [PUT /research-sys/api/v1/award-science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"},
              {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Science Keywords [POST /research-sys/api/v1/award-science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Science Keywords [POST /research-sys/api/v1/award-science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"},
              {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"},
              {"awardScienceKeywordId": "(val)","awardId": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Science Keywords by Key [DELETE /research-sys/api/v1/award-science-keywords/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Science Keywords [DELETE /research-sys/api/v1/award-science-keywords/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Science Keywords with Matching [DELETE /research-sys/api/v1/award-science-keywords/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardScienceKeywordId
            + awardId
            + scienceKeywordCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
