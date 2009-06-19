CREATE OR REPLACE procedure get_max_prop_degree_for_one(
						as_proposal_num in osp$eps_prop_person_degree.proposal_number%type,
						as_person_id in osp$eps_prop_person_degree.person_id%type,
						acur_prop_person_degree IN OUT result_sets.cur_generic) is
begin
open acur_prop_person_degree for
	SELECT pd.degree_code,
		pd.proposal_number,
		pd.person_id,
		pd.graduation_date,
		pd.degree,
		pd.field_of_study,
		pd.specialization,
		pd.school,
		pd.school_id_code,
		pd.school_id,
		pd.update_timestamp,
		pd.update_user,		
		DT.DESCRIPTION AS DEGREEDESC,
		SC.DESCRIPTION AS SCHOOLDESC
	from osp$eps_prop_person_degree PD,
	   OSP$DEGREE_TYPE DT,
	   OSP$SCHOOL_CODE SC
	where PD.PERSON_ID = as_person_id and
	   PD.PROPOSAL_NUMBER = as_proposal_num and
   	PD.DEGREE_CODE = DT.DEGREE_CODE(+) and
	   PD.SCHOOL_ID_CODE = SC.SCHOOL_CODE(+)
   order by dt.degree_level desc, pd.graduation_date desc;
end;
/





