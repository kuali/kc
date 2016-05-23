## Valid Frequency Bases [/award/api/v1/valid-frequency-bases/]

### Get Valid Frequency Bases by Key [GET /award/api/v1/valid-frequency-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Frequency Bases [GET /award/api/v1/valid-frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"},
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Frequency Bases with Filtering [GET /award/api/v1/valid-frequency-bases/]
    
+ Parameters

    + validFrequencyBaseId (optional) - Valid Frequency Base Id. Maximum length is 22.
    + frequencyCode (optional) - Frequency Code. Maximum length is 3.
    + frequencyBaseCode (optional) - Frequency Base Code. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"},
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Frequency Bases [GET /award/api/v1/valid-frequency-bases/]
	                                          
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
    
            {"columns":["validFrequencyBaseId","frequencyCode","frequencyBaseCode"],"primaryKey":"validFrequencyBaseId"}
		
### Get Blueprint API specification for Valid Frequency Bases [GET /award/api/v1/valid-frequency-bases/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Frequency Bases.md"
            transfer-encoding:chunked
### Update Valid Frequency Bases [PUT /award/api/v1/valid-frequency-bases/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Frequency Bases [PUT /award/api/v1/valid-frequency-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"},
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Valid Frequency Bases [POST /award/api/v1/valid-frequency-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Frequency Bases [POST /award/api/v1/valid-frequency-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"},
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"},
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Valid Frequency Bases by Key [DELETE /award/api/v1/valid-frequency-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Frequency Bases [DELETE /award/api/v1/valid-frequency-bases/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Frequency Bases with Matching [DELETE /award/api/v1/valid-frequency-bases/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validFrequencyBaseId (optional) - Valid Frequency Base Id. Maximum length is 22.
    + frequencyCode (optional) - Frequency Code. Maximum length is 3.
    + frequencyBaseCode (optional) - Frequency Base Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
