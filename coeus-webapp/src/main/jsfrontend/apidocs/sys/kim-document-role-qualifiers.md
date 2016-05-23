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
    
+ Parameters

    + roleMemberId (optional) - Role Member Id. Maximum length is 40.
    + kimTypId (optional) - Kim Typ Id. Maximum length is 40.
    + kimAttrDefnId (optional) - Kim Attr Defn Id. Maximum length is 40.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + attrVal (optional) - Attr Val. Maximum length is 400.
    + active (optional) - Active.
    + attrDataId (optional) - Attr Data Id. Maximum length is 40.

            
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
			
### Get Schema for Kim Document Role Qualifiers [GET /research-sys/api/v1/kim-document-role-qualifiers/]
	                                          
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
    
            {"columns":["roleMemberId","kimTypId","kimAttrDefnId","edit","documentNumber","attrVal","active","attrDataId"],"primaryKey":"attrDataId"}
		
### Get Blueprint API specification for Kim Document Role Qualifiers [GET /research-sys/api/v1/kim-document-role-qualifiers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

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

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Document Role Qualifiers with Matching [DELETE /research-sys/api/v1/kim-document-role-qualifiers/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + roleMemberId (optional) - Role Member Id. Maximum length is 40.
    + kimTypId (optional) - Kim Typ Id. Maximum length is 40.
    + kimAttrDefnId (optional) - Kim Attr Defn Id. Maximum length is 40.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + attrVal (optional) - Attr Val. Maximum length is 400.
    + active (optional) - Active.
    + attrDataId (optional) - Attr Data Id. Maximum length is 40.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
