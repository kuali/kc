/**********************************************************************
This function checks if a module has custom data defined for it.

Return values:
	1 = ok 
	0 = not 
**********************************************************************/

create or replace function fn_module_has_custom_data (
		 aw_module_code IN osp$custom_data_element_usage.module_code%TYPE)
	RETURN smallint

is

li_count smallint;
li_ret_code smallint := NULL;
	

begin

	SELECT COUNT(*)
  INTO li_Count
  FROM OSP$CUSTOM_DATA_ELEMENT_USAGE
  WHERE OSP$CUSTOM_DATA_ELEMENT_USAGE.MODULE_CODE = AW_MODULE_CODE;
	
	IF li_count > 0 THEN
		li_ret_code := 1;
	ELSE
		li_ret_code := 0;
	END IF;

	RETURN (li_ret_code);
end;

/


