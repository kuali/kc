CREATE OR REPLACE function FN_GET_UNIT_NAME
   ( AW_UNIT_NUMBER IN osp$unit.unit_number%TYPE )
	RETURN osp$unit.unit_name%TYPE

IS
	var_unit_name osp$unit.unit_name%TYPE;

BEGIN
   SELECT unit_name
	INTO	 var_unit_name
	FROM   osp$unit
	WHERE  unit_number = AW_UNIT_NUMBER ;

	RETURN (var_unit_name) ;

EXCEPTION
	WHEN NO_DATA_FOUND THEN
		RETURN (var_unit_name) ;

END;
/

