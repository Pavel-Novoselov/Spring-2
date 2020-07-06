create table if not exists roles (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB;
GO
create table if not exists users
 (
 id bigint not null auto_increment,
 age integer,
 email varchar(255),
 username varchar(32) not null,
 password varchar(128) not null,
 primary key (id)
 )
 engine=InnoDB;
GO
create table if not exists users_roles (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id)) engine=InnoDB;
GO
alter table roles add constraint UK_ofx66keruapi6vyqpv6f2or3711 unique (name);
GO
alter table users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy11 foreign key (role_id) references roles (id);
GO
alter table users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa11 foreign key (user_id) references users (id);
GO

create table if not exists products
 (
 id bigint not null auto_increment,
 title varchar(255),
 description varchar(100),
 price decimal(19,2) not null,
 category_id bigint,
 primary key (id)
 )
 engine=InnoDB;
GO

create table if not exists categories
 (
 id bigint not null auto_increment,
 title varchar(255),
 description varchar(100),
 primary key (id)
 )
 engine=InnoDB;
GO

alter table products add constraint FK_category11 foreign key (category_id) references categories (id);
GO





