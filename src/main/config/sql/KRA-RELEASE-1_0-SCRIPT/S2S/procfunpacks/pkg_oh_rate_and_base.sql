CREATE OR REPLACE package pkg_oh_rate_and_base as

procedure get_oh_rate_and_base_for_per(av_proposal_number  IN osp$budget_details.PROPOSAL_NUMBER%TYPE,
		  					   av_version_number  IN osp$budget_details.VERSION_NUMBER%TYPE,
							   av_budget_period   IN osp$budget_details.BUDGET_PERIOD%TYPE,
							   cur_type IN OUT result_sets.cur_generic);

			


end;
/

CREATE OR REPLACE package body pkg_oh_rate_and_base as



PROCEDURE  get_oh_rate_and_base_for_per(av_proposal_number  IN osp$budget_details.PROPOSAL_NUMBER%TYPE,
		  					   av_version_number  IN osp$budget_details.VERSION_NUMBER%TYPE,
							   av_budget_period   IN osp$budget_details.BUDGET_PERIOD%TYPE,
           		         cur_type IN OUT result_sets.cur_generic)

		  					   
IS

ls_modular_flag	varchar2(3);

begin

-- determine if this is a modular budget

SELECT MODULAR_BUDGET_FLAG
INTO   ls_modular_flag
FROM	 OSP$BUDGET
WHERE  PROPOSAL_NUMBER = av_proposal_number
AND    VERSION_NUMBER = av_version_number;

if ls_modular_flag = 'N' then

	OPEN cur_type FOR
     	select 	proposal_number,
            	version_number,
			 		budget_period,
   		   	osp$budget_rate_and_base.rate_class_code AS RATE_CLASS_CODE,
			 		osp$rate_class.description as DESCRIPTION,
		    		rate_type_code,
			 		applied_rate as APPLIED_RATE,
			 		sum(base_cost) BASE_COST,
			 		sum(base_cost_sharing) BASE_COST_SHARING,
			 		sum(calculated_cost) CALCULATED_COST, 
			 		sum(calculated_cost_sharing) CALCULATED_COST_SHARING
		from   	osp$budget_rate_and_base, osp$rate_class
		where  	proposal_number = av_proposal_number
   	and      version_number = av_version_number
   	AND      budget_period = av_budget_period
   	and    	osp$budget_rate_and_base.rate_class_code = osp$rate_class.rate_class_code
   	and      rate_class_type = 'O'
		group by proposal_number, version_number, budget_period,  
					osp$budget_rate_and_base.rate_class_code, 
					osp$rate_class.description, 
					rate_type_code, applied_rate;


 else
	--modular budget
   BEGIN
    OPEN cur_type FOR
		select 	proposal_number,
            	version_number,
			 		budget_period,
			 		description ,
			 		idc_rate as APPLIED_RATE,
			 		sum(idc_base) BASE_COST,
			 		sum(funds_requested) CALCULATED_COST
		from   	osp$budget_modular_idc
		where  	proposal_number = av_proposal_number
   	and      version_number = av_version_number
   	AND      budget_period = av_budget_period
		group by proposal_number, version_number, budget_period,  
					description, 
					idc_rate;


	END;
 end if;


end;

 
END;
 


/

