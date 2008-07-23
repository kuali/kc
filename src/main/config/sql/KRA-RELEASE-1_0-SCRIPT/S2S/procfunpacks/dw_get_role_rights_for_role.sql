/********************************************************************
This procedure selects role_rights.* and rights.decription, right_type,
and descend_flag for a given role_id.
********************************************************************/

create or replace procedure dw_get_role_rights_for_role
   ( AW_ROLE_ID IN OSP$ROLE_RIGHTS.ROLE_ID%TYPE,
	  cur_generic IN OUT result_sets.cur_generic) is 

begin

open cur_generic for

  SELECT RR.RIGHT_ID,
			RR.ROLE_ID,
			RR.DESCEND_FLAG,
			RR.UPDATE_TIMESTAMP,
			RR.UPDATE_USER,
			R.DESCRIPTION,
			R.RIGHT_TYPE,
			R.DESCEND_FLAG  
    FROM OSP$RIGHTS R,   
         OSP$ROLE_RIGHTS RR 
   WHERE RR.ROLE_ID = AW_ROLE_ID
	  AND R.RIGHT_ID = RR.RIGHT_ID ;

end;

/



