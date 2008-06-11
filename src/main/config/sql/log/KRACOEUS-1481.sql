
alter table institute_rates add (active_flag char(1) default 'Y' constraint institute_ratesN15 NOT NULL);

alter table institute_la_rates add (active_flag char(1) default 'Y' constraint institute_la_ratesN15 NOT NULL);
