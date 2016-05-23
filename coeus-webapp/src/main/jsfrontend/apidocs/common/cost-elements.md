## Cost Elements [/research-common/api/v1/cost-elements/]

### Get Cost Elements by Key [GET /research-common/api/v1/cost-elements/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}

### Get All Cost Elements [GET /research-common/api/v1/cost-elements/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Cost Elements with Filtering [GET /research-common/api/v1/cost-elements/]
    
+ Parameters

    + costElement (optional) - Object Code Name. Maximum length is 8.
    + budgetCategoryCode (optional) - Budget Category Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + onOffCampusFlag (optional) - On CampusContract. Maximum length is 1.
    + active (optional) - Active. Maximum length is 1.
    + financialObjectCode (optional) - Financial Object Code. Maximum length is 8.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Cost Elements [GET /research-common/api/v1/cost-elements/]
	                                          
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
    
            {"columns":["costElement","budgetCategoryCode","description","onOffCampusFlag","active","financialObjectCode"],"primaryKey":"costElement"}
		
### Get Blueprint API specification for Cost Elements [GET /research-common/api/v1/cost-elements/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Cost Elements.md"
            transfer-encoding:chunked
### Update Cost Elements [PUT /research-common/api/v1/cost-elements/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Cost Elements [PUT /research-common/api/v1/cost-elements/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Cost Elements [POST /research-common/api/v1/cost-elements/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Cost Elements [POST /research-common/api/v1/cost-elements/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Cost Elements by Key [DELETE /research-common/api/v1/cost-elements/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Cost Elements [DELETE /research-common/api/v1/cost-elements/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Cost Elements with Matching [DELETE /research-common/api/v1/cost-elements/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + costElement (optional) - Object Code Name. Maximum length is 8.
    + budgetCategoryCode (optional) - Budget Category Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + onOffCampusFlag (optional) - On CampusContract. Maximum length is 1.
    + active (optional) - Active. Maximum length is 1.
    + financialObjectCode (optional) - Financial Object Code. Maximum length is 8.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
