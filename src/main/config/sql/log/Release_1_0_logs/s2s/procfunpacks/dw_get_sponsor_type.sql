CREATE OR REPLACE PROCEDURE  DW_GET_SPONSOR_TYPE 
   ( cur_sponsor_type IN OUT result_sets.cur_generic) is

begin

open cur_sponsor_type for
   select *
     from osp$sponsor_type
	  order by sponsor_type_code asc;
end;
/

