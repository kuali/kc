## Rate Class Types [/research-common/api/v1/rate-class-types/]

### List All Rate Class Types [GET]
	 
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
			    "code": "E",
			    "sortId": 2,
			    "description": "Fringe Benefits",
			    "prefixActivityType": false
			  },
			  {
			    "code": "I",
			    "sortId": 3,
			    "description": "Inflation",
			    "prefixActivityType": false
			  }
			]

## Rate Classes [/research-common/api/v1/rate-classes/]

### List All Rate Classes [GET]
	 
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
			    "description": "MTDC",
			    "rateClassTypeCode": "O",
			    "code": "1"
			  },
			  {
			    "description": "Lab Allocation - Salaries",
			    "rateClassTypeCode": "Y",
			    "code": "10"
			  }
			]
		
## Rate Types [/research-common/api/v1/rate-types/]

### List All Rate Types [GET]
	 
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
			    "description": "MTDC",
			    "rateClassCode": "1",
			    "rateTypeCode": "1"
			  },
			  {
			    "description": "Lab Allocation - Salaries",
			    "rateClassCode": "10",
			    "rateTypeCode": "1"
			  }
			]

## Institute Rates [/research-common/api/v1/institute-rates/]

### List All Institute Rates [GET]
	 
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
			    "rateTypeCode": "1",
			    "rateClassCode": "1",
			    "startDate": "1995-07-01",
			    "onOffCampusFlag": false,
			    "fiscalYear": "1996",
			    "instituteRate": 9,
			    "activityTypeCode": "1"
			  },
			  {
			    "unitNumber": "000001",
			    "rateTypeCode": "1",
			    "rateClassCode": "1",
			    "startDate": "1995-07-01",
			    "onOffCampusFlag": true,
			    "fiscalYear": "1996",
			    "instituteRate": 54.5,
			    "activityTypeCode": "1"
			  }
			]

### Update Rates [PUT]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
			[
			  {
			    "unitNumber": "000001",
			    "rateTypeCode": "1",
			    "rateClassCode": "1",
			    "startDate": "1995-07-01",
			    "onOffCampusFlag": false,
			    "fiscalYear": "1996",
			    "instituteRate": 9,
			    "activityTypeCode": "1"
			  },
			  {
			    "unitNumber": "000001",
			    "rateTypeCode": "1",
			    "rateClassCode": "1",
			    "startDate": "1995-07-01",
			    "onOffCampusFlag": true,
			    "fiscalYear": "1996",
			    "instituteRate": 54.5,
			    "activityTypeCode": "1"
			  }
			]
+ Response 200
    	