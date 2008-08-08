-- 
-- SQL script to patch a release 1.0 DB to release 1.0.1 DB
-- Created 6-AUG-2008 by Tyler Wilson
--
-- Includes JIRA Fixes:
--   KRACOEUS-1757 7-AUG-2008 Tyler Wilson
--      - Changed sh_parm_txt from 1 to 7 (TEMPORARY EMPLOYEE to REGULAR EMPLOYEE)

-- KRACOEUS-1757 
UPDATE sh_parm_t
SET sh_parm_txt = '7'
WHERE sh_parm_nm = 'budgetPersonDefaultAppointmentType';