## Document Type Policies [/research-sys/api/v1/document-type-policies/]

### Get Document Type Policies by Key [GET /research-sys/api/v1/document-type-policies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"}

### Get All Document Type Policies [GET /research-sys/api/v1/document-type-policies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"},
              {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Type Policies with Filtering [GET /research-sys/api/v1/document-type-policies/]
    
+ Parameters

        + policyName
            + policyValue
            + policyStringValue

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"},
              {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Type Policies [GET /research-sys/api/v1/document-type-policies/]
	                                          
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
    
            {"columns":["policyName","policyValue","policyStringValue"],"primaryKey":"documentType:policyName"}
		
### Get Blueprint API specification for Document Type Policies [GET /research-sys/api/v1/document-type-policies/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Type Policies.md"
            transfer-encoding:chunked


### Update Document Type Policies [PUT /research-sys/api/v1/document-type-policies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Type Policies [PUT /research-sys/api/v1/document-type-policies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"},
              {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Document Type Policies [POST /research-sys/api/v1/document-type-policies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Type Policies [POST /research-sys/api/v1/document-type-policies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"},
              {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"},
              {"policyName": "(val)","policyValue": "(val)","policyStringValue": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Document Type Policies by Key [DELETE /research-sys/api/v1/document-type-policies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Type Policies [DELETE /research-sys/api/v1/document-type-policies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Type Policies with Matching [DELETE /research-sys/api/v1/document-type-policies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + policyName
            + policyValue
            + policyStringValue

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
