## Custom Report Types [/research-sys/api/v1/custom-report-types/]

### Get Custom Report Types by Key [GET /research-sys/api/v1/custom-report-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}

### Get All Custom Report Types [GET /research-sys/api/v1/custom-report-types/]
	 
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

### Get All Custom Report Types with Filtering [GET /research-sys/api/v1/custom-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + reportTypeCode
            + reportTypeDesc
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"},
              {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Custom Report Types [GET /research-sys/api/v1/custom-report-types/]
	 
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
		
### Get Blueprint API specification for Custom Report Types [GET /research-sys/api/v1/custom-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Report Types.md"
            transfer-encoding:chunked


### Update Custom Report Types [PUT /research-sys/api/v1/custom-report-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Report Types [PUT /research-sys/api/v1/custom-report-types/]

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

### Insert Custom Report Types [POST /research-sys/api/v1/custom-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reportTypeCode": "(val)","reportTypeDesc": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Report Types [POST /research-sys/api/v1/custom-report-types/]

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
            
### Delete Custom Report Types by Key [DELETE /research-sys/api/v1/custom-report-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Report Types [DELETE /research-sys/api/v1/custom-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Custom Report Types with Matching [DELETE /research-sys/api/v1/custom-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + reportTypeCode
            + reportTypeDesc


+ Response 204
