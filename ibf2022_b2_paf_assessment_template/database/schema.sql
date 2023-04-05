create database bgg;

use bgg;

create table user (
    user_id 			varchar(8) not null,
    username 			text not null,
    name 				text not null,
    constraint			users_pk primary key (user_id)			
);

insert into user values ('1b80114c', 'fred', 'Fred');
insert into user values ('cf66dae3', 'wilma', 'Wilma');
insert into user values ('a8b9800d', 'barney', 'Barney');
insert into user values ('66223e28', 'betty', 'Betty');

select * from user;

select * from user where username = 'fred';

create table task (
	task_id			int not null auto_increment,
    user_id			varchar(8) not null,
    description		text not null,
    priority		int not null ,
    due_date		date not null,
	constraint		task_pk primary key (task_id),
    constraint		task_user_fk foreign key(user_id) references user(user_id),
    constraint		chk_priority check(priority between 1 and 3)	
);

insert into task (user_id, description, priority, due_date) values ('66223e28', 'jogging', 2, '2023-5-7');

select * from task;