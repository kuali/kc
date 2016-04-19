## Sponsors [/research-common/api/v1/sponsors/]

### Get Sponsors by Key [GET /research-common/api/v1/sponsors/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Sponsors [GET /research-common/api/v1/sponsors/]
	 
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

### Get All Sponsors with Filtering [GET /research-common/api/v1/sponsors/]
    
+ Parameters

    + sponsorCode (optional) - Sponsor Code. Maximum length is 6.
    + acronym (optional) - Acronym. Maximum length is 10.
    + auditReportSentForFy (optional) - Audit Report Sent For Fy. Maximum length is 4.
    + cageNumber (optional) - CAGE Number. Maximum length is 20.
    + countryCode (optional) - Country Code. Maximum length is 3.
    + dodacNumber (optional) - DODAC Number. Maximum length is 20.
    + dunAndBradstreetNumber (optional) - DUN And Bradstreet Number. Maximum length is 20.
    + dunsPlusFourNumber (optional) - DUNS Plus Four Number. Maximum length is 20.
    + ownedByUnit (optional) - Owned By Unit. Maximum length is 8.
    + postalCode (optional) - Postal Code. Maximum length is 15.
    + rolodexId (optional) - Rolodex Id. Maximum length is 6.
    + sponsorName (optional) - The name of the sponsoring agency. Maximum length is 200.
    + sponsorTypeCode (optional) - Sponsor Type Code. Maximum length is 3.
    + state (optional) - State. Maximum length is 30.
    + createUser (optional) - Create User.
    + dunningCampaignId (optional) - Dunning Campaign ID. Maximum length is 4.
    + customerNumber (optional) - Customer Number. Maximum length is 40.
    + active (optional) - Active. Maximum length is 1.

            
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
			
### Get Schema for Sponsors [GET /research-common/api/v1/sponsors/]
	                                          
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
		
### Get Blueprint API specification for Sponsors [GET /research-common/api/v1/sponsors/]
	 
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


### Update Sponsors [PUT /research-common/api/v1/sponsors/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsors [PUT /research-common/api/v1/sponsors/]

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

### Insert Sponsors [POST /research-common/api/v1/sponsors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sponsorCode": "(val)","acronym": "(val)","auditReportSentForFy": "(val)","cageNumber": "(val)","countryCode": "(val)","dodacNumber": "(val)","dunAndBradstreetNumber": "(val)","dunsPlusFourNumber": "(val)","ownedByUnit": "(val)","postalCode": "(val)","rolodexId": "(val)","sponsorName": "(val)","sponsorTypeCode": "(val)","state": "(val)","createUser": "(val)","dunningCampaignId": "(val)","customerNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsors [POST /research-common/api/v1/sponsors/]

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
            
### Delete Sponsors by Key [DELETE /research-common/api/v1/sponsors/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsors [DELETE /research-common/api/v1/sponsors/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsors with Matching [DELETE /research-common/api/v1/sponsors/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + sponsorCode (optional) - Sponsor Code. Maximum length is 6.
    + acronym (optional) - Acronym. Maximum length is 10.
    + auditReportSentForFy (optional) - Audit Report Sent For Fy. Maximum length is 4.
    + cageNumber (optional) - CAGE Number. Maximum length is 20.
    + countryCode (optional) - Country Code. Maximum length is 3.
    + dodacNumber (optional) - DODAC Number. Maximum length is 20.
    + dunAndBradstreetNumber (optional) - DUN And Bradstreet Number. Maximum length is 20.
    + dunsPlusFourNumber (optional) - DUNS Plus Four Number. Maximum length is 20.
    + ownedByUnit (optional) - Owned By Unit. Maximum length is 8.
    + postalCode (optional) - Postal Code. Maximum length is 15.
    + rolodexId (optional) - Rolodex Id. Maximum length is 6.
    + sponsorName (optional) - The name of the sponsoring agency. Maximum length is 200.
    + sponsorTypeCode (optional) - Sponsor Type Code. Maximum length is 3.
    + state (optional) - State. Maximum length is 30.
    + createUser (optional) - Create User.
    + dunningCampaignId (optional) - Dunning Campaign ID. Maximum length is 4.
    + customerNumber (optional) - Customer Number. Maximum length is 40.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
