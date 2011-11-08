CREATE OR REPLACE function get_sponsor_name_new (as_sponsor_code in osp$sponsor.sponsor_code%TYPE)
return varchar2 is
	ls_name osp$sponsor.sponsor_name%type;
begin
		SELECT OSP$SPONSOR.SPONSOR_NAME
    	INTO LS_NAME
   	FROM OSP$SPONSOR
   	WHERE UPPER(SPONSOR_CODE) = upper(rtrim(ltrim(as_sponsor_code)));

return ls_name;
EXCEPTION
 WHEN NO_DATA_FOUND THEN
  RETURN '' ;
end;
/

