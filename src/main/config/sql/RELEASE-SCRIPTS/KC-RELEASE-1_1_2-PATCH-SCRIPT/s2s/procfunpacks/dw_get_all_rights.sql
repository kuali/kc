/********************************************************************
This procedure selects rights.*.
********************************************************************/

create or replace procedure dw_get_all_rights
   ( cur_generic IN OUT result_sets.cur_generic) is 

begin

open cur_generic for

  SELECT RIGHT_ID,   
         DESCRIPTION,   
         RIGHT_TYPE,   
         DESCEND_FLAG,   
         UPDATE_USER,   
         UPDATE_TIMESTAMP  
    FROM OSP$RIGHTS  ;

end;

/






