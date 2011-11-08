CREATE OR REPLACE function FN_GET_CITIZENSHIP_INFO(ls_person_id OSP$EPS_PROP_INVESTIGATORS.PERSON_ID%type)
return varchar2 is
    ls_citizenship varchar2(50);
begin


-- codes and descriptions: 
-- A : Non-Resident 
-- C : Citizen 
-- N : Non - Citizen 
-- null : N/A
-- null : null 

--valid enumeration values: 
--"U.S. Citizen or noncitizen national"
--"Permanent Resident of U.S."
--"Non-U.S. Citizen with temporary visa"

    begin
--     select  decode(residency_status_code, 'C','U.S. Citizen or noncitizen national','N','Permanent Resident of U.S.',
--      'A','Non-U.S. Citizen with temporary visa',null,null)
--    into ls_citizenship
--    from    warehouse_person
--    where   person_id = ls_person_id;
	  select 'Permanent Resident of U.S.'   as CITIZENSHIP_INFO INTO ls_citizenship from dual;

    EXCEPTION
	  When NO_DATA_FOUND then
		ls_citizenship := null;
    end;
 
return ls_citizenship;

 end;
/