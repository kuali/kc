## Proposal Special Review Exemptions [/propdev/api/v1/proposal-special-review-exemptions/]

### Get Proposal Special Review Exemptions by Key [GET /propdev/api/v1/proposal-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Proposal Special Review Exemptions [GET /propdev/api/v1/proposal-special-review-exemptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Special Review Exemptions with Filtering [GET /propdev/api/v1/proposal-special-review-exemptions/]
    
+ Parameters

    + id (optional) - Proposal Special Review Exemption Id. Maximum length is 22.
    + exemptionTypeCode (optional) - Exemption #. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Special Review Exemptions [GET /propdev/api/v1/proposal-special-review-exemptions/]
	                                          
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
    
            {"columns":["id","exemptionTypeCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Proposal Special Review Exemptions [GET /propdev/api/v1/proposal-special-review-exemptions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Special Review Exemptions.md"
            transfer-encoding:chunked
