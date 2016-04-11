## Organizations [/research-common/api/v1/organizations/]

### Get Organizations by Key [GET /research-common/api/v1/organizations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"}

### Get All Organizations [GET /research-common/api/v1/organizations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Organizations with Filtering [GET /research-common/api/v1/organizations/]
    
+ Parameters

        + organizationId
            + address
            + agencySymbol
            + animalWelfareAssurance
            + cableAddress
            + cageNumber
            + cognizantAuditor
            + comGovEntityCode
            + congressionalDistrict
            + contactAddressId
            + county
            + dodacNumber
            + dunsNumber
            + dunsPlusFourNumber
            + federalEmployerId
            + humanSubAssurance
            + incorporatedDate
            + incorporatedIn
            + indirectCostRateAgreement
            + irsTaxExemption
            + stateEmployeeClaim
            + stateTaxExemptNum
            + nsfInstitutionalCode
            + numberOfEmployees
            + onrResidentRep
            + organizationName
            + phsAccount
            + scienceMisconductComplDate
            + telexNumber
            + vendorCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Organizations [GET /research-common/api/v1/organizations/]
	                                          
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
    
            {"columns":["organizationId","address","agencySymbol","animalWelfareAssurance","cableAddress","cageNumber","cognizantAuditor","comGovEntityCode","congressionalDistrict","contactAddressId","county","dodacNumber","dunsNumber","dunsPlusFourNumber","federalEmployerId","humanSubAssurance","incorporatedDate","incorporatedIn","indirectCostRateAgreement","irsTaxExemption","stateEmployeeClaim","stateTaxExemptNum","nsfInstitutionalCode","numberOfEmployees","onrResidentRep","organizationName","phsAccount","scienceMisconductComplDate","telexNumber","vendorCode"],"primaryKey":"organizationId"}
		
### Get Blueprint API specification for Organizations [GET /research-common/api/v1/organizations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Organizations.md"
            transfer-encoding:chunked


### Update Organizations [PUT /research-common/api/v1/organizations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Organizations [PUT /research-common/api/v1/organizations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Organizations [POST /research-common/api/v1/organizations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Organizations [POST /research-common/api/v1/organizations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","address": "(val)","agencySymbol": "(val)","animalWelfareAssurance": "(val)","cableAddress": "(val)","cageNumber": "(val)","cognizantAuditor": "(val)","comGovEntityCode": "(val)","congressionalDistrict": "(val)","contactAddressId": "(val)","county": "(val)","dodacNumber": "(val)","dunsNumber": "(val)","dunsPlusFourNumber": "(val)","federalEmployerId": "(val)","humanSubAssurance": "(val)","incorporatedDate": "(val)","incorporatedIn": "(val)","indirectCostRateAgreement": "(val)","irsTaxExemption": "(val)","stateEmployeeClaim": "(val)","stateTaxExemptNum": "(val)","nsfInstitutionalCode": "(val)","numberOfEmployees": "(val)","onrResidentRep": "(val)","organizationName": "(val)","phsAccount": "(val)","scienceMisconductComplDate": "(val)","telexNumber": "(val)","vendorCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Organizations by Key [DELETE /research-common/api/v1/organizations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organizations [DELETE /research-common/api/v1/organizations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organizations with Matching [DELETE /research-common/api/v1/organizations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + organizationId
            + address
            + agencySymbol
            + animalWelfareAssurance
            + cableAddress
            + cageNumber
            + cognizantAuditor
            + comGovEntityCode
            + congressionalDistrict
            + contactAddressId
            + county
            + dodacNumber
            + dunsNumber
            + dunsPlusFourNumber
            + federalEmployerId
            + humanSubAssurance
            + incorporatedDate
            + incorporatedIn
            + indirectCostRateAgreement
            + irsTaxExemption
            + stateEmployeeClaim
            + stateTaxExemptNum
            + nsfInstitutionalCode
            + numberOfEmployees
            + onrResidentRep
            + organizationName
            + phsAccount
            + scienceMisconductComplDate
            + telexNumber
            + vendorCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
