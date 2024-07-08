alter  table  "user"
    drop column role;
CREATE TABLE role(
    name text,
    is_deleted boolean,
    created_date timestamp(6),
    last_modified_date timestamp(6),
    "created_by_id" uuid,
    "last_modified_by_id" uuid,
    id uuid not null primary key
);
create table user_role(
    users_id uuid not null,
    roles_id uuid not null
--     id uuid not null,

);
alter table
    IF EXISTS user_role
    ADD
        constraint FK_UserRole_User foreign key ("users_id") references "user",
    ADD
        constraint FK_UserRole_Role foreign key (roles_id) references "role";
alter table
    IF EXISTS role
    ADD
        CONSTRAINT FK_Role_User_CreatedBy FOREIGN KEY (created_by_id) references "user",
    ADD
        CONSTRAINT FK_Role_User_LastModifiedBy FOREIGN KEY (last_modified_by_id) references "user";