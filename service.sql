create table users
(
    id             bigserial primary key,
    login          varchar(128) not null,
    pwd            varchar(128) not null,
    role           varchar(128) not null,
    profit_percent int          not null
);

create table clients
(
    id              bigserial primary key,
    name            varchar(128) not null,
    phone           varchar(16)  not null,
    additional_info varchar(256)
);

create table orders
(
    id              bigserial primary key,
    created         timestamp    not null,
    type            varchar(128) not null,
    model           varchar(128) not null,
    serial          varchar(128),
    problem         varchar(256) not null,
    set             varchar(128),
    additional_info varchar(256),
    status          varchar(128) not null,
    client_id       bigint references clients

);

create table items
(
    id       bigserial primary key,
    order_id bigint references orders,
    user_id  bigint references users,
    name     varchar(128) not null,
    quantity int          not null,
    cost     bigint       not null,
    price    bigint       not null
);

create table transactions
(
    id          bigserial primary key,
    time        timestamp not null,
    amount      bigint    not null,
    total_cash  bigint    not null,
    description varchar(128)

);

insert into users (login, pwd, role, profit_percent)
values ('manager', '123', 'MANAGER', 0),
       ('reception', '123', 'RECEPTION', 0),
       ('master', '123', 'MASTER', 50);

insert into clients (name, phone, additional_info)
values ('client1', '+79123456789', ''),
       ('client2', '+79123456789', 'компания работает по безналу'),
       ('client3', '+79123456789', '');

insert into orders (created, type, model, serial, problem, set, additional_info, status, client_id)
values ('2023-10-14T18:07', 'телевизор', 'Samsung UE50l7500', '', 'не включается', 'пульт', '', 'DIAGNOSTIC', 1),
       ('2023-10-20T12:35', 'смартфон', 'Mi 10T', '', 'разбит экран', '', '', 'WAITING_PARTS', 3);

insert into items (order_id, user_id, name, quantity, cost, price)
values (2, 3, 'замена дисплея смартфона', 1, 0, 1500),
       (2, 3, 'дисплей Mi 10T orig', 1, 3500, 4500);

insert into transactions (time, amount, total_cash, description)
values ('2023-10-14T18:07', 1000, 5000, 'приход'),
       ('2023-11-05T11:45', -3500, 1500, 'покупка дисплея Mi 10T');

alter table users
    add constraint uc_login unique(login);