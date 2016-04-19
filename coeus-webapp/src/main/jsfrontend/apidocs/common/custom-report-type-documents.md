## Custom Report Type Documents [/research-common/api/v1/custom-report-type-documents/]

### Get Custom Report Type Documents by Key [GET /research-common/api/v1/custom-report-type-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"}

### Get All Custom Report Type Documents [GET /research-common/api/v1/custom-report-type-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Custom Report Type Documents with Filtering [GET /research-common/api/v1/custom-report-type-documents/]
    
+ Parameters

    + reportTypeCode (optional) - Report Type Code. Maximum length is 3.
    + documentName (optional) - Document Name. Maximum length is 100.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Custom Report Type Documents [GET /research-common/api/v1/custom-report-type-documents/]
	                                          
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
    
            {"columns":["reportTypeCode","documentName"],"primaryKey":"documentName:reportTypeCode"}
		
### Get Blueprint API specification for Custom Report Type Documents [GET /research-common/api/v1/custom-report-type-documents/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Report Type Documents.md"
            transfer-encoding:chunked


### Update Custom Report Type Documents [PUT /research-common/api/v1/custom-report-type-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Report Type Documents [PUT /research-common/api/v1/custom-report-type-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Custom Report Type Documents [POST /research-common/api/v1/custom-report-type-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Report Type Documents [POST /research-common/api/v1/custom-report-type-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","documentName": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Custom Report Type Documents by Key [DELETE /research-common/api/v1/custom-report-type-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Report Type Documents [DELETE /research-common/api/v1/custom-report-type-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Report Type Documents with Matching [DELETE /research-common/api/v1/custom-report-type-documents/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + reportTypeCode (optional) - Report Type Code. Maximum length is 3.
    + documentName (optional) - Document Name. Maximum length is 100.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
