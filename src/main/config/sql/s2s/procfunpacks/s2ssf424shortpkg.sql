
CREATE OR REPLACE package s2sSF424ShortPkg as

procedure get_eps_infos (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic);
			
procedure get_contact_person_info(as_proposal_number in osp$eps_proposal.proposal_number%type,
           		cur_type IN OUT result_sets.cur_generic);
           		
procedure getProjectSummary(as_proposal_number IN osp$eps_proposal.proposal_number%type,
           		cur_type IN OUT result_sets.cur_generic);
           		
procedure get_project_director(AS_PROPOSAL_NUMBER IN OSP$EPS_PROP_PERSON.PROPOSAL_NUMBER%TYPE,
     			cur_generic IN OUT result_sets.cur_generic);
           
procedure getApplicantType(as_ORG_TYPE_CODE IN OSP$ORGANIZATION_TYPE_LIST.ORGANIZATION_TYPE_CODE%type,
           		cur_type IN OUT result_sets.cur_generic);
           		
procedure getS2sOpportunity(as_proposal_number IN osp$eps_proposal.proposal_number%type,
           		cur_type IN OUT result_sets.cur_generic);         		
			
procedure getStateName(as_state_code IN osp$state_code.state_code%type,
           		cur_type IN OUT result_sets.cur_generic);
           
procedure getCountryName(as_country_code IN osp$country_code.country_code%type,
           		cur_type IN OUT result_sets.cur_generic) ;
           			

			
end; 


/



CREATE OR REPLACE package body s2sSF424ShortPkg as

----------------------------------
-- procedure get_eps_infos
--changed for case 2406  
----------------------------------

procedure get_eps_infos (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic)
is
 begin
 open cur_proposal for
 	
  --   SELECT   osp$eps_proposal.ORGANIZATION_ID,
       SELECT sites.organization_id,
     	      osp$eps_proposal.PROGRAM_ANNOUNCEMENT_TITLE,  
     	      osp$eps_proposal.CFDA_NUMBER, 
     	      osp$eps_proposal.TITLE, 
     	      osp$eps_proposal.REQUESTED_START_DATE_INITIAL, 
     	      osp$eps_proposal.REQUESTED_END_DATE_INITIAL, 
     	      osp$organization.CONTACT_ADDRESS_ID,
     	      osp$organization.CONGRESSIONAL_DISTRICT,
              osp$organization.organization_name ,
              s.sponsor_name as SPONSOR_NAME,
              ps.sponsor_name as PRIME_SPONSOR_NAME,
              'Prime'	AS REPORT_ENTITY_TYPE
     FROM     osp$eps_proposal, osp$organization,
	          osp$eps_prop_sites sites,
     	      OSP$SPONSOR S, OSP$SPONSOR PS
     WHERE    osp$eps_proposal.PROPOSAL_NUMBER = as_proposal_number 
   --  and      osp$eps_proposal.organization_id = osp$organization.organization_id
     and      sites.proposal_number=osp$eps_proposal.proposal_number
     and      sites.organization_id = osp$organization.organization_id
	 and      sites.location_type_code = 1 
     and      osp$eps_proposal.SPONSOR_CODE = S.SPONSOR_CODE(+)
     and      osp$eps_proposal.PRIME_SPONSOR_CODE = PS.SPONSOR_CODE(+);
 	
end;

-------------------------------
-- procedure getProjectSummary
-------------------------------

procedure getProjectSummary
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is

 begin
	open cur_type for
	SELECT ABSTRACT_TYPE_CODE,
	       ABSTRACT as PROJECT_DESCRIPTION
	FROM 	 OSP$EPS_PROP_ABSTRACT
	WHERE  PROPOSAL_NUMBER = as_proposal_number
   	AND    ABSTRACT_TYPE_CODE = 1 ;

 end;

-------------------------------
-- procedure get_project_director -- PI
-------------------------------
procedure get_project_director
   ( AS_PROPOSAL_NUMBER IN OSP$EPS_PROP_PERSON.PROPOSAL_NUMBER%TYPE,
     cur_generic IN OUT result_sets.cur_generic) is
