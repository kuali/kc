## Kim Document Role Qualifiers [/research-sys/api/v1/kim-document-role-qualifiers/]

### Get Kim Document Role Qualifiers by Key [GET /research-sys/api/v1/kim-document-role-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}

### Get All Kim Document Role Qualifiers [GET /research-sys/api/v1/kim-document-role-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kim Document Role Qualifiers with Filtering [GET /research-sys/api/v1/kim-document-role-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + roleMemberId
            + kimTypId
            + kimAttrDefnId
            + edit
            + documentNumber
            + attrVal
            + active
            + attrDataId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kim Document Role Qualifiers [GET /research-sys/api/v1/kim-document-role-qualifiers/]
	 
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
		
### Get Blueprint API specification for Kim Document Role Qualifiers [GET /research-sys/api/v1/kim-document-role-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kim Document Role Qualifiers.md"
            transfer-encoding:chunked


### Update Kim Document Role Qualifiers [PUT /research-sys/api/v1/kim-document-role-qualifiers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kim Document Role Qualifiers [PUT /research-sys/api/v1/kim-document-role-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kim Document Role Qualifiers [POST /research-sys/api/v1/kim-document-role-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kim Document Role Qualifiers [POST /research-sys/api/v1/kim-document-role-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"roleMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kim Document Role Qualifiers by Key [DELETE /research-sys/api/v1/kim-document-role-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Document Role Qualifiers [DELETE /research-sys/api/v1/kim-document-role-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Kim Document Role Qualifiers with Matching [DELETE /research-sys/api/v1/kim-document-role-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + roleMemberId
            + kimTypId
            + kimAttrDefnId
            + edit
            + documentNumber
            + attrVal
            + active
            + attrDataId


+ Response 204
