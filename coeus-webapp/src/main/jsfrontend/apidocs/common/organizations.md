## Organizations [/research-common/api/v1/organizations]

### Organization Summary [GET /research-common/api/v1/organizations/?summary]

Summary of all Organizations in the system

+ Parameters
	+ summary: (boolean, required) - Renders the organizations in a summary view instead of the full data from the organization. Currently the only option and required.
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {
               "count" : 1,
               "totalFound" : 1,
               "organizations" : [
                  {
                     "organizationName" : "University",
                     "address" : "1375 N Scottsdale Rd, Suite 480, Scottsdale, AZ 85257-3454",
                     "organizationId" : "000001",
                     "contact" : {
                        "middleName" : null,
                        "county" : "Maricopa",
                        "emailAddress" : "sean.warren@rsmart.com",
                        "active" : true,
                        "city" : "Scottsdale",
                        "suffix" : null,
                        "postalCode" : "85257-3454",
                        "lastName" : "Warren",
                        "addressLine3" : null,
                        "addressLine2" : "Suite 480",
                        "state" : "AZ",
                        "firstName" : "Sean",
                        "sponsor" : null,
                        "prefix" : null,
                        "rolodexId" : 100013,
                        "phoneNumber" : "480-414-0450",
                        "countryCode" : "USA",
                        "addressLine1" : "1375 N Scottsdale Rd, Scottsdale, AZ 85257-3454",
                        "title" : "OSP Approver",
                        "faxNumber" : "602-391-2172"
                     }
                  }
               ]
            }
            
            

