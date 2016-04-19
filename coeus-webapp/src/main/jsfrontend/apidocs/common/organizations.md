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

    + organizationId (optional) - Organization Id. Maximum length is 8.
    + address (optional) - Address. Maximum length is 60.
    + agencySymbol (optional) - Agency Symbol. Maximum length is 30.
    + animalWelfareAssurance (optional) - Animal Welfare Assurance. Maximum length is 20.
    + cableAddress (optional) - Cable Address. Maximum length is 20.
    + cageNumber (optional) - CAGE Number. Maximum length is 20.
    + cognizantAuditor (optional) - Cognizant Auditor. Maximum length is 6.
    + comGovEntityCode (optional) - Com Gov Entity Code. Maximum length is 30.
    + congressionalDistrict (optional) - This field represents the "applicant" congressional district. Maximum length is 50.
    + contactAddressId (optional) - Contact Address Id. Maximum length is 6.
    + county (optional) - County. Maximum length is 30.
    + dodacNumber (optional) - DODAC Number. Maximum length is 20.
    + dunsNumber (optional) - DUNS Number. Maximum length is 20.
    + dunsPlusFourNumber (optional) - DUNS Plus Four Number. Maximum length is 20.
    + federalEmployerId (optional) - Federal Employer Id. Maximum length is 15.
    + humanSubAssurance (optional) - Human Sub Assurance. Maximum length is 30.
    + incorporatedDate (optional) - Incorporated Date. Maximum length is 10.
    + incorporatedIn (optional) - Incorporated In. Maximum length is 50.
    + indirectCostRateAgreement (optional) - Indirect Cost Rate Agreement. Maximum length is 50.
    + irsTaxExemption (optional) - IRS Tax Exemption. Maximum length is 30.
    + stateEmployeeClaim (optional) - State Employee Claim. Maximum length is 30.
    + stateTaxExemptNum (optional) - State Tax Exempt Num. Maximum length is 30.
    + nsfInstitutionalCode (optional) - NSF Institutional Code. Maximum length is 30.
    + numberOfEmployees (optional) - Number Of Employees. Maximum length is 6.
    + onrResidentRep (optional) - ONR Resident Rep. Maximum length is 6.
    + organizationName (optional) - Organization Name. Maximum length is 60.
    + phsAccount (optional) - PHS Account. Maximum length is 30.
    + scienceMisconductComplDate (optional) - Science Misconduct Compl Date. Maximum length is 10.
    + telexNumber (optional) - Telex Number. Maximum length is 20.
    + vendorCode (optional) - Vendor Code. Maximum length is 30.

            
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
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + address (optional) - Address. Maximum length is 60.
    + agencySymbol (optional) - Agency Symbol. Maximum length is 30.
    + animalWelfareAssurance (optional) - Animal Welfare Assurance. Maximum length is 20.
    + cableAddress (optional) - Cable Address. Maximum length is 20.
    + cageNumber (optional) - CAGE Number. Maximum length is 20.
    + cognizantAuditor (optional) - Cognizant Auditor. Maximum length is 6.
    + comGovEntityCode (optional) - Com Gov Entity Code. Maximum length is 30.
    + congressionalDistrict (optional) - This field represents the "applicant" congressional district. Maximum length is 50.
    + contactAddressId (optional) - Contact Address Id. Maximum length is 6.
    + county (optional) - County. Maximum length is 30.
    + dodacNumber (optional) - DODAC Number. Maximum length is 20.
    + dunsNumber (optional) - DUNS Number. Maximum length is 20.
    + dunsPlusFourNumber (optional) - DUNS Plus Four Number. Maximum length is 20.
    + federalEmployerId (optional) - Federal Employer Id. Maximum length is 15.
    + humanSubAssurance (optional) - Human Sub Assurance. Maximum length is 30.
    + incorporatedDate (optional) - Incorporated Date. Maximum length is 10.
    + incorporatedIn (optional) - Incorporated In. Maximum length is 50.
    + indirectCostRateAgreement (optional) - Indirect Cost Rate Agreement. Maximum length is 50.
    + irsTaxExemption (optional) - IRS Tax Exemption. Maximum length is 30.
    + stateEmployeeClaim (optional) - State Employee Claim. Maximum length is 30.
    + stateTaxExemptNum (optional) - State Tax Exempt Num. Maximum length is 30.
    + nsfInstitutionalCode (optional) - NSF Institutional Code. Maximum length is 30.
    + numberOfEmployees (optional) - Number Of Employees. Maximum length is 6.
    + onrResidentRep (optional) - ONR Resident Rep. Maximum length is 6.
    + organizationName (optional) - Organization Name. Maximum length is 60.
    + phsAccount (optional) - PHS Account. Maximum length is 30.
    + scienceMisconductComplDate (optional) - Science Misconduct Compl Date. Maximum length is 10.
    + telexNumber (optional) - Telex Number. Maximum length is 20.
    + vendorCode (optional) - Vendor Code. Maximum length is 30.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
