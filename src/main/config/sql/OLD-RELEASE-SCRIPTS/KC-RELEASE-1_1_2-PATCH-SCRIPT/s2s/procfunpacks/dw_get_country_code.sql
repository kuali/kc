CREATE OR REPLACE PROCEDURE          DW_GET_COUNTRY_CODE 
   ( cur_generic IN OUT result_sets.cur_generic) is

begin

	open cur_generic for
  	SELECT COUNTRY_CODE,
   		 COUNTRY_NAME,
   		 UPDATE_TIMESTAMP,
   		 UPDATE_USER
     FROM OSP$COUNTRY_CODE ;

end;
/

