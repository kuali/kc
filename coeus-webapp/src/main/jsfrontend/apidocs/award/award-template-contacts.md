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
    
            {"templateContactId": "(val)","roleCode": "(val)","rolodexId": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"}

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
              {"templateContactId": "(val)","roleCode": "(val)","rolodexId": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"},
              {"templateContactId": "(val)","roleCode": "(val)","rolodexId": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Template Contacts with Filtering [GET /award/api/v1/award-template-contacts/]
    
+ Parameters

    + templateContactId (optional) - Template Contact Id. Maximum length is 22.
    + roleCode (optional) - Contact Type. Maximum length is 22.
    + rolodexId (optional) - Rolodex Id. Maximum length is 22.
    + awardTemplate.templateCode (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateContactId": "(val)","roleCode": "(val)","rolodexId": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"},
              {"templateContactId": "(val)","roleCode": "(val)","rolodexId": "(val)","awardTemplate.templateCode": "(val)","_primaryKey": "(val)"}
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
    
            {"columns":["templateContactId","roleCode","rolodexId","awardTemplate.templateCode"],"primaryKey":"templateContactId"}
		
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
