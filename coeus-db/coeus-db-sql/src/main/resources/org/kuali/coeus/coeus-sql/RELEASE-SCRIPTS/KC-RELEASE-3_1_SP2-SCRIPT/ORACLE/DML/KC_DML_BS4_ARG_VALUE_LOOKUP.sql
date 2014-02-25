-- I have not been able to find any instances in KC where deleting these entries will cause a problem. In
-- fact, I cannot find anywhere that they are yet used. However, with that in mind, you should receive
-- a referential integrity error if you have existing data that will cause an issue here. In that case,
-- skip this script because it is not necessary for proper operation of KC.
delete from arg_value_lookup where argument_name='PeriodTypes' and description='Cycle';
delete from arg_value_lookup where argument_name='AppointmentTypes' and description='REG EMPLOYEE';
commit;