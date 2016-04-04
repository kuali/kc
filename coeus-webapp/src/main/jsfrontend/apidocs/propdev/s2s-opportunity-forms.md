## S2s Opportunity Forms [/research-sys/api/v1/s2s-opportunity-forms/]

### Get S2s Opportunity Forms by Key [GET /research-sys/api/v1/s2s-opportunity-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}

### Get All S2s Opportunity Forms [GET /research-sys/api/v1/s2s-opportunity-forms/]
	 
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

### Get All S2s Opportunity Forms with Filtering [GET /research-sys/api/v1/s2s-opportunity-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + s2sOppFormsId
            + available
            + formName
            + include
            + mandatory
            + userAttachedForm
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Opportunity Forms [GET /research-sys/api/v1/s2s-opportunity-forms/]
	 
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
		
### Get Blueprint API specification for S2s Opportunity Forms [GET /research-sys/api/v1/s2s-opportunity-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Opportunity Forms.md"
            transfer-encoding:chunked


### Update S2s Opportunity Forms [PUT /research-sys/api/v1/s2s-opportunity-forms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Opportunity Forms [PUT /research-sys/api/v1/s2s-opportunity-forms/]

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

### Insert S2s Opportunity Forms [POST /research-sys/api/v1/s2s-opportunity-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"s2sOppFormsId": "(val)","available": "(val)","formName": "(val)","include": "(val)","mandatory": "(val)","userAttachedForm": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Opportunity Forms [POST /research-sys/api/v1/s2s-opportunity-forms/]

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
            
### Delete S2s Opportunity Forms by Key [DELETE /research-sys/api/v1/s2s-opportunity-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Opportunity Forms [DELETE /research-sys/api/v1/s2s-opportunity-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All S2s Opportunity Forms with Matching [DELETE /research-sys/api/v1/s2s-opportunity-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + s2sOppFormsId
            + available
            + formName
            + include
            + mandatory
            + userAttachedForm


+ Response 204
