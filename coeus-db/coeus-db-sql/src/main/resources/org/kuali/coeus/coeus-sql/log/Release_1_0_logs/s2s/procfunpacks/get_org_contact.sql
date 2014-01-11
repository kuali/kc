  CREATE OR REPLACE PROCEDURE GET_ORG_CONTACT(
   AS_PROPOSAL_NUMBER IN OSP$BUDGET_PERIODS.PROPOSAL_NUMBER%TYPE, 
   cur_generic IN OUT result_sets.cur_generic)
 is 

 liCount number;

  begin  

   SELECT COUNT(*)
   INTO   	liCount
   FROM	   OSP$PERSON P, OSP$UNIT U, OSP$EPS_PROP_UNITS PU 
   WHERE	  	PU.PROPOSAL_NUMBER = as_proposal_number 
   AND 		PU.LEAD_UNIT_FLAG = 'Y' 
   AND 		PU.UNIT_NUMBER = U.UNIT_NUMBER 
   AND 		U.OSP_ADMINISTRATOR = P.PERSON_ID; 

   if liCount > 0 then

   	open cur_generic for  

    	SELECT DECODE(LENGTH(RTRIM(P.FIRST_NAME )), 0, ' ', RTRIM(P.FIRST_NAME))  FIRST_NAME, 
      		 DECODE(LENGTH(RTRIM(P.MIDDLE_NAME)), 0, ' ', RTRIM(P.MIDDLE_NAME))  MIDDLE_NAME, 
     			 DECODE(LENGTH(RTRIM(P.LAST_NAME)), 0, ' ', RTRIM(P.LAST_NAME)) LAST_NAME, 
             RTRIM(P.FULL_NAME) FULL_NAME, 
             P.PERSON_ID,P.EMAIL_ADDRESS,P.OFFICE_PHONE,P.PRIMARY_TITLE,P.OFFICE_LOCATION ,
             p.address_line_1, p.address_line_2, p.address_line_3, p.city, p.state, p.postal_code,
             p.country_code
  		FROM 	 OSP$PERSON P, OSP$UNIT U, OSP$EPS_PROP_UNITS PU 
  		WHERE  PU.PROPOSAL_NUMBER = as_proposal_number 
  		AND 	 PU.LEAD_UNIT_FLAG = 'Y' 
  		AND 	 PU.UNIT_NUMBER = U.UNIT_NUMBER 
  		AND 	 U.OSP_ADMINISTRATOR = P.PERSON_ID; 

   else

 		open cur_generic for  
    	select 'UNKNOWN' as FIRST_NAME,
			  	'UNKNOWN' as MIDDLE_NAME,
			  	'UNKNOWN' as LAST_NAME,
			  	'UNKNOWN' as FULL_NAME,
			  	'XXXXXXXXX' AS PERSON_ID,
			  	'UNKNOWN' as EMAIL_ADDRESS,
			  	'UNKNOWN' as OFFICE_PHONE,
			  	'UNKNOWN' as PRIMARY_TITLE,
			  	'UNKNOWN' as OFFICE_LOCATION,
			  	'UNKNOWN' as city,
			  	'UNKNOWN' as postal_code,
			  	'UNKNOWN' as country_code
      from   dual;
	end if;
    
end;
/


