alter table roles drop index UK_ofx66keruapi6vyqpv6f2or37;
alter table roles drop constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);
alter table users_roles drop constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles (id);
alter table users_roles drop constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users (id);
drop table roles;
drop  table users;
drop  table users_roles;
