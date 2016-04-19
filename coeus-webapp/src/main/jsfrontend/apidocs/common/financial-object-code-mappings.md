## Financial Object Code Mappings [/research-common/api/v1/financial-object-code-mappings/]

### Get Financial Object Code Mappings by Key [GET /research-common/api/v1/financial-object-code-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}

### Get All Financial Object Code Mappings [GET /research-common/api/v1/financial-object-code-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Financial Object Code Mappings with Filtering [GET /research-common/api/v1/financial-object-code-mappings/]
    
+ Parameters

    + mappingId (optional) - 
    + activityTypeCode (optional) - Activity Type Code. Maximum length is 3.
    + financialObjectCode (optional) - Financial Object Code. Maximum length is 8.
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.
    + unitNumber (optional) - Unit Number. Maximum length is 8.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Financial Object Code Mappings [GET /research-common/api/v1/financial-object-code-mappings/]
	                                          
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
    
            {"columns":["mappingId","activityTypeCode","financialObjectCode","rateClassCode","rateTypeCode","unitNumber"],"primaryKey":"mappingId"}
		
### Get Blueprint API specification for Financial Object Code Mappings [GET /research-common/api/v1/financial-object-code-mappings/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Financial Object Code Mappings.md"
            transfer-encoding:chunked


### Update Financial Object Code Mappings [PUT /research-common/api/v1/financial-object-code-mappings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Financial Object Code Mappings [PUT /research-common/api/v1/financial-object-code-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Financial Object Code Mappings [POST /research-common/api/v1/financial-object-code-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Financial Object Code Mappings [POST /research-common/api/v1/financial-object-code-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Financial Object Code Mappings by Key [DELETE /research-common/api/v1/financial-object-code-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Financial Object Code Mappings [DELETE /research-common/api/v1/financial-object-code-mappings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Financial Object Code Mappings with Matching [DELETE /research-common/api/v1/financial-object-code-mappings/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + mappingId (optional) - 
    + activityTypeCode (optional) - Activity Type Code. Maximum length is 3.
    + financialObjectCode (optional) - Financial Object Code. Maximum length is 8.
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.
    + unitNumber (optional) - Unit Number. Maximum length is 8.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
