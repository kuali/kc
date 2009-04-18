CREATE OR REPLACE VIEW OSP$V_SALARY_SUMMARY AS 
                       select 
                            a.proposal_number, 
                            a.version_number, 
                            a.budget_period, 
                            a.line_item_number,
                            decode(b.start_date, null, a.START_DATE, b.START_DATE) START_DATE,
                            decode(b.end_date, null, a.END_DATE, b.END_DATE) END_DATE,
                            f.description category, 
                            p.full_name person_name, 
                            b.percent_effort, 
                            b.percent_charged, 
                            null eb_rate, 
                            null vac_rate, 
                            a.BUDGET_CATEGORY_CODE, 
                            decode(b.SALARY_REQUESTED, null, a.LINE_ITEM_cost, b.SALARY_REQUESTED) salary , 
                            b.cost_sharing_amount cost_sharing_amount,
                            null fringe, 
                            null fringe_cost_sharing,
                            DECODE(inv.PRINCIPAL_INVESTIGATOR_FLAG, 'Y', '1', 'N', '2', NULL, '3', '4') AS PRINCIPAL_INVESTIGATOR, 
                            g.COST_ELEMENT, 
                            g.description cost_element_desc                   
                    from osp$budget_details a,
                         osp$budget_personnel_details b,
                         osp$person p ,
                         osp$budget_category f,
                         osp$cost_element g ,
                         osp$eps_prop_investigators inv
                    where
                         a.proposal_number = b.proposal_number (+) and
                         a.version_number = b.version_number (+) and
                         a.budget_period = b.budget_period (+) and
                         a.line_item_number = b.line_item_number (+) and
                         b.person_id = p.person_id (+) and
                         a.budget_category_code = f.budget_category_code and
                         a.budget_category_code = g.budget_category_code and
                         a.cost_element = g.cost_element and
                         b.proposal_number = inv.proposal_number (+) and
                         b.person_id = inv.person_id (+)
                         and f.CATEGORY_TYPE = 'P' ;

