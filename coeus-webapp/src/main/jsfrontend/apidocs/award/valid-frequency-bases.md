## Valid Frequency Bases [/research-sys/api/v1/valid-frequency-bases/]

### Get Valid Frequency Bases by Key [GET /research-sys/api/v1/valid-frequency-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Frequency Bases [GET /research-sys/api/v1/valid-frequency-bases/]
	 
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

### Get All Valid Frequency Bases with Filtering [GET /research-sys/api/v1/valid-frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + validFrequencyBaseId
            + frequencyCode
            + frequencyBaseCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"},
              {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Frequency Bases [GET /research-sys/api/v1/valid-frequency-bases/]
	 
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
		
### Get Blueprint API specification for Valid Frequency Bases [GET /research-sys/api/v1/valid-frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Frequency Bases.md"
            transfer-encoding:chunked


### Update Valid Frequency Bases [PUT /research-sys/api/v1/valid-frequency-bases/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Frequency Bases [PUT /research-sys/api/v1/valid-frequency-bases/]

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

### Insert Valid Frequency Bases [POST /research-sys/api/v1/valid-frequency-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validFrequencyBaseId": "(val)","frequencyCode": "(val)","frequencyBaseCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Frequency Bases [POST /research-sys/api/v1/valid-frequency-bases/]

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
            
### Delete Valid Frequency Bases by Key [DELETE /research-sys/api/v1/valid-frequency-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Frequency Bases [DELETE /research-sys/api/v1/valid-frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Valid Frequency Bases with Matching [DELETE /research-sys/api/v1/valid-frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + validFrequencyBaseId
            + frequencyCode
            + frequencyBaseCode


+ Response 204
