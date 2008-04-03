CREATE OR REPLACE FUNCTION GetPersonOrg ( AW_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
									               AW_PERSON_ID OSP$EPS_PROP_KEY_PERSONS.PERSON_ID%TYPE)
RETURN VARCHAR2 is
ls_org	varchar2(80);
li_count	number;
BEGIN
SELECT 	COUNT(*)
INTO   	li_count
FROM     OSP$PERSON
WHERE    PERSON_ID = AW_PERSON_ID;

IF li_count > 0 then
	--	SELECT ORGANIZATION_NAME
	--	INTO		ls_org
	--	FROM   osp$ORGANIZATION
	--	WHERE  ORGANIZATION_ID = '000001';
		select 	o.organization_name
		into     ls_org
	   from 		osp$organization o, osp$eps_proposal p
		where 	o.organization_id = p.organization_id
		and 		p.proposal_number= AW_PROPOSAL_NUMBER;
ELSE
		SELECT 	ORGANIZATION
		INTO		ls_org
		FROM 		OSP$ROLODEX
		WHERE    ROLODEX_ID = AW_PERSON_ID;
END IF;

IF ls_org IS NOT NULL THEN
    return ls_org;
ELSE
	return '';
END IF;
 end;
/

