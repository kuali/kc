CREATE OR REPLACE procedure dw_get_user
   ( AW_USER_ID IN osp$user.user_id%TYPE,
	  cur_generic IN OUT result_sets.cur_generic) is

begin

open cur_generic for
  SELECT US.*,
			UN.UNIT_NAME
    FROM OSP$USER US,
			OSP$UNIT UN
   WHERE UPPER(USER_ID) = UPPER(AW_USER_ID)
	  AND UN.UNIT_NUMBER = US.UNIT_NUMBER ;
end;
/

