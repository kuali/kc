## Award Hierarchies [/research-sys/api/v1/award-hierarchies/]

### Get Award Hierarchies by Key [GET /research-sys/api/v1/award-hierarchies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Award Hierarchies [GET /research-sys/api/v1/award-hierarchies/]
	 
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

### Get All Award Hierarchies with Filtering [GET /research-sys/api/v1/award-hierarchies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardHierarchyId
            + rootAwardNumber
            + awardNumber
            + parentAwardNumber
            + originatingAwardNumber
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Hierarchies [GET /research-sys/api/v1/award-hierarchies/]
	 
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
		
### Get Blueprint API specification for Award Hierarchies [GET /research-sys/api/v1/award-hierarchies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Hierarchies.md"
            transfer-encoding:chunked


### Update Award Hierarchies [PUT /research-sys/api/v1/award-hierarchies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Hierarchies [PUT /research-sys/api/v1/award-hierarchies/]

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

### Insert Award Hierarchies [POST /research-sys/api/v1/award-hierarchies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardHierarchyId": "(val)","rootAwardNumber": "(val)","awardNumber": "(val)","parentAwardNumber": "(val)","originatingAwardNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Hierarchies [POST /research-sys/api/v1/award-hierarchies/]

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
            
### Delete Award Hierarchies by Key [DELETE /research-sys/api/v1/award-hierarchies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Hierarchies [DELETE /research-sys/api/v1/award-hierarchies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Hierarchies with Matching [DELETE /research-sys/api/v1/award-hierarchies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardHierarchyId
            + rootAwardNumber
            + awardNumber
            + parentAwardNumber
            + originatingAwardNumber
            + active


+ Response 204
