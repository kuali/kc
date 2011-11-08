
CREATE OR REPLACE VIEW osp$eps_prop_person_degree ( PROPOSAL_NUMBER, PERSON_ID, DEGREE_CODE, GRADUATION_DATE, DEGREE, FIELD_OF_STUDY, SPECIALIZATION, SCHOOL, SCHOOL_ID_CODE, SCHOOL_ID, UPDATE_TIMESTAMP, UPDATE_USER) 
	   AS select a.PROPOSAL_NUMBER, decode(b.PERSON_ID,null,b.rolodex_id,b.person_id), DEGREE_CODE, to_date(GRADUATION_YEAR,'YYYY'), a.DEGREE, FIELD_OF_STUDY, SPECIALIZATION, a.SCHOOL, SCHOOL_ID_CODE, SCHOOL_ID, a.UPDATE_TIMESTAMP, a.UPDATE_USER
	from eps_prop_person_degree a ,eps_prop_person b
	where a.proposal_number = b.proposal_number
		  and a.prop_person_number = b.prop_person_number;
