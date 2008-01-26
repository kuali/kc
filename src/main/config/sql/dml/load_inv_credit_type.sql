#
# $Id: load_inv_credit_type.sql,v 1.3 2008-01-26 19:18:52 lprzybyl Exp $
#
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER) values ('0', 'Recognition', 'Y', sysdate, 'kradev');
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER) values ('1', 'Responsibility', 'Y', sysdate, 'kradev');
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER) values ('2', 'Space', 'Y', sysdate, 'kradev');
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER) values ('3', 'Financial', 'Y', sysdate, 'kradev');
