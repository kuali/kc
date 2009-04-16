CREATE OR REPLACE PROCEDURE          DW_GET_STATE_CODES 
  ( cur_generic IN OUT result_sets.cur_generic) is

begin

open cur_generic for
  SELECT *
    FROM OSP$STATE_CODE
	order by description;
end;
/

