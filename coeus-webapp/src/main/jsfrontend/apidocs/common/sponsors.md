## Sponsors [/research-sys/api/v1/sponsors/]

### Get Sponsors by Key [GET /research-sys/api/v1/sponsors/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Sponsors [GET /research-sys/api/v1/sponsors/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sponsors with Filtering [GET /research-sys/api/v1/sponsors/]
    
+ Parameters

        + sponsorCode
            + acronym
            + auditReportSentForFy
            + cageNumber
            + countryCode
            + dodacNumber
            + dunAndBradstreetNumber
            + dunsPlusFourNumber
            + ownedByUnit
            + postalCode
            + rolodexId
            + sponsorName
            + sponsorTypeCode
            + state
            + createUser
            + dunningCampaignId
            + customerNumber
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsors [GET /research-sys/api/v1/sponsors/]
	                                          
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
    
            {"columns":["sponsorCode","acronym","auditReportSentForFy","cageNumber","countryCode","dodacNumber","dunAndBradstreetNumber","dunsPlusFourNumber","ownedByUnit","postalCode","rolodexId","sponsorName","sponsorTypeCode","state","createUser","dunningCampaignId","customerNumber","active"],"primaryKey":"sponsorCode"}
		
### Get Blueprint API specification for Sponsors [GET /research-sys/api/v1/sponsors/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsors.md"
            transfer-encoding:chunked


### Update Sponsors [PUT /research-sys/api/v1/sponsors/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsors [PUT /research-sys/api/v1/sponsors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sponsors [POST /research-sys/api/v1/sponsors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsors [POST /research-sys/api/v1/sponsors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sponsors by Key [DELETE /research-sys/api/v1/sponsors/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsors [DELETE /research-sys/api/v1/sponsors/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsors with Matching [DELETE /research-sys/api/v1/sponsors/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + sponsorCode
            + acronym
            + auditReportSentForFy
            + cageNumber
            + countryCode
            + dodacNumber
            + dunAndBradstreetNumber
            + dunsPlusFourNumber
            + ownedByUnit
            + postalCode
            + rolodexId
            + sponsorName
            + sponsorTypeCode
            + state
            + createUser
            + dunningCampaignId
            + customerNumber
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
