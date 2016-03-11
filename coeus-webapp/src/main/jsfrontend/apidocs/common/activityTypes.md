## Activity Types [/research-common/api/v1/activity-types/]

### List All Activity Types [GET]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
			[
			  {
			    "description": "Research",
			    "code": "1"
			  },
			  {
			    "description": "Instruction",
			    "code": "2"
			  }
			]
            
            

