## Award Custom Data [/award/api/v1/award-custom-data/]

### Get Award Custom Data by Key [GET /award/api/v1/award-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardCustomDataId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Award Custom Data [GET /award/api/v1/award-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardCustomDataId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"awardCustomDataId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Custom Data with Filtering [GET /award/api/v1/award-custom-data/]
    
+ Parameters

    + awardCustomDataId (optional) - 
    + awardNumber (optional) - 
    + sequenceNumber (optional) - 
    + customAttributeId (optional) - 
    + value (optional) - Value. Maximum length is 2000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardCustomDataId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"awardCustomDataId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Custom Data [GET /award/api/v1/award-custom-data/]
	                                          
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
    
            {"columns":["awardCustomDataId","awardNumber","sequenceNumber","customAttributeId","value"],"primaryKey":"awardCustomDataId"}
		
### Get Blueprint API specification for Award Custom Data [GET /award/api/v1/award-custom-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Custom Data.md"
            transfer-encoding:chunked
