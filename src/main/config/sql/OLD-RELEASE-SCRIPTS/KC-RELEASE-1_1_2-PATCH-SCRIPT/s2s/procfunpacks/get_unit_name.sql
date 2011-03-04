create or replace procedure get_unit_name ( 
	as_unit_num in osp$unit.unit_number%type, 
	as_unit_name out osp$unit.unit_name%type)is  
begin 
	select unit_name into as_unit_name from osp$unit where ltrim(rtrim(unit_number)) = ltrim(rtrim(as_unit_num));
end; 

/


