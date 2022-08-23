do $$
declare a character;
begin
for i in 1..10000000 loop
		a := cast(i as character);
insert into community_user(id,username,account,password, create_time , first_name,last_name,email)
values (i, 'Asuna' || a, 'test' || a, md5('123456'), '2022-11-21 22:22:22', 'zhao', 'yifan', a || '@gmail.com');
end loop;
end; $$