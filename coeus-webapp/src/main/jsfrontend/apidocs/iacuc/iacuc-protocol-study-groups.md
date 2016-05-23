## Iacuc Protocol Study Groups [/iacuc/api/v1/iacuc-protocol-study-groups/]

### Get Iacuc Protocol Study Groups by Key [GET /iacuc/api/v1/iacuc-protocol-study-groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Study Groups [GET /iacuc/api/v1/iacuc-protocol-study-groups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Study Groups with Filtering [GET /iacuc/api/v1/iacuc-protocol-study-groups/]
    
+ Parameters

    + iacucProtocolStudyGroupId (optional) - Protocol Study Group Id. Maximum length is 22.
    + iacucProtocolStudyGroupHeaderId (optional) - Protocol Study Group Header Id. Maximum length is 22.
    + iacucProtocolSpeciesId (optional) - Protocol Species Id. Maximum length is 22.
    + painCategoryCode (optional) - Pain Category. Maximum length is 3.
    + count (optional) - Count. Maximum length is 8.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Study Groups [GET /iacuc/api/v1/iacuc-protocol-study-groups/]
	                                          
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
    
            {"columns":["iacucProtocolStudyGroupId","iacucProtocolStudyGroupHeaderId","iacucProtocolSpeciesId","painCategoryCode","count"],"primaryKey":"iacucProtocolStudyGroupId"}
		
### Get Blueprint API specification for Iacuc Protocol Study Groups [GET /iacuc/api/v1/iacuc-protocol-study-groups/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Study Groups.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Study Groups [PUT /iacuc/api/v1/iacuc-protocol-study-groups/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Study Groups [PUT /iacuc/api/v1/iacuc-protocol-study-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Study Groups [POST /iacuc/api/v1/iacuc-protocol-study-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Study Groups [POST /iacuc/api/v1/iacuc-protocol-study-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Study Groups by Key [DELETE /iacuc/api/v1/iacuc-protocol-study-groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Groups [DELETE /iacuc/api/v1/iacuc-protocol-study-groups/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Groups with Matching [DELETE /iacuc/api/v1/iacuc-protocol-study-groups/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucProtocolStudyGroupId (optional) - Protocol Study Group Id. Maximum length is 22.
    + iacucProtocolStudyGroupHeaderId (optional) - Protocol Study Group Header Id. Maximum length is 22.
    + iacucProtocolSpeciesId (optional) - Protocol Species Id. Maximum length is 22.
    + painCategoryCode (optional) - Pain Category. Maximum length is 3.
    + count (optional) - Count. Maximum length is 8.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
