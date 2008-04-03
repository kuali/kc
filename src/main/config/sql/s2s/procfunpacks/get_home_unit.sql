/**********************************************************************
This procedure gets the home_unit for the given person_id.
**********************************************************************/

create or replace procedure get_home_unit
   (  AW_PERSON_ID IN osp$person.person_id%TYPE,
		as_home_unit  out osp$person.home_unit%TYPE) IS 
	
BEGIN

   SELECT home_unit
	INTO	 as_home_unit
	FROM   osp$person
	WHERE  UPPER(person_id) = UPPER(AW_PERSON_ID) ;
	EXCEPTION
		when no_data_found then
		as_home_unit := '000001';
END;
/

