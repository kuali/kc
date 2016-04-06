## Award Template Contacts [/research-sys/api/v1/award-template-contacts/]

### Get Award Template Contacts by Key [GET /research-sys/api/v1/award-template-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}

### Get All Award Template Contacts [GET /research-sys/api/v1/award-template-contacts/]
	 
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

### Get All Award Template Contacts with Filtering [GET /research-sys/api/v1/award-template-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + templateContactId
            + templateCode
            + roleCode
            + rolodexId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Template Contacts [GET /research-sys/api/v1/award-template-contacts/]
	 
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
		
### Get Blueprint API specification for Award Template Contacts [GET /research-sys/api/v1/award-template-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Template Contacts.md"
            transfer-encoding:chunked


### Update Award Template Contacts [PUT /research-sys/api/v1/award-template-contacts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Template Contacts [PUT /research-sys/api/v1/award-template-contacts/]

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

### Insert Award Template Contacts [POST /research-sys/api/v1/award-template-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"templateContactId": "(val)","templateCode": "(val)","roleCode": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Template Contacts [POST /research-sys/api/v1/award-template-contacts/]

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
            
### Delete Award Template Contacts by Key [DELETE /research-sys/api/v1/award-template-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Contacts [DELETE /research-sys/api/v1/award-template-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Template Contacts with Matching [DELETE /research-sys/api/v1/award-template-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + templateContactId
            + templateCode
            + roleCode
            + rolodexId


+ Response 204
