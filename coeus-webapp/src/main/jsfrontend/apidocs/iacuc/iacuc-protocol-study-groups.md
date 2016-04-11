## Iacuc Protocol Study Groups [/research-sys/api/v1/iacuc-protocol-study-groups/]

### Get Iacuc Protocol Study Groups by Key [GET /research-sys/api/v1/iacuc-protocol-study-groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Study Groups [GET /research-sys/api/v1/iacuc-protocol-study-groups/]
	 
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

### Get All Iacuc Protocol Study Groups with Filtering [GET /research-sys/api/v1/iacuc-protocol-study-groups/]
    
+ Parameters

        + iacucProtocolStudyGroupId
            + iacucProtocolStudyGroupHeaderId
            + iacucProtocolSpeciesId
            + painCategoryCode
            + count

            
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
			
### Get Schema for Iacuc Protocol Study Groups [GET /research-sys/api/v1/iacuc-protocol-study-groups/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Study Groups [GET /research-sys/api/v1/iacuc-protocol-study-groups/]
	 
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


### Update Iacuc Protocol Study Groups [PUT /research-sys/api/v1/iacuc-protocol-study-groups/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Study Groups [PUT /research-sys/api/v1/iacuc-protocol-study-groups/]

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

### Insert Iacuc Protocol Study Groups [POST /research-sys/api/v1/iacuc-protocol-study-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolStudyGroupId": "(val)","iacucProtocolStudyGroupHeaderId": "(val)","iacucProtocolSpeciesId": "(val)","painCategoryCode": "(val)","count": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Study Groups [POST /research-sys/api/v1/iacuc-protocol-study-groups/]

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
            
### Delete Iacuc Protocol Study Groups by Key [DELETE /research-sys/api/v1/iacuc-protocol-study-groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Groups [DELETE /research-sys/api/v1/iacuc-protocol-study-groups/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Groups with Matching [DELETE /research-sys/api/v1/iacuc-protocol-study-groups/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + iacucProtocolStudyGroupId
            + iacucProtocolStudyGroupHeaderId
            + iacucProtocolSpeciesId
            + painCategoryCode
            + count

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
