## Person Mass Changes [/research-common/api/v1/person-mass-changes/]

### Get Person Mass Changes by Key [GET /research-common/api/v1/person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personMassChangeId": "(val)","replaceePersonId": "(val)","replaceeRolodexId": "(val)","replacerPersonId": "(val)","replacerRolodexId": "(val)","changeAllSequences": "(val)","personMassChangeDocument.documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Person Mass Changes [GET /research-common/api/v1/person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personMassChangeId": "(val)","replaceePersonId": "(val)","replaceeRolodexId": "(val)","replacerPersonId": "(val)","replacerRolodexId": "(val)","changeAllSequences": "(val)","personMassChangeDocument.documentNumber": "(val)","_primaryKey": "(val)"},
              {"personMassChangeId": "(val)","replaceePersonId": "(val)","replaceeRolodexId": "(val)","replacerPersonId": "(val)","replacerRolodexId": "(val)","changeAllSequences": "(val)","personMassChangeDocument.documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Mass Changes with Filtering [GET /research-common/api/v1/person-mass-changes/]
    
+ Parameters

    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + replaceePersonId (optional) - Employee. Maximum length is 40.
    + replaceeRolodexId (optional) - Non-Employee. Maximum length is 40.
    + replacerPersonId (optional) - Employee. Maximum length is 40.
    + replacerRolodexId (optional) - Non-Employee. Maximum length is 40.
    + changeAllSequences (optional) - Change All Sequences. Maximum length is 1.
    + personMassChangeDocument.documentNumber (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personMassChangeId": "(val)","replaceePersonId": "(val)","replaceeRolodexId": "(val)","replacerPersonId": "(val)","replacerRolodexId": "(val)","changeAllSequences": "(val)","personMassChangeDocument.documentNumber": "(val)","_primaryKey": "(val)"},
              {"personMassChangeId": "(val)","replaceePersonId": "(val)","replaceeRolodexId": "(val)","replacerPersonId": "(val)","replacerRolodexId": "(val)","changeAllSequences": "(val)","personMassChangeDocument.documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Mass Changes [GET /research-common/api/v1/person-mass-changes/]
	                                          
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
    
            {"columns":["personMassChangeId","replaceePersonId","replaceeRolodexId","replacerPersonId","replacerRolodexId","changeAllSequences","personMassChangeDocument.documentNumber"],"primaryKey":"personMassChangeId"}
		
### Get Blueprint API specification for Person Mass Changes [GET /research-common/api/v1/person-mass-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Mass Changes.md"
            transfer-encoding:chunked
