## Person Editable Fields [/research-sys/api/v1/person-editable-fields/]

### Get Person Editable Fields by Key [GET /research-sys/api/v1/person-editable-fields/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Editable Fields [GET /research-sys/api/v1/person-editable-fields/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Editable Fields with Filtering [GET /research-sys/api/v1/person-editable-fields/]
    
+ Parameters

        + personEditableFieldId
            + moduleCode
            + fieldName
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Editable Fields [GET /research-sys/api/v1/person-editable-fields/]
	                                          
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
    
            {"columns":["personEditableFieldId","moduleCode","fieldName","active"],"primaryKey":"personEditableFieldId"}
		
### Get Blueprint API specification for Person Editable Fields [GET /research-sys/api/v1/person-editable-fields/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Editable Fields.md"
            transfer-encoding:chunked


### Update Person Editable Fields [PUT /research-sys/api/v1/person-editable-fields/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Editable Fields [PUT /research-sys/api/v1/person-editable-fields/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Editable Fields [POST /research-sys/api/v1/person-editable-fields/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Editable Fields [POST /research-sys/api/v1/person-editable-fields/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"personEditableFieldId": "(val)","moduleCode": "(val)","fieldName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Editable Fields by Key [DELETE /research-sys/api/v1/person-editable-fields/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Editable Fields [DELETE /research-sys/api/v1/person-editable-fields/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Editable Fields with Matching [DELETE /research-sys/api/v1/person-editable-fields/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + personEditableFieldId
            + moduleCode
            + fieldName
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
