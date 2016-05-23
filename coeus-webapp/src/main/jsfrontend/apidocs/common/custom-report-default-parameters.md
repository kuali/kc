## Custom Report Default Parameters [/research-common/api/v1/custom-report-default-parameters/]

### Get Custom Report Default Parameters by Key [GET /research-common/api/v1/custom-report-default-parameters/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"}

### Get All Custom Report Default Parameters [GET /research-common/api/v1/custom-report-default-parameters/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"},
              {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"}
            ]

### Get All Custom Report Default Parameters with Filtering [GET /research-common/api/v1/custom-report-default-parameters/]
    
+ Parameters

    + parameterName (optional) - Parameter Name. Maximum length is 100.
    + reportTypeCode (optional) - Report Type Code. Maximum length is 3.
    + className (optional) - Class Name. Maximum length is 100.
    + javaStatements (optional) - Java Statements. Maximum length is 2000.
    + unitForAuthCheck (optional) - Unit For Auth Check. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"},
              {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Custom Report Default Parameters [GET /research-common/api/v1/custom-report-default-parameters/]
	                                          
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
    
            {"columns":["parameterName","reportTypeCode","className","javaStatements","unitForAuthCheck"],"primaryKey":"className:parameterName:reportTypeCode"}
		
### Get Blueprint API specification for Custom Report Default Parameters [GET /research-common/api/v1/custom-report-default-parameters/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Report Default Parameters.md"
            transfer-encoding:chunked
### Update Custom Report Default Parameters [PUT /research-common/api/v1/custom-report-default-parameters/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Report Default Parameters [PUT /research-common/api/v1/custom-report-default-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"},
              {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Custom Report Default Parameters [POST /research-common/api/v1/custom-report-default-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Report Default Parameters [POST /research-common/api/v1/custom-report-default-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"},
              {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"},
              {"parameterName": "(val)","reportTypeCode": "(val)","className": "(val)","javaStatements": "(val)","unitForAuthCheck": "(val)","_primaryKey": "(val)"}
            ]
### Delete Custom Report Default Parameters by Key [DELETE /research-common/api/v1/custom-report-default-parameters/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Report Default Parameters [DELETE /research-common/api/v1/custom-report-default-parameters/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Report Default Parameters with Matching [DELETE /research-common/api/v1/custom-report-default-parameters/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + parameterName (optional) - Parameter Name. Maximum length is 100.
    + reportTypeCode (optional) - Report Type Code. Maximum length is 3.
    + className (optional) - Class Name. Maximum length is 100.
    + javaStatements (optional) - Java Statements. Maximum length is 2000.
    + unitForAuthCheck (optional) - Unit For Auth Check. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
