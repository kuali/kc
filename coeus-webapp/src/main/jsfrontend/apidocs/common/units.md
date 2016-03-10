## Units [/research-common/api/v1/units/]

## List All Units [GET]
	 
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
			    "unitNumber": "000001",
			    "unitName": "University",
			    "parentUnitNumber": null,
			    "organizationId": "000001"
			  },
			  {
			    "unitNumber": "BL-BL",
			    "unitName": "BLOOMINGTON CAMPUS",
			    "parentUnitNumber": "IU-UNIV",
			    "organizationId": "000001"
			  }
			]
            
            

