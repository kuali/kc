## Negotiation Unassociated Details [/research-sys/api/v1/negotiation-unassociated-details/]

### Get Negotiation Unassociated Details by Key [GET /research-sys/api/v1/negotiation-unassociated-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Unassociated Details [GET /research-sys/api/v1/negotiation-unassociated-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"},
              {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Negotiation Unassociated Details with Filtering [GET /research-sys/api/v1/negotiation-unassociated-details/]
    
+ Parameters

        + negotiationUnassociatedDetailId
            + negotiationId
            + title
            + piPersonId
            + piRolodexId
            + leadUnitNumber
            + sponsorCode
            + piName
            + primeSponsorCode
            + sponsorAwardNumber
            + contactAdminPersonId
            + subAwardOrganizationId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"},
              {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Negotiation Unassociated Details [GET /research-sys/api/v1/negotiation-unassociated-details/]
	                                          
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
    
            {"columns":["negotiationUnassociatedDetailId","negotiationId","title","piPersonId","piRolodexId","leadUnitNumber","sponsorCode","piName","primeSponsorCode","sponsorAwardNumber","contactAdminPersonId","subAwardOrganizationId"],"primaryKey":"negotiationUnassociatedDetailId"}
		
### Get Blueprint API specification for Negotiation Unassociated Details [GET /research-sys/api/v1/negotiation-unassociated-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiation Unassociated Details.md"
            transfer-encoding:chunked


### Update Negotiation Unassociated Details [PUT /research-sys/api/v1/negotiation-unassociated-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Unassociated Details [PUT /research-sys/api/v1/negotiation-unassociated-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"},
              {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Negotiation Unassociated Details [POST /research-sys/api/v1/negotiation-unassociated-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Unassociated Details [POST /research-sys/api/v1/negotiation-unassociated-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"},
              {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"},
              {"negotiationUnassociatedDetailId": "(val)","negotiationId": "(val)","title": "(val)","piPersonId": "(val)","piRolodexId": "(val)","leadUnitNumber": "(val)","sponsorCode": "(val)","piName": "(val)","primeSponsorCode": "(val)","sponsorAwardNumber": "(val)","contactAdminPersonId": "(val)","subAwardOrganizationId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Negotiation Unassociated Details by Key [DELETE /research-sys/api/v1/negotiation-unassociated-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Unassociated Details [DELETE /research-sys/api/v1/negotiation-unassociated-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Unassociated Details with Matching [DELETE /research-sys/api/v1/negotiation-unassociated-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + negotiationUnassociatedDetailId
            + negotiationId
            + title
            + piPersonId
            + piRolodexId
            + leadUnitNumber
            + sponsorCode
            + piName
            + primeSponsorCode
            + sponsorAwardNumber
            + contactAdminPersonId
            + subAwardOrganizationId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
