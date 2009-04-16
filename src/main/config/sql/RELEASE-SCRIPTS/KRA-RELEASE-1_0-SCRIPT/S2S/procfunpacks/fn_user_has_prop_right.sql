/**********************************************************************
This function determines whether the specified proposal right exists
for a given user_id.  A prerequisite for the right to exist is that the
role to which the right belongs is active.
Return values:
	1 = right_exists
	0 = right does not exist

changes for case 589.  we don't want to allow a user to edit a proposal
after post-submission rejection.
**********************************************************************/

create or replace function FN_USER_HAS_PROP_RIGHT
   ( AW_USER_ID IN OSP$EPS_PROP_USER_ROLES.user_id%TYPE,
	  AW_PROPOSAL_NUMBER IN OSP$EPS_PROP_USER_ROLES.proposal_number%TYPE,
	  AW_RIGHT_ID IN osp$role_rights.right_id%TYPE )
	RETURN smallint

is 
	row_count smallint;
	ret_code smallint := NULL;
	right_exists smallint := 1; 
	right_does_not_exist smallint := 0; 
	

begin

  SELECT count(*)  
	 INTO row_count
    FROM OSP$EPS_PROP_USER_ROLES PUR,   
         OSP$ROLE_RIGHTS RR,
         OSP$ROLE R
   WHERE UPPER(PUR.USER_ID) = UPPER(AW_USER_ID)
	  AND PUR.PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER
	  AND R.STATUS_FLAG = 'A'
	  AND RR.RIGHT_ID = AW_RIGHT_ID
	  AND RR.ROLE_ID = PUR.ROLE_ID
	  AND R.ROLE_ID = RR.ROLE_ID ;
	
	IF row_count > 0 THEN
		/* additions for case 589 */
      if (aw_right_id in ( 'MODIFY_PROPOSAL' , 'MODIFY_NARRATIVE','MODIFY_BUDGET')) 
			 and (fn_get_proposal_status(AW_PROPOSAL_NUMBER) > 4) then
			ret_code := right_does_not_exist;
		else
			/*end additions case 589 */
			ret_code := right_exists;
		end if;
	ELSE
		ret_code := right_does_not_exist;
	END IF;

	RETURN (ret_code);
end;

/