begin
  open cur_generic for
     	SELECT decode(OSP$EPS_PROP_PERSON.FIRST_NAME, null, OSP$EPS_PROP_PERSON.LAST_NAME, OSP$EPS_PROP_PERSON.FIRST_NAME) as FIRST_NAME,
        	OSP$EPS_PROP_PERSON.MIDDLE_NAME,
        	decode(OSP$EPS_PROP_PERSON.LAST_NAME,null,OSP$EPS_PROP_PERSON.FIRST_NAME, OSP$EPS_PROP_PERSON.LAST_NAME) as LAST_NAME,
     		OSP$EPS_PROP_PERSON.OFFICE_PHONE, --fn_format_phone_number(OSP$EPS_PROP_PERSON.OFFICE_PHONE),
     		OSP$EPS_PROP_PERSON.FAX_NUMBER, --fn_format_phnoe_number(OSP$EPS_PROP_PERSON.FAX_NUMBER),
     		OSP$EPS_PROP_PERSON.EMAIL_ADDRESS,
     		OSP$EPS_PROP_PERSON.PRIMARY_TITLE,
     		decode(OSP$EPS_PROP_PERSON.ADDRESS_LINE_1, null, decode(OSP$EPS_PROP_PERSON.ADDRESS_LINE_2, null, OSP$EPS_PROP_PERSON.ADDRESS_LINE_3,OSP$EPS_PROP_PERSON.ADDRESS_LINE_2) ,OSP$EPS_PROP_PERSON.ADDRESS_LINE_1)as ADDRESS_LINE_1,
              	OSP$EPS_PROP_PERSON.ADDRESS_LINE_2,
              	OSP$EPS_PROP_PERSON.CITY,
              	OSP$EPS_PROP_PERSON.COUNTY,
              	OSP$EPS_PROP_PERSON.STATE,
              	OSP$EPS_PROP_PERSON.POSTAL_CODE,
              	OSP$EPS_PROP_PERSON.COUNTRY_CODE
     	FROM	OSP$EPS_PROP_PERSON, OSP$EPS_PROP_INVESTIGATORS
     	WHERE	OSP$EPS_PROP_INVESTIGATORS.PROPOSAL_NUMBER = as_proposal_number
     		AND	OSP$EPS_PROP_INVESTIGATORS.PRINCIPAL_INVESTIGATOR_FLAG = 'Y'
     		AND	OSP$EPS_PROP_PERSON.PROPOSAL_NUMBER = OSP$EPS_PROP_INVESTIGATORS.PROPOSAL_NUMBER
		AND	OSP$EPS_PROP_INVESTIGATORS.PERSON_ID = OSP$EPS_PROP_PERSON.PERSON_ID;
   	


end;

-------------------------------
-- procedure get_contact_person_info
-------------------------------
procedure get_contact_person_info
          (as_proposal_number in osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is
begin

	s2sPHS398CoverPageSupplemtPkg.get_contact_person_info(as_proposal_number,cur_type);

end;


-------------------------------
-- procedure getApplicantType
-------------------------------
procedure getApplicantType
          (as_ORG_TYPE_CODE IN OSP$ORGANIZATION_TYPE_LIST.ORGANIZATION_TYPE_CODE%type,
           cur_type IN OUT result_sets.cur_generic) is

begin

	s2sSF424V2Pkg.getSF424V2ApplicantType(as_ORG_TYPE_CODE,cur_type);

end;

-------------------------------
-- procedure getS2sOpportunity
-------------------------------
procedure getS2sOpportunity
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is
begin

	GET_S2S_OPPORTUNITY(as_proposal_number, cur_type);
end;

-------------------------------
-- procedure getStateName
-------------------------------
procedure getStateName
          (as_state_code IN osp$state_code.state_code%type,
           cur_type IN OUT result_sets.cur_generic) is
begin

	s2sSF424V2Pkg.getStateName(as_state_code,cur_type);

end;

-------------------------------
-- procedure getCountryName
-------------------------------
procedure getCountryName
          (as_country_code IN osp$country_code.country_code%type,
           cur_type IN OUT result_sets.cur_generic) is

begin
	s2sSF424V2Pkg.getCountryName(as_country_code,	cur_type);			
				
end;

end;

/

