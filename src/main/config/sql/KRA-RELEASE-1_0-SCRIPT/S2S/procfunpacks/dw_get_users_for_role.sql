/*******************************************************************
This procedure selects all columns from user_roles, user.user_name
and status, and role.role_name, role_type, owned_by_unit, and
status_flag.
*******************************************************************/

create or replace procedure dw_get_users_for_role
   ( AW_ROLE_ID IN osp$user_roles.role_id%TYPE,
     AW_UNIT_NUMBER IN osp$user_roles.unit_number%TYPE,
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
         U.STATUS,
         R.ROLE_NAME,
			R.ROLE_TYPE,
			R.OWNED_BY_UNIT,
			R.STATUS_FLAG,
			6 DESCEND_FLAG_BORDER	-- For setting border of descend flag in datawindows (6=3D raised)
    FROM OSP$USER_ROLES UR,   
         OSP$USER U,   
         OSP$ROLE R  
   WHERE UPPER(UR.USER_ID) = UPPER(U.USER_ID)
     AND UR.ROLE_ID = R.ROLE_ID
     AND UR.ROLE_ID = AW_ROLE_ID
     AND UR.UNIT_NUMBER = AW_UNIT_NUMBER ;   
end;

/



