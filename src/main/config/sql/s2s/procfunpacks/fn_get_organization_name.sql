create or replace function fn_get_organization_name (as_organization_id in osp$organization.organization_id%TYPE)
return varchar2 is
	ls_name osp$organization.organization_name%type;
begin
		SELECT OSP$organization.organization_name 
    	INTO LS_NAME  
   	FROM OSP$organization  
   	WHERE rtrim(ltrim(organization_id)) = rtrim(ltrim(as_organization_id));

	EXCEPTION WHEN NO_DATA_FOUND THEN
            	ls_name := ' ';

return ls_name;
end;

/
