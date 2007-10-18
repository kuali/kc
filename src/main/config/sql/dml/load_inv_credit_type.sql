#
# $Id: load_inv_credit_type.sql,v 1.2 2007-10-18 15:49:28 lprzybyl Exp $
#
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER) values ('0', 'Recognition', 'N', sysdate, 'kradev');
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER) values ('1', 'Responsibility', 'N', sysdate, 'kradev');
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER) values ('2', 'Space', 'N', sysdate, 'kradev');
insert into INV_CREDIT_TYPE (INV_CREDIT_TYPE_CODE, DESCRIPTION, ADDS_TO_HUNDRED, UPDATE_TIMESTAMP, UPDATE_USER) values ('3', 'Financial', 'N', sysdate, 'kradev');
