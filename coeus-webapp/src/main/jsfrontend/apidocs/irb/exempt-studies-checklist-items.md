## Exempt Studies Checklist Items [/research-sys/api/v1/exempt-studies-checklist-items/]

### Get Exempt Studies Checklist Items by Key [GET /research-sys/api/v1/exempt-studies-checklist-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Exempt Studies Checklist Items [GET /research-sys/api/v1/exempt-studies-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Exempt Studies Checklist Items with Filtering [GET /research-sys/api/v1/exempt-studies-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + exemptStudiesCheckListCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Exempt Studies Checklist Items [GET /research-sys/api/v1/exempt-studies-checklist-items/]
	 
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
		
### Get Blueprint API specification for Exempt Studies Checklist Items [GET /research-sys/api/v1/exempt-studies-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Exempt Studies Checklist Items.md"
            transfer-encoding:chunked


### Update Exempt Studies Checklist Items [PUT /research-sys/api/v1/exempt-studies-checklist-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Exempt Studies Checklist Items [PUT /research-sys/api/v1/exempt-studies-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Exempt Studies Checklist Items [POST /research-sys/api/v1/exempt-studies-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Exempt Studies Checklist Items [POST /research-sys/api/v1/exempt-studies-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Exempt Studies Checklist Items by Key [DELETE /research-sys/api/v1/exempt-studies-checklist-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Exempt Studies Checklist Items [DELETE /research-sys/api/v1/exempt-studies-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Exempt Studies Checklist Items with Matching [DELETE /research-sys/api/v1/exempt-studies-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + exemptStudiesCheckListCode
            + description


+ Response 204
