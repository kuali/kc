create or replace procedure get_person_info ( as_personid in osp$person.person_id%type,
		as_full_name out osp$person.full_name%type,
		as_directory_title out osp$person.directory_title%type,
		as_faculty_flag out varchar2,
		as_home_unit out osp$person.home_unit%type) is 
begin 
	select full_name,directory_title,is_faculty,HOME_UNIT
	into as_full_name,as_directory_title,as_faculty_flag,as_home_unit 
	from osp$person 
	where person_id = as_personid; 
end; 

/



