create or replace procedure get_name_from_rolodex ( as_personid in osp$rolodex.rolodex_id%type, as_ret out varchar2)is  
	rolodex_rec osp$rolodex%rowtype;
	ls_temp varchar2(100)  := '';
 begin 
	select * into rolodex_rec from osp$rolodex where rolodex_id = as_personid;
	IF rolodex_rec.last_name  is NULL THEN
		ls_temp := ltrim( rtrim(rolodex_rec.organization) );
	ELSE
		ls_temp := ltrim(rtrim(rolodex_rec.last_name));
		IF rolodex_rec.suffix is NULL THEN
			ls_temp := ls_temp || ',';
		ELSE
			ls_temp := ls_temp || ' ' || ltrim(rtrim(rolodex_rec.suffix)) || ',';
		END IF;
		IF rolodex_rec.prefix is NOT NULL THEN
			ls_temp := ls_temp || ' ' || ltrim(rtrim(rolodex_rec.prefix));
		END IF;
		IF rolodex_rec.first_name is NOT NULL THEN
			ls_temp := ls_temp || ' ' || ltrim(rtrim(rolodex_rec.first_name)) || ' ';
		END IF;
		IF rolodex_rec.middle_name is NOT NULL THEN
			ls_temp := ls_temp || ' ' || ltrim(rtrim(rolodex_rec.middle_name));
		END IF;
	END IF;
	as_ret := ls_temp;
end; 

/


