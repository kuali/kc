## Custom Report Types [/research-common/api/v1/custom-report-types/]

### Get Custom Report Types by Key [GET /research-common/api/v1/custom-report-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}

### Get All Custom Report Types [GET /research-common/api/v1/custom-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
            ]

### Get All Custom Report Types with Filtering [GET /research-common/api/v1/custom-report-types/]
    
+ Parameters

    + reportTypeCode (optional) - Report Type Code. Maximum length is 3.
    + reportTypeDesc (optional) - Report Type Desc. Maximum length is 50.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Custom Report Types [GET /research-common/api/v1/custom-report-types/]
	                                          
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
    
            {"columns":["reportTypeCode","reportTypeDesc"],"primaryKey":"reportTypeCode"}
		
### Get Blueprint API specification for Custom Report Types [GET /research-common/api/v1/custom-report-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Report Types.md"
            transfer-encoding:chunked


### Update Custom Report Types [PUT /research-common/api/v1/custom-report-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Report Types [PUT /research-common/api/v1/custom-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Custom Report Types [POST /research-common/api/v1/custom-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Report Types [POST /research-common/api/v1/custom-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Custom Report Types by Key [DELETE /research-common/api/v1/custom-report-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Report Types [DELETE /research-common/api/v1/custom-report-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Report Types with Matching [DELETE /research-common/api/v1/custom-report-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + reportTypeCode (optional) - Report Type Code. Maximum length is 3.
    + reportTypeDesc (optional) - Report Type Desc. Maximum length is 50.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
