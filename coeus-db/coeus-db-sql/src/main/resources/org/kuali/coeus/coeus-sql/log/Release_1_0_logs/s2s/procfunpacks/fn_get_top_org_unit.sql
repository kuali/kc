CREATE OR REPLACE FUNCTION fn_get_top_org_unit (as_unit_number  in osp$unit.UNIT_NUMBER%TYPE)
RETURN VARCHAR2 IS

------------------------------------------------------------------
-- this function gets the top unit for a given unit. top unit is
-- defined as the first unit found going up the hierarchy that has an organization
------------------------------------------------------------------

ls_org         osp$unit.ORGANIZATION_ID%type;
ls_unit_number OSP$UNIT.UNIT_NUMBER%TYPE;
ls_topunit     osp$unit.unit_number%type;


CURSOR C_UNIT_HIER IS
SELECT UNIT_NUMBER
FROM OSP$UNIT_HIERARCHY
START WITH UNIT_NUMBER = as_unit_number
CONNECT BY UNIT_NUMBER = PRIOR PARENT_UNIT_NUMBER ;

BEGIN

ls_TopUnit := '000001';

--Traverse the hierarchy starting at unit_number 
--moving towards root until unit has an organization

OPEN C_UNIT_HIER;
 
	LOOP
		FETCH C_UNIT_HIER
		INTO ls_unit_number;
		EXIT WHEN C_UNIT_HIER%NOTFOUND;


		--check if this unit has an org associated with it
		SELECT organization_id
		INTO   ls_org
		FROM   OSP$UNIT
		WHERE  UNIT_NUMBER = ls_unit_number;

	   IF length(ls_org) >0 THEN

		   ls_topUnit := ls_unit_number;
         EXIT;

	   END IF;

	END LOOP;
   CLOSE C_UNIT_HIER;


	RETURN ls_topUnit;


END;
/
