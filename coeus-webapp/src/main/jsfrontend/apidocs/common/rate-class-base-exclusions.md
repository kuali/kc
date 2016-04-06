## Rate Class Base Exclusions [/research-sys/api/v1/rate-class-base-exclusions/]

### Get Rate Class Base Exclusions by Key [GET /research-sys/api/v1/rate-class-base-exclusions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}

### Get All Rate Class Base Exclusions [GET /research-sys/api/v1/rate-class-base-exclusions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"},
              {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rate Class Base Exclusions with Filtering [GET /research-sys/api/v1/rate-class-base-exclusions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + rateClassBaseExclusionId
            + rateClassCode
            + rateTypeCode
            + rateClassCodeExcl
            + rateTypeCodeExcl
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"},
              {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rate Class Base Exclusions [GET /research-sys/api/v1/rate-class-base-exclusions/]
	 
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
		
### Get Blueprint API specification for Rate Class Base Exclusions [GET /research-sys/api/v1/rate-class-base-exclusions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rate Class Base Exclusions.md"
            transfer-encoding:chunked


### Update Rate Class Base Exclusions [PUT /research-sys/api/v1/rate-class-base-exclusions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rate Class Base Exclusions [PUT /research-sys/api/v1/rate-class-base-exclusions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"},
              {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rate Class Base Exclusions [POST /research-sys/api/v1/rate-class-base-exclusions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rate Class Base Exclusions [POST /research-sys/api/v1/rate-class-base-exclusions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"},
              {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"},
              {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rate Class Base Exclusions by Key [DELETE /research-sys/api/v1/rate-class-base-exclusions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Class Base Exclusions [DELETE /research-sys/api/v1/rate-class-base-exclusions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Rate Class Base Exclusions with Matching [DELETE /research-sys/api/v1/rate-class-base-exclusions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + rateClassBaseExclusionId
            + rateClassCode
            + rateTypeCode
            + rateClassCodeExcl
            + rateTypeCodeExcl


+ Response 204
