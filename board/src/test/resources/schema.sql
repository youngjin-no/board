drop table if exists board CASCADE
create table board
(
    board_id           varchar(36) not null,
    created_date       timestamp,
    last_modified_date timestamp,
    contents           clob,
    delete_yn          boolean,
    password           varchar(255),
    subject            varchar(255),
    writer             varchar(20),
    primary key (board_id)
)