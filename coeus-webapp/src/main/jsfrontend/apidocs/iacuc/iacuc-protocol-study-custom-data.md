## Iacuc Protocol Study Custom Data [/iacuc/api/v1/iacuc-protocol-study-custom-data/]

### Get Iacuc Protocol Study Custom Data by Key [GET /iacuc/api/v1/iacuc-protocol-study-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Study Custom Data [GET /iacuc/api/v1/iacuc-protocol-study-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Study Custom Data with Filtering [GET /iacuc/api/v1/iacuc-protocol-study-custom-data/]
    
+ Parameters

    + iacucProtocolStudyCustomDataId (optional) - IACUC Protocol Study Custom Data Id. Maximum length is 22.
    + iacucProtocolStudyGroupId (optional) - Protocol Study Group Id. Maximum length is 22.
    + procedureCustomAttributeId (optional) - Custom Attribute Id. Maximum length is 22.
    + value (optional) - Value. Maximum length is 2000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Study Custom Data [GET /iacuc/api/v1/iacuc-protocol-study-custom-data/]
	                                          
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
    
            {"columns":["iacucProtocolStudyCustomDataId","iacucProtocolStudyGroupId","procedureCustomAttributeId","value"],"primaryKey":"iacucProtocolStudyCustomDataId"}
		
### Get Blueprint API specification for Iacuc Protocol Study Custom Data [GET /iacuc/api/v1/iacuc-protocol-study-custom-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Study Custom Data.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Study Custom Data [PUT /iacuc/api/v1/iacuc-protocol-study-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Study Custom Data [PUT /iacuc/api/v1/iacuc-protocol-study-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Study Custom Data [POST /iacuc/api/v1/iacuc-protocol-study-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Study Custom Data [POST /iacuc/api/v1/iacuc-protocol-study-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyCustomDataId": "(val)","iacucProtocolStudyGroupId": "(val)","procedureCustomAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Study Custom Data by Key [DELETE /iacuc/api/v1/iacuc-protocol-study-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Custom Data [DELETE /iacuc/api/v1/iacuc-protocol-study-custom-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Custom Data with Matching [DELETE /iacuc/api/v1/iacuc-protocol-study-custom-data/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucProtocolStudyCustomDataId (optional) - IACUC Protocol Study Custom Data Id. Maximum length is 22.
    + iacucProtocolStudyGroupId (optional) - Protocol Study Group Id. Maximum length is 22.
    + procedureCustomAttributeId (optional) - Custom Attribute Id. Maximum length is 22.
    + value (optional) - Value. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
