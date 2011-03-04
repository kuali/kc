CREATE OR REPLACE procedure GET_ROLES_FOR_UNIT
   ( AW_UNIT_NUMBER IN osp$unit.unit_number%TYPE,
     cur_generic IN OUT result_sets.cur_generic) is

begin

open cur_generic for
  SELECT ROLE_ID,
			DESCRIPTION,
         ROLE_NAME,
         ROLE_TYPE,
			OWNED_BY_UNIT,
			DESCEND_FLAG,
			STATUS_FLAG,
			CREATE_TIMESTAMP,
			CREATE_USER,
			UPDATE_TIMESTAMP,
			UPDATE_USER,
	   	AW_UNIT_NUMBER    -- for linking treeview levels
    FROM OSP$ROLE
   WHERE OWNED_BY_UNIT IN (AW_UNIT_NUMBER, eps_global.institute_unit_number) 
   AND ROLE_ID != 101
   ORDER BY ROLE_TYPE, ROLE_NAME;

end;
/

