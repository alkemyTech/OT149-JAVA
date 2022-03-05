alter table user drop if exists username;
alter table user modify email varchar(265) not null unique;