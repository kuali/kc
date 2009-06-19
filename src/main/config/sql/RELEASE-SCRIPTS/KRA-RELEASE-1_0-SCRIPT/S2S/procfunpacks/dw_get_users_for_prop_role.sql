CREATE OR REPLACE procedure dw_get_users_for_prop_role
   ( AW_PROPOSAL_NUMBER IN OSP$EPS_PROP_USER_ROLES.proposal_number%TYPE,
	  AW_ROLE_ID IN OSP$EPS_PROP_USER_ROLES.role_id%TYPE,
     cur_generic IN OUT result_sets.cur_generic) is

begin

open cur_generic for
  SELECT PUR.PROPOSAL_NUMBER,
   		PUR.USER_ID,
         PUR.ROLE_ID,
         PUR.UPDATE_TIMESTAMP,
         PUR.UPDATE_USER,
         U.USER_NAME,
			U.UNIT_NUMBER,
         U.STATUS,
			UN.UNIT_NAME
    FROM OSP$EPS_PROP_USER_ROLES PUR,
         OSP$USER U,
			OSP$UNIT UN
   WHERE UPPER(PUR.USER_ID) = UPPER(U.USER_ID)
   	AND PUR.PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER
     	AND PUR.ROLE_ID = AW_ROLE_ID
		AND  UN.UNIT_NUMBER(+) = U.UNIT_NUMBER;
end;
/

