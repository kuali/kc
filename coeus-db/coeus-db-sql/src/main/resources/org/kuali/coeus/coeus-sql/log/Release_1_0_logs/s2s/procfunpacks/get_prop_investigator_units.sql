create or replace procedure get_prop_investigator_units (
						as_proposal_num in OSP$EPS_PROP_UNITS.proposal_number%type,
						as_person_id in OSP$EPS_PROP_UNITS.PERSON_ID%type,
						acur_proposal_units IN OUT result_sets.cur_generic) is
begin
open acur_proposal_units for
	SELECT OSP$EPS_PROP_UNITS.PROPOSAL_NUMBER,
         OSP$EPS_PROP_UNITS.PERSON_ID,
         OSP$EPS_PROP_UNITS.UNIT_NUMBER,
         OSP$EPS_PROP_UNITS.LEAD_UNIT_FLAG,
        	OSP$EPS_PROP_UNITS.UPDATE_TIMESTAMP,
         OSP$EPS_PROP_UNITS.UPDATE_USER,
			OSP$UNIT.UNIT_NAME
    FROM OSP$EPS_PROP_UNITS,
         OSP$UNIT
   WHERE OSP$EPS_PROP_UNITS.PROPOSAL_NUMBER = as_proposal_num AND
   		 OSP$EPS_PROP_UNITS.PERSON_ID = as_person_id AND
			( OSP$EPS_PROP_UNITS.UNIT_NUMBER = OSP$UNIT.UNIT_NUMBER );


end;

/

