## Rate Class Base Inclusions [/research-common/api/v1/rate-class-base-inclusions/]

### Get Rate Class Base Inclusions by Key [GET /research-common/api/v1/rate-class-base-inclusions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"}

### Get All Rate Class Base Inclusions [GET /research-common/api/v1/rate-class-base-inclusions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"},
              {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rate Class Base Inclusions with Filtering [GET /research-common/api/v1/rate-class-base-inclusions/]
    
+ Parameters

    + rateClassBaseInclusionId (optional) - Rate Class Base Inclusion Id. Maximum length is 22.
    + rateClassCode (optional) - Rate Class. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type. Maximum length is 3.
    + rateClassCodeIncl (optional) - Rate Class Inclusion. Maximum length is 3.
    + rateTypeCodeIncl (optional) - Rate Type Inclusion. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"},
              {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rate Class Base Inclusions [GET /research-common/api/v1/rate-class-base-inclusions/]
	                                          
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
    
            {"columns":["rateClassBaseInclusionId","rateClassCode","rateTypeCode","rateClassCodeIncl","rateTypeCodeIncl"],"primaryKey":"rateClassBaseInclusionId"}
		
### Get Blueprint API specification for Rate Class Base Inclusions [GET /research-common/api/v1/rate-class-base-inclusions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rate Class Base Inclusions.md"
            transfer-encoding:chunked
### Update Rate Class Base Inclusions [PUT /research-common/api/v1/rate-class-base-inclusions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rate Class Base Inclusions [PUT /research-common/api/v1/rate-class-base-inclusions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"},
              {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Rate Class Base Inclusions [POST /research-common/api/v1/rate-class-base-inclusions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rate Class Base Inclusions [POST /research-common/api/v1/rate-class-base-inclusions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"},
              {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"},
              {"rateClassBaseInclusionId": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","rateClassCodeIncl": "(val)","rateTypeCodeIncl": "(val)","_primaryKey": "(val)"}
            ]
### Delete Rate Class Base Inclusions by Key [DELETE /research-common/api/v1/rate-class-base-inclusions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Class Base Inclusions [DELETE /research-common/api/v1/rate-class-base-inclusions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Class Base Inclusions with Matching [DELETE /research-common/api/v1/rate-class-base-inclusions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + rateClassBaseInclusionId (optional) - Rate Class Base Inclusion Id. Maximum length is 22.
    + rateClassCode (optional) - Rate Class. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type. Maximum length is 3.
    + rateClassCodeIncl (optional) - Rate Class Inclusion. Maximum length is 3.
    + rateTypeCodeIncl (optional) - Rate Type Inclusion. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
