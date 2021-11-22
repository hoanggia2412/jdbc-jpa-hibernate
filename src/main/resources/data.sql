insert into course(id, name, created_date, last_updated_date, is_deleted) values (10001,'JPA in 50 steps',sysdate(),sysdate(),false);
insert into course(id,name, created_date, last_updated_date,is_deleted) values (10002,'Spring in 100 steps',sysdate(),sysdate(),false);
insert into course(id,name, created_date, last_updated_date,is_deleted) values (10003,'Spring Boot in 100 steps',sysdate(),sysdate(),false);
insert into course(id,name, created_date, last_updated_date,is_deleted) values (10004,'Spring MVC in 50 steps',sysdate(),sysdate(),false);
insert into course(id,name, created_date, last_updated_date,is_deleted) values (10005,'Dummy1',sysdate(),sysdate(),false);
insert into course(id,name, created_date, last_updated_date,is_deleted) values (10006,'Dummy2',sysdate(),sysdate(),false);
insert into course(id,name, created_date, last_updated_date,is_deleted) values (10007,'Dummy3',sysdate(),sysdate(),false);
insert into course(id,name, created_date, last_updated_date,is_deleted) values (10008,'Dummy4',sysdate(),sysdate(),false);
insert into course(id,name, created_date, last_updated_date,is_deleted) values (10009,'Dummy5',sysdate(),sysdate(),false);
insert into course(id,name, created_date, last_updated_date,is_deleted) values (10010,'Dummy6',sysdate(),sysdate(),false);

insert into passport(id,number) values (30001,'E123456');
insert into passport(id,number) values (30002,'N123456');
insert into passport(id,number) values (30003,'L123456');


insert into student(id,name,passport_id) values (20001,'Mana',30001);
insert into student(id,name,passport_id) values (20002,'Mike',30002);
insert into student(id,name,passport_id) values (20003,'James',30003);
insert into student(id,name,passport_id) values (20004,'Baby',null);


insert into review(id,rating,description,course_id) values (40001,5,'Great Course',10001);
insert into review(id,rating,description,course_id) values (40002,4,'Wonderful Course',10001);
insert into review(id,rating,description,course_id) values (40003,5,'Awesome Course',10002);

insert into student_course(course_id,student_id) values (10001,20001);
insert into student_course(course_id,student_id) values (10001,20002);
insert into student_course(course_id,student_id) values (10001,20003);
insert into student_course(course_id,student_id) values (10003,20001)