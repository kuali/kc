CREATE OR REPLACE procedure get_users_for_unit
   ( AW_UNIT_NUMBER IN osp$user.unit_number%TYPE,
     cur_generic IN OUT result_sets.cur_generic) is

begin

open cur_generic for

SELECT   DISTINCT US.USER_ID,
         US.USER_NAME,
         US.NON_MIT_PERSON_FLAG,
         US.PERSON_ID,
         US.USER_TYPE,
         US.UNIT_NUMBER,
         US.STATUS,
         US.UPDATE_TIMESTAMP,
         US.UPDATE_USER,
         UN.UNIT_NAME
    FROM OSP$UNIT UN,
         OSP$USER US ,
			OSP$USER_ROLES UR
   WHERE (UR.UNIT_NUMBER = AW_UNIT_NUMBER or US.UNIT_NUMBER = AW_UNIT_NUMBER) and
			UPPER(US.USER_ID) = UPPER(UR.USER_ID (+)) and
			US.UNIT_NUMBER = UN.UNIT_NUMBER 
			ORDER BY UPPER(US.USER_ID);

end;
/
