CREATE SEQUENCE IF NOT EXISTS emp_sequence START WITH 1 INCREMENT BY 1 MINVALUE 1;

create table if not exists emp.employee (
    id bigint default emp_sequence.nextval,
    name varchar(256) not null,
    location varchar(256) not null,
    email varchar(256) not null unique,
    mobile varchar(256) not null
);

create table if not exists emp.credit (
    id bigint AUTO_INCREMENT,
    pan varchar(256) not null,
    score number(3,2) not null,
);

-- insert into emp.credit(id, pan, score) values (1, 'AHPPJ5588C', 5.80);
-- insert into emp.credit(id, pan, score) values (2, 'AHPPJ5588D', 4.80);
-- insert into emp.credit(id, pan, score) values (3, 'AHPPJ5588E', 3.80);
-- insert into emp.credit(id, pan, score) values (4, 'AHPPJ5588F', 5.00);
-- insert into emp.credit(id, pan, score) values (5, 'AHPPJ5588G', 8.80);
