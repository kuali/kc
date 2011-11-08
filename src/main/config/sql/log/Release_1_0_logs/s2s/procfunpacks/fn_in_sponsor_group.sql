CREATE OR REPLACE function fn_in_sponsor_group (AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
															   AS_SPONSOR_GROUP OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
                  RETURN VARCHAR2 IS

--------------------------------------------------------------------
-- this function added for case 2691.
-- check if the sponsor_code or prime sponsor is in the given sponsor group
-- return 1 if yes, 0 if no
-- if the 'Sponsor Groups' hierarchy is not defined, it will return 0
--------------------------------------------------------------------


  li_count     			number;
  li_count_hierarchy  	number;
  ls_sponsor  				OSP$EPS_PROPOSAL.SPONSOR_CODE%TYPE;
  ls_prime_sponsor 		OSP$EPS_PROPOSAL.PRIME_SPONSOR_CODE%TYPE;

  BEGIN

		SELECT sponsor_code, prime_sponsor_code
      INTO	 ls_sponsor, ls_prime_sponsor
      FROM   OSP$EPS_PROPOSAL
      WHERE  PROPOSAL_NUMBER = as_proposal_number;


      SELECT 	COUNT(*)
      INTO     li_count
		FROM 	   OSP$SPONSOR_HIERARCHY
		WHERE 	UPPER(HIERARCHY_NAME) ='SPONSOR GROUPS'
		AND 		UPPER(LEVEL1) = Upper(as_sponsor_group)
		AND 		(sponsor_code = ls_sponsor OR sponsor_code = ls_prime_sponsor);


     if li_count > 0 then
	    RETURN '1';
     else
       RETURN '0';
     end if;


  END;
/

