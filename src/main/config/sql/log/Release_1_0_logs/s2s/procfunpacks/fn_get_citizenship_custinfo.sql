CREATE OR REPLACE function fn_get_citizenship_custinfo (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%type)
return varchar2 is
    ls_citizenship varchar2(50);
    ls_person_id osp$eps_prop_investigators.person_id%type;

--valid enumeration values: 
--"U.S. Citizen or noncitizen national"
--"Permanent Resident of U.S."
--"Non-U.S. Citizen with temporary visa"

--get info for the pi 

begin

-- get the pi
     SELECT   PERSON_ID
     INTO     ls_person_id
	 FROM     OSP$eps_PROP_INVESTIGATORS
	 WHERE 	  PROPOSAL_NUMBER = as_proposal_number
     AND      PRINCIPAL_INVESTIGATOR_FLAG = 'Y';

 begin
--  SELECT decode(COLUMN_VALUE, 'C','U.S. Citizen or noncitizen national','N','Permanent Resident of U.S.',
--      'A','Non-U.S. Citizen with temporary visa')   as CITIZENSHIP_INFO
--  INTO ls_citizenship
--  FROM   OSP$EPS_PROP_PER_CUSTOM_DATA
--  WHERE  PROPOSAL_NUMBER= as_proposal_number
--  AND    PERSON_ID = ls_person_id
--  AND    COLUMN_NAME = 'CITIZENSHIP_INFO';
    select 'Permanent Resident of U.S.'   as CITIZENSHIP_INFO INTO ls_citizenship from dual;
  EXCEPTION
      When NO_DATA_FOUND then
        ls_citizenship := null;
  end;
  
  return ls_citizenship;
  
  end;
/

