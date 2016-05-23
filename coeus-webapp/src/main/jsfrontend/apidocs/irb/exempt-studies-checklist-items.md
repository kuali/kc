## Exempt Studies Checklist Items [/irb/api/v1/exempt-studies-checklist-items/]

### Get Exempt Studies Checklist Items by Key [GET /irb/api/v1/exempt-studies-checklist-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Exempt Studies Checklist Items [GET /irb/api/v1/exempt-studies-checklist-items/]
	 
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

### Get All Exempt Studies Checklist Items with Filtering [GET /irb/api/v1/exempt-studies-checklist-items/]
    
+ Parameters

    + exemptStudiesCheckListCode (optional) - Exempt Studies CheckList Code. Maximum length is 4.
    + description (optional) - Description. Maximum length is 2000.

            
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
			
### Get Schema for Exempt Studies Checklist Items [GET /irb/api/v1/exempt-studies-checklist-items/]
	                                          
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
    
            {"columns":["exemptStudiesCheckListCode","description"],"primaryKey":"exemptStudiesCheckListCode"}
		
### Get Blueprint API specification for Exempt Studies Checklist Items [GET /irb/api/v1/exempt-studies-checklist-items/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Exempt Studies Checklist Items.md"
            transfer-encoding:chunked
### Update Exempt Studies Checklist Items [PUT /irb/api/v1/exempt-studies-checklist-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Exempt Studies Checklist Items [PUT /irb/api/v1/exempt-studies-checklist-items/]

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
### Insert Exempt Studies Checklist Items [POST /irb/api/v1/exempt-studies-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"exemptStudiesCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Exempt Studies Checklist Items [POST /irb/api/v1/exempt-studies-checklist-items/]

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
### Delete Exempt Studies Checklist Items by Key [DELETE /irb/api/v1/exempt-studies-checklist-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Exempt Studies Checklist Items [DELETE /irb/api/v1/exempt-studies-checklist-items/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Exempt Studies Checklist Items with Matching [DELETE /irb/api/v1/exempt-studies-checklist-items/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + exemptStudiesCheckListCode (optional) - Exempt Studies CheckList Code. Maximum length is 4.
    + description (optional) - Description. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
