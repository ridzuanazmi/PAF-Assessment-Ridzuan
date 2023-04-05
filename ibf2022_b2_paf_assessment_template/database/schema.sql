-- Create a new schema as per application.properties
create database bgg;

-- Use the schema 
use bgg;

 -- Create table user
create table user (
    user_id 			varchar(8) not null,
    username 			text not null,
    name 				text not null,
    constraint			users_pk primary key (user_id)			
);

-- Create Operation using given values
insert into user values ('1b80114c', 'fred', 'Fred');
insert into user values ('cf66dae3', 'wilma', 'Wilma');
insert into user values ('a8b9800d', 'barney', 'Barney');
insert into user values ('66223e28', 'betty', 'Betty');

-- Read operation to check entries
select * from user;

-- Read operation with condition
select * from user where username = 'fred';

-- Create table 'task'
create table task (
	task_id			int not null auto_increment,
    user_id			varchar(8) not null,
    description		varchar(255) not null,
    priority		int not null ,
    due_date		date not null,
	constraint		task_pk primary key (task_id),
    constraint		task_user_fk foreign key(user_id) references user(user_id),
    constraint		chk_priority check(priority between 1 and 3)	
);

-- Create operation for task table
insert into task (user_id, description, priority, due_date) values ('66223e28', 'jogging', 2, '2023-5-7');

-- Left join to check data entries
select * 
from user
left join task
on user.user_id = task.user_id;

-- Read operation to check task table entries
select * from task;

-- Delete operation
delete from user where user_id = "7e9f7155"