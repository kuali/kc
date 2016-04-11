## Valid Narrative Forms [/research-sys/api/v1/valid-narrative-forms/]

### Get Valid Narrative Forms by Key [GET /research-sys/api/v1/valid-narrative-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}

### Get All Valid Narrative Forms [GET /research-sys/api/v1/valid-narrative-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"},
              {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Narrative Forms with Filtering [GET /research-sys/api/v1/valid-narrative-forms/]
    
+ Parameters

        + validNarrFormsId
            + formName
            + narrativeTypeCode
            + mandatory

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"},
              {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Narrative Forms [GET /research-sys/api/v1/valid-narrative-forms/]
	                                          
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
    
            {"columns":["validNarrFormsId","formName","narrativeTypeCode","mandatory"],"primaryKey":"validNarrFormsId"}
		
### Get Blueprint API specification for Valid Narrative Forms [GET /research-sys/api/v1/valid-narrative-forms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Narrative Forms.md"
            transfer-encoding:chunked


### Update Valid Narrative Forms [PUT /research-sys/api/v1/valid-narrative-forms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Narrative Forms [PUT /research-sys/api/v1/valid-narrative-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"},
              {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Valid Narrative Forms [POST /research-sys/api/v1/valid-narrative-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Narrative Forms [POST /research-sys/api/v1/valid-narrative-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"},
              {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"},
              {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Valid Narrative Forms by Key [DELETE /research-sys/api/v1/valid-narrative-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Narrative Forms [DELETE /research-sys/api/v1/valid-narrative-forms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Narrative Forms with Matching [DELETE /research-sys/api/v1/valid-narrative-forms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + validNarrFormsId
            + formName
            + narrativeTypeCode
            + mandatory

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
