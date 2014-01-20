create or replace function fn_is_inv (AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
															   AS_PERSON_ID OSP$PERSON.PERSON_ID%TYPE)
                  RETURN number IS
                                                
--------------------------------------------------------------------
-- this function added for case 2695 - Multiple pis
-- check if the person is an investigator. called for proposal printing (not s2s)
-- return 1 if yes, 0 if no
--------------------------------------------------------------------

  
  li_count     			number;
  
  BEGIN

	 		
      SELECT 	COUNT(*)
      INTO     li_count
		FROM 	   OSP$EPS_PROP_INVESTIGATORS
		WHERE 	PERSON_ID = as_person_id
      AND	   PROPOSAL_NUMBER =as_proposal_number;

        
     if li_count > 0 then
	    RETURN '1';
     else
       RETURN '0';
     end if;
      
   
  END;

/

