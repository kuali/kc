## Financial Object Code Mappings [/research-sys/api/v1/financial-object-code-mappings/]

### Get Financial Object Code Mappings by Key [GET /research-sys/api/v1/financial-object-code-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}

### Get All Financial Object Code Mappings [GET /research-sys/api/v1/financial-object-code-mappings/]
	 
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

### Get All Financial Object Code Mappings with Filtering [GET /research-sys/api/v1/financial-object-code-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + mappingId
            + activityTypeCode
            + financialObjectCode
            + rateClassCode
            + rateTypeCode
            + unitNumber
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Financial Object Code Mappings [GET /research-sys/api/v1/financial-object-code-mappings/]
	 
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
		
### Get Blueprint API specification for Financial Object Code Mappings [GET /research-sys/api/v1/financial-object-code-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Financial Object Code Mappings.md"
            transfer-encoding:chunked


### Update Financial Object Code Mappings [PUT /research-sys/api/v1/financial-object-code-mappings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Financial Object Code Mappings [PUT /research-sys/api/v1/financial-object-code-mappings/]

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

### Insert Financial Object Code Mappings [POST /research-sys/api/v1/financial-object-code-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"mappingId": "(val)","activityTypeCode": "(val)","financialObjectCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Financial Object Code Mappings [POST /research-sys/api/v1/financial-object-code-mappings/]

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
            
### Delete Financial Object Code Mappings by Key [DELETE /research-sys/api/v1/financial-object-code-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Financial Object Code Mappings [DELETE /research-sys/api/v1/financial-object-code-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Financial Object Code Mappings with Matching [DELETE /research-sys/api/v1/financial-object-code-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + mappingId
            + activityTypeCode
            + financialObjectCode
            + rateClassCode
            + rateTypeCode
            + unitNumber


+ Response 204
