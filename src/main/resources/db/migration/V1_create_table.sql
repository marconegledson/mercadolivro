create table customer (
    id int auto_increment primary key,
    name varchar(200) not null,
    email varchar(200) not null unique
);
