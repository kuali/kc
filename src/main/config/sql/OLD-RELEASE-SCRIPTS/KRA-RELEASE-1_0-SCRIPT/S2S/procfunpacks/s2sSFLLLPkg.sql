
CREATE OR REPLACE package s2sSFLLLPkg as

procedure get_types (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic);

procedure get_eps_infos (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic);
			
procedure getStateName
          (as_state_code IN osp$state_code.state_code%type,
           cur_type IN OUT result_sets.cur_generic);
           
procedure getCountryName
          (as_country_code IN osp$country_code.country_code%type,
           cur_type IN OUT result_sets.cur_generic) ;
           			

			
end; 


/




CREATE OR REPLACE package body s2sSFLLLPkg as

----------------------------------
-- procedure get_types
----------------------------------

procedure get_types (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic)
is
 begin
 open cur_proposal for
 	
     SELECT   'Grant' AS FED_ACTION_TYPE,
     	      'BidOffer' AS FED_ACTION_STATUS,
              'InitialFiling'   AS REPORT_TYPE                
     FROM     dual;
     
 	
end;


----------------------------------
-- procedure get_eps_infos
----------------------------------

procedure get_eps_infos (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic)
is
 begin
 open cur_proposal for
 	
     SELECT   osp$eps_proposal.ORGANIZATION_ID,
     	      osp$eps_proposal.PROGRAM_ANNOUNCEMENT_TITLE,  
     	      osp$eps_proposal.CFDA_NUMBER, 
     	      osp$organization.CONTACT_ADDRESS_ID,
     	      osp$organization.CONGRESSIONAL_DISTRICT,
              osp$organization.organization_name ,
              s.sponsor_name as SPONSOR_NAME,
              ps.sponsor_name as PRIME_SPONSOR_NAME,
              'Prime'	AS REPORT_ENTITY_TYPE
     FROM     osp$eps_proposal, osp$organization,
     	      OSP$SPONSOR S, OSP$SPONSOR PS
     WHERE    osp$eps_proposal.PROPOSAL_NUMBER = as_proposal_number 
     and      osp$eps_proposal.organization_id = osp$organization.organization_id
     and      osp$eps_proposal.SPONSOR_CODE = S.SPONSOR_CODE(+)
     and      osp$eps_proposal.PRIME_SPONSOR_CODE = PS.SPONSOR_CODE(+);
 	
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

