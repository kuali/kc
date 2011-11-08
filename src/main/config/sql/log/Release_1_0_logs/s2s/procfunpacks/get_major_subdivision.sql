CREATE OR REPLACE PROCEDURE get_major_subdivision (
  as_unit_num in osp$unit.unit_number%type,
  as_maj_sub_unit_name out osp$unit.unit_name%type)

is

 li_index     number;
 TYPE r_units IS RECORD  ( unit_number varchar2(8));
 TYPE t_units_list IS TABLE OF r_units INDEX BY BINARY_INTEGER;
 unit_list      t_units_list;
   ls_unit     varchar2(8);
   is_found     boolean;
 li_count     number;
 ls_parent      varchar2(8);
 ls_major_sub_unit  varchar2(8);

begin
 unit_list.delete;
 li_index := 0;
 -- build a table with all the level 4 units
 DECLARE CURSOR c_units IS
  SELECT UNIT_NUMBER
  FROM   osp$unit_hierarchy
  WHERE  LEVEL = 4
  start with unit_number = '000001'
  connect by prior unit_number = parent_unit_number;
 BEGIN
   OPEN c_units;
         LOOP
    FETCH c_units INTO ls_unit;
      EXIT WHEN c_units%NOTFOUND;
    li_index := li_index + 1;
    unit_list(li_index).unit_number := ls_unit;
   END LOOP;
   CLOSE c_units;
  -- now we have a table of units at level 4 in the heirarchy.
  -- go up hierarchy from lead unit and see if we get to a level 4
  -- if not , return the lead unit
   is_found := false;
   ls_unit  := as_unit_num;
   li_count := li_index;
   ls_parent := as_unit_num;
    while (is_found = false and ls_parent <> '000001') loop
    li_index := li_count;
    SELECT PARENT_UNIT_NUMBER
    INTO  Ls_parent
    FROM  OSP$UNIT_hierarchy
    WHERE UNIT_NUMBER = ls_UNIT;
     while (li_index > 0  and is_found = false ) loop
      if ls_parent = unit_list(li_index).unit_number then
       is_found := true;
      else
       li_index := li_index - 1 ;
      end if;
      ls_unit:= ls_parent;
      end loop;
   end loop;
   if is_found = false then
    ls_major_sub_unit := as_unit_num;
   else
    ls_major_sub_unit := ls_parent;
   end if;
  end;
 SELECT UNIT_NAME
 INTO   as_maj_sub_unit_name
 FROM  OSP$UNIT
 WHERE  UNIT_NUMBER = ls_major_sub_unit;

 EXCEPTION

	WHEN NO_DATA_FOUND THEN
		as_maj_sub_unit_name := '';
end;
/

