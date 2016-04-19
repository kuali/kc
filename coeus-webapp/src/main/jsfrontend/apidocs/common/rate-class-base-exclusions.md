## Rate Class Base Exclusions [/research-common/api/v1/rate-class-base-exclusions/]

### Get Rate Class Base Exclusions by Key [GET /research-common/api/v1/rate-class-base-exclusions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}

### Get All Rate Class Base Exclusions [GET /research-common/api/v1/rate-class-base-exclusions/]
	 
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

### Get All Rate Class Base Exclusions with Filtering [GET /research-common/api/v1/rate-class-base-exclusions/]
    
+ Parameters

    + rateClassBaseExclusionId (optional) - Rate Class Base Exclusion Id. Maximum length is 22.
    + rateClassCode (optional) - Rate Class. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type. Maximum length is 3.
    + rateClassCodeExcl (optional) - Rate Class Exclusion. Maximum length is 3.
    + rateTypeCodeExcl (optional) - Rate Type Exclusion. Maximum length is 3.

            
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
			
### Get Schema for Rate Class Base Exclusions [GET /research-common/api/v1/rate-class-base-exclusions/]
	                                          
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
    
            {"columns":["rateClassBaseExclusionId","rateClassCode","rateTypeCode","rateClassCodeExcl","rateTypeCodeExcl"],"primaryKey":"rateClassBaseExclusionId"}
		
### Get Blueprint API specification for Rate Class Base Exclusions [GET /research-common/api/v1/rate-class-base-exclusions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rate Class Base Exclusions.md"
            transfer-encoding:chunked


### Update Rate Class Base Exclusions [PUT /research-common/api/v1/rate-class-base-exclusions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rate Class Base Exclusions [PUT /research-common/api/v1/rate-class-base-exclusions/]

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

### Insert Rate Class Base Exclusions [POST /research-common/api/v1/rate-class-base-exclusions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"rateClassBaseExclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeExcl": "(val)","rateTypeCodeExcl": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rate Class Base Exclusions [POST /research-common/api/v1/rate-class-base-exclusions/]

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
            
### Delete Rate Class Base Exclusions by Key [DELETE /research-common/api/v1/rate-class-base-exclusions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Class Base Exclusions [DELETE /research-common/api/v1/rate-class-base-exclusions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Class Base Exclusions with Matching [DELETE /research-common/api/v1/rate-class-base-exclusions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + rateClassBaseExclusionId (optional) - Rate Class Base Exclusion Id. Maximum length is 22.
    + rateClassCode (optional) - Rate Class. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type. Maximum length is 3.
    + rateClassCodeExcl (optional) - Rate Class Exclusion. Maximum length is 3.
    + rateTypeCodeExcl (optional) - Rate Type Exclusion. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
