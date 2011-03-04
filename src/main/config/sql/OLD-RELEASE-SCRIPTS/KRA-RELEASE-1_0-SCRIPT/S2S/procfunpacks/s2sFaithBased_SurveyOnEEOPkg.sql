
CREATE OR REPLACE package s2sFaithBased_SurveyOnEEOPkg as

procedure get_eps_org_info (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic);
			
procedure getS2sFedProgram (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic);			
			
end; 


/




CREATE OR REPLACE package body s2sFaithBased_SurveyOnEEOPkg as

----------------------------------
-- procedure get_eps_org_info
----------------------------------

procedure get_eps_org_info (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic)
is
 begin
 open cur_proposal for
 	
     SELECT   osp$eps_proposal.ORGANIZATION_ID,
     	      osp$organization.DUNS_NUMBER,
              osp$organization.organization_name          
     FROM     osp$eps_proposal, osp$organization
     WHERE    osp$eps_proposal.PROPOSAL_NUMBER = as_proposal_number 
     and      osp$eps_proposal.organization_id = osp$organization.organization_id ;
 	
end;

-------------------------------
-- procedure getS2sFedProgram
-------------------------------

procedure getS2sFedProgram
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_proposal IN OUT result_sets.cur_generic) 
is
     
 begin
	GET_S2S_OPPORTUNITY(as_proposal_number, cur_proposal);

 end;


end;

/

