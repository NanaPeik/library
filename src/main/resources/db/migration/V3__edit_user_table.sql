alter table users.users add column if not exists is_admin bool default false;
insert into users.users(user_name,password,email,is_admin)
values('Admin','Admin','Admin@gmail.com',true)