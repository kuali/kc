create or replace FUNCTION          "GET_PARAMETER_VALUE" (
	as_parameter in osp$parameter.parameter%TYPE)

return varchar2 is
	ls_value osp$parameter.value%type;
begin
	select osp$parameter.value
	into ls_value
	from osp$parameter
	where parameter = rtrim(ltrim(as_parameter));

return ls_value;

EXCEPTION
 WHEN NO_DATA_FOUND THEN
  return '';

end;
/