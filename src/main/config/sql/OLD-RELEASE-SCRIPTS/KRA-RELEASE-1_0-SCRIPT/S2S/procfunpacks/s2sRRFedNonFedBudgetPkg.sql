
CREATE OR REPLACE package s2sRRFedNonFedBudgetPkg as

procedure get_tot_Fed_NonFed_costsharing 
			 (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
				cur_type IN OUT result_sets.cur_generic);				
		  					 
end;
/

CREATE OR REPLACE package body s2sRRFedNonFedBudgetPkg as
-----------------------------------------------------------
-- procedure get_tot_Fed_NonFed_costsharing
-- gets total direct costSharing and total indirect costsharing
-----------------------------------------------------------
procedure get_tot_Fed_NonFed_costsharing (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
											cur_type IN OUT result_sets.cur_generic) is
	
	li_version 		number;
	li_sumLine 		number;
   li_sumNoOH 		number;
   li_sumOH   		number;
   li_dir_nonFunds 	number;

	begin
    	li_version := fn_get_version(as_proposal_number);
		if li_version > 0 then 
			li_sumLine := 0;
			li_sumNoOH := 0;
			li_sumOH := 0;
	
			
			select 	decode(sum(cost_sharing_amount),null,0,sum(cost_sharing_amount))
			into     li_sumLine
			from 		osp$budget_details 
			where 	proposal_number = as_proposal_number
			and 		version_number = li_version;
	
	
			select decode(sum(c.calculated_cost_sharing),null,0,sum(c.calculated_cost_sharing))
			into li_sumNoOH
			from osp$budget_details_cal_amts c,
				  osp$rate_class rc
			where c.proposal_number = as_proposal_number
			and  c.version_number = li_version
			and  c.rate_class_code = rc.rate_class_code 
			and  rc.Rate_class_type <> 'O';
	
			 select  decode(sum(a.calculated_cost_sharing),null,0,sum(a.calculated_cost_sharing))
			 into li_sumOH
			 from   		osp$budget_details_cal_amts a, osp$rate_class r
			 where  		a.proposal_number = as_proposal_number
			 and        a.version_number = li_version
			 and   		a.rate_class_code = r.rate_class_code
			 and  	   r.Rate_class_type = 'O';
	
			li_dir_nonFunds := li_sumLine + li_sumNoOH;
		else
			li_dir_nonFunds := 0;
		end if;

   open cur_type for

	SELECT li_dir_nonFunds as TOTAL_DIRECT_COST_SHARING,
	       li_sumOH as TOTAL_INDIRECT_COST_SHARING
	FROM DUAL;

   end;
   
   
  
end;
/
