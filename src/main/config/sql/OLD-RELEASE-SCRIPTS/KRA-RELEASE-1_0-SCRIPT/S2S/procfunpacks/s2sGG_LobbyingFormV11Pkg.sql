
CREATE OR REPLACE package s2sGG_LobbyingFormV11Pkg as

procedure get_eps_org_name (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic);
			
end; 


/




CREATE OR REPLACE package body s2sGG_LobbyingFormV11Pkg as

----------------------------------
-- procedure get_eps_org_name
----------------------------------

procedure get_eps_org_name (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic)
is
 begin
 open cur_proposal for
 	
     SELECT   osp$eps_proposal.ORGANIZATION_ID,
              osp$organization.organization_name          
     FROM     osp$eps_proposal, osp$organization
     WHERE    osp$eps_proposal.PROPOSAL_NUMBER = as_proposal_number 
     and      osp$eps_proposal.organization_id = osp$organization.organization_id ;
 	
end;
 
 

end;

/

