
create table KREW_AUTO_INGESTED_CHECKSUM (
	file_name	varchar(100)	primary key,
	checksum	int(11)	not null,
	last_ingested timestamp
);