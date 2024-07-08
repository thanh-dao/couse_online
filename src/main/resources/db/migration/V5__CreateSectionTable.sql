create table section (
     id uuid not null,
     created_date timestamp(6),
     last_modified_date timestamp(6),
     attached_file varchar(255),
     description varchar(255),
     name varchar(255) not null,
     video varchar(255),
     "created_by_id" uuid,
     "last_modified_by_id" uuid,
     chapter_id uuid,
     is_deleted boolean,
     primary key (id)
);
alter table if exists section
    add constraint FK_Section_User_CreatedBy
    foreign key ("created_by_id")
    references "user";
alter table if exists section
    add constraint FK_Section_User_LastModifiedBy
    foreign key ("last_modified_by_id")
    references "user";
alter table if exists section
    add constraint FK_Section_Chapter
    foreign key (chapter_id)
    references chapter;