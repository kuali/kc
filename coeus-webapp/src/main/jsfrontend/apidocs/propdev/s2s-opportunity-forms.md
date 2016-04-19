## S2s Opportunity Forms [/propdev/api/v1/s2s-opportunity-forms/]

### Get S2s Opportunity Forms by Key [GET /propdev/api/v1/s2s-opportunity-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}

### Get All S2s Opportunity Forms [GET /propdev/api/v1/s2s-opportunity-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s Opportunity Forms with Filtering [GET /propdev/api/v1/s2s-opportunity-forms/]
    
+ Parameters

    + s2sOppFormsId (optional) - S 2s Opp Forms Id.
    + available (optional) - Available. Maximum length is 1.
    + formName (optional) - Form Name. Maximum length is 100.
    + include (optional) - Include. Maximum length is 1.
    + mandatory (optional) - Mandatory. Maximum length is 1.
    + userAttachedForm (optional) - User Attached Form.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Opportunity Forms [GET /propdev/api/v1/s2s-opportunity-forms/]
	                                          
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
    
            {"columns":["s2sOppFormsId","available","formName","include","mandatory","userAttachedForm"],"primaryKey":"oppNameSpace:proposalNumber"}
		
### Get Blueprint API specification for S2s Opportunity Forms [GET /propdev/api/v1/s2s-opportunity-forms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Opportunity Forms.md"
            transfer-encoding:chunked


### Update S2s Opportunity Forms [PUT /propdev/api/v1/s2s-opportunity-forms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Opportunity Forms [PUT /propdev/api/v1/s2s-opportunity-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s Opportunity Forms [POST /propdev/api/v1/s2s-opportunity-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Opportunity Forms [POST /propdev/api/v1/s2s-opportunity-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s Opportunity Forms by Key [DELETE /propdev/api/v1/s2s-opportunity-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Opportunity Forms [DELETE /propdev/api/v1/s2s-opportunity-forms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Opportunity Forms with Matching [DELETE /propdev/api/v1/s2s-opportunity-forms/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + s2sOppFormsId (optional) - S 2s Opp Forms Id.
    + available (optional) - Available. Maximum length is 1.
    + formName (optional) - Form Name. Maximum length is 100.
    + include (optional) - Include. Maximum length is 1.
    + mandatory (optional) - Mandatory. Maximum length is 1.
    + userAttachedForm (optional) - User Attached Form.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
