## Valid Narrative Forms [/propdev/api/v1/valid-narrative-forms/]

### Get Valid Narrative Forms by Key [GET /propdev/api/v1/valid-narrative-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}

### Get All Valid Narrative Forms [GET /propdev/api/v1/valid-narrative-forms/]
	 
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

### Get All Valid Narrative Forms with Filtering [GET /propdev/api/v1/valid-narrative-forms/]
    
+ Parameters

    + validNarrFormsId (optional) - Valid Narr Forms Id. Maximum length is 22.
    + formName (optional) - Form Name. Maximum length is 100.
    + narrativeTypeCode (optional) - Narrative Type Code. Maximum length is 22.
    + mandatory (optional) - Mandatory. Maximum length is 3.

            
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
			
### Get Schema for Valid Narrative Forms [GET /propdev/api/v1/valid-narrative-forms/]
	                                          
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
		
### Get Blueprint API specification for Valid Narrative Forms [GET /propdev/api/v1/valid-narrative-forms/]
	 
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


### Update Valid Narrative Forms [PUT /propdev/api/v1/valid-narrative-forms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Narrative Forms [PUT /propdev/api/v1/valid-narrative-forms/]

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

### Insert Valid Narrative Forms [POST /propdev/api/v1/valid-narrative-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validNarrFormsId": "(val)","formName": "(val)","narrativeTypeCode": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Narrative Forms [POST /propdev/api/v1/valid-narrative-forms/]

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
            
### Delete Valid Narrative Forms by Key [DELETE /propdev/api/v1/valid-narrative-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Narrative Forms [DELETE /propdev/api/v1/valid-narrative-forms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Narrative Forms with Matching [DELETE /propdev/api/v1/valid-narrative-forms/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validNarrFormsId (optional) - Valid Narr Forms Id. Maximum length is 22.
    + formName (optional) - Form Name. Maximum length is 100.
    + narrativeTypeCode (optional) - Narrative Type Code. Maximum length is 22.
    + mandatory (optional) - Mandatory. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
