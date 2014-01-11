
/*******************************************************************
This procedure selects all columns from user_roles, user.user_name
and role.role_name, for a given unit number, where the role_type is
'P' (proposal) and the role is active (status = 'A').
*******************************************************************/

create or replace procedure dw_get_default_prop_user_roles
   ( AW_UNIT_NUMBER IN osp$user_roles.unit_number%TYPE,
     cur_generic IN OUT result_sets.cur_generic) is 

begin

open cur_generic for
  SELECT UR.USER_ID,   
         UR.ROLE_ID,   
         UR.UNIT_NUMBER,   
         UR.DESCEND_FLAG,   
         UR.UPDATE_TIMESTAMP,   
         UR.UPDATE_USER,   
         U.USER_NAME,   
         R.ROLE_NAME  
    FROM OSP$USER_ROLES UR,   
         OSP$USER U,   
         OSP$ROLE R  
   WHERE UR.UNIT_NUMBER = AW_UNIT_NUMBER
     AND UPPER(UR.USER_ID) = UPPER(U.USER_ID)
     AND UR.ROLE_ID = R.ROLE_ID
	  AND R.ROLE_TYPE = 'P'
	  AND R.STATUS_FLAG = 'A' ;   
end;

/


