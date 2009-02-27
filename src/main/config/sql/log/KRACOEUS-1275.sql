alter table person add (active_flag char(1) default 'Y' NOT NULL);
update person set active_flag = 'N' where user_name = 'KULUSER';
