## Award Template Contacts [/award/api/v1/award-template-contacts/]

### Get Award Template Contacts by Key [GET /award/api/v1/award-template-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}

### Get All Award Template Contacts [GET /award/api/v1/award-template-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Template Contacts with Filtering [GET /award/api/v1/award-template-contacts/]
    
+ Parameters

    + templateContactId (optional) - Template Contact Id. Maximum length is 22.
    + templateCode (optional) - 
    + roleCode (optional) - Contact Type. Maximum length is 22.
    + rolodexId (optional) - Rolodex Id. Maximum length is 22.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Template Contacts [GET /award/api/v1/award-template-contacts/]
	                                          
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
    
            {"columns":["templateContactId","templateCode","roleCode","rolodexId"],"primaryKey":"templateContactId"}
		
### Get Blueprint API specification for Award Template Contacts [GET /award/api/v1/award-template-contacts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Template Contacts.md"
            transfer-encoding:chunked


### Update Award Template Contacts [PUT /award/api/v1/award-template-contacts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Template Contacts [PUT /award/api/v1/award-template-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Template Contacts [POST /award/api/v1/award-template-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Template Contacts [POST /award/api/v1/award-template-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Template Contacts by Key [DELETE /award/api/v1/award-template-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Contacts [DELETE /award/api/v1/award-template-contacts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Contacts with Matching [DELETE /award/api/v1/award-template-contacts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + templateContactId (optional) - Template Contact Id. Maximum length is 22.
    + templateCode (optional) - 
    + roleCode (optional) - Contact Type. Maximum length is 22.
    + rolodexId (optional) - Rolodex Id. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
