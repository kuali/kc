-- This script assumes that you want to convert any budget personnel entries from REGULAR EMPLOYEE to 12M DURATION.
-- If you want to keep any existing entries that are set to REGULAR EMPLOYEE, then don't apply this script.
update BUDGET_PERSONS set APPOINTMENT_TYPE_CODE='6' where APPOINTMENT_TYPE_CODE='7';