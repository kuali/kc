
  CREATE OR REPLACE PROCEDURE DW_GET_CUR_SYSDATE 
   ( cur_generic IN OUT result_sets.cur_generic) is

begin

open cur_generic for

  SELECT SYSDATE FROM DUAL;

end;
 /
 