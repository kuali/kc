/*******************************************************************
This procedure selects all columns from user_roles, user.user_name
and status, and role.role_name, role_type, owned_by_unit, and
status_flag for the given unit number.
*****************************************************************
ENH014 - added the OR condition in the where clause so that Institute
roles for users in the current dept (or who have roles in the current
department) will be displayed
*******************************************************************/

create or replace  procedure dw_get_user_roles_for_unit 
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
   		U.STATUS, 
   		R.ROLE_NAME, 
   		R.ROLE_TYPE, 
   		R.OWNED_BY_UNIT, 
   		R.STATUS_FLAG, 
   		6 DESCEND_FLAG_BORDER -- For setting border of descend flag in datawindows (6=3D raised) 
    FROM OSP$USER_ROLES UR, 
         OSP$USER U, 
         OSP$ROLE R 
   WHERE (UPPER(UR.USER_ID) = UPPER(U.USER_ID) 
     AND UR.ROLE_ID = R.ROLE_ID 
     AND UR.UNIT_NUMBER = AW_UNIT_NUMBER)
     OR  (upper(UR.USER_ID) = UPPER(U.USER_ID)
	  AND UR.ROLE_ID = R.ROLE_ID
  	  AND R.ROLE_TYPE = 'O'
     AND UPPER(U.USER_ID) IN (SELECT UPPER(USER_ID)          --Added upper FIX179
                       FROM OSP$USER_ROLES
							  WHERE UNIT_NUMBER = AW_UNIT_NUMBER ));

end;

/

