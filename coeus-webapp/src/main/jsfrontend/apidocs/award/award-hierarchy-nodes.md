## Award Hierarchy Nodes [/award/api/v1/award-hierarchy-nodes/]

### Get Award Hierarchy Nodes by Key [GET /award/api/v1/award-hierarchy-nodes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Award Hierarchy Nodes [GET /award/api/v1/award-hierarchy-nodes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Hierarchy Nodes with Filtering [GET /award/api/v1/award-hierarchy-nodes/]
    
+ Parameters

    + awardHierarchyId (optional) - 
    + rootAwardNumber (optional) - Root  Award Number. Maximum length is 12.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + parentAwardNumber (optional) - Parent  Award Number. Maximum length is 12.
    + originatingAwardNumber (optional) - 
    + active (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Hierarchy Nodes [GET /award/api/v1/award-hierarchy-nodes/]
	                                          
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
    
            {"columns":["awardHierarchyId","rootAwardNumber","awardNumber","parentAwardNumber","originatingAwardNumber","active"],"primaryKey":"awardHierarchyId"}
		
### Get Blueprint API specification for Award Hierarchy Nodes [GET /award/api/v1/award-hierarchy-nodes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Hierarchy Nodes.md"
            transfer-encoding:chunked
### Update Award Hierarchy Nodes [PUT /award/api/v1/award-hierarchy-nodes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Hierarchy Nodes [PUT /award/api/v1/award-hierarchy-nodes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Award Hierarchy Nodes [POST /award/api/v1/award-hierarchy-nodes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Hierarchy Nodes [POST /award/api/v1/award-hierarchy-nodes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Award Hierarchy Nodes by Key [DELETE /award/api/v1/award-hierarchy-nodes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Hierarchy Nodes [DELETE /award/api/v1/award-hierarchy-nodes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Hierarchy Nodes with Matching [DELETE /award/api/v1/award-hierarchy-nodes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardHierarchyId (optional) - 
    + rootAwardNumber (optional) - Root  Award Number. Maximum length is 12.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + parentAwardNumber (optional) - Parent  Award Number. Maximum length is 12.
    + originatingAwardNumber (optional) - 
    + active (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
