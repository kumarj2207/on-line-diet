insert into emp.employee(id,name,location,email,mobile) values (1,'Ram','Bangalore','ram@mail.com','9867512345');
insert into calendar_users(id,email,password,first_name,last_name) values (2,'Raj','Chennai','raj@mail.com','7867534521');
insert into calendar_users(id,email,password,first_name,last_name) values (3,'Vinay','Pune','vinay@mail.com','9975287450');

insert into events (id,when,summary,description,owner,attendee) values (100,'2013-10-04 20:30:00','Birthday Party','This is going to be a great birthday',0,1);
insert into events (id,when,summary,description,owner,attendee) values (101,'2013-12-23 13:00:00','Conference Call','Call with the client',2,0);
insert into events (id,when,summary,description,owner,attendee) values (102,'2014-01-23 11:30:00','Lunch','Eating lunch together',1,2);