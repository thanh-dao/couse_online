create table category (
      created_date timestamp(6),
      last_modified_date timestamp(6),
      "created_by_id" uuid,
      id uuid not null,
      "last_modified_by_id" uuid,
      name text,
      is_deleted boolean,
      primary key (id)
);

create table category_course (
     categories_id uuid not null,
     courses_id uuid not null,
     primary key (categories_id, courses_id)
);

create table chapter (
     index integer,
     created_date timestamp(6),
     last_modified_date timestamp(6),
     course_id uuid,
     "created_by_id" uuid,
     id uuid not null,
     "last_modified_by_id" uuid,
     name varchar(255),
     is_deleted boolean,
     primary key (id)
);

create table course (
    sold_count integer,
    tuition_fee integer not null,
    created_date timestamp(6),
    last_modified_date timestamp(6),
    "created_by_id" uuid,
    id uuid not null,
    "last_modified_by_id" uuid,
    teacher_id uuid,
    name text,
    description text,
    image_url text,
    is_deleted boolean,
    primary key (id)
);

create table feedback (
      rating integer,
      created_date timestamp(6),
      last_modified_date timestamp(6),
      course_id uuid,
      "created_by_id" uuid,
      id uuid not null,
      "last_modified_by_id" uuid,
      order_detail_id uuid unique,
      comment text,
      is_deleted boolean,
      primary key (id)
);

create table "order" (
     payment_status boolean,
     created_date timestamp(6),
     last_modified_date timestamp(6),
     "created_by_id" uuid,
     id uuid not null,
     "last_modified_by_id" uuid,
     coupon text,
     payment_id text,
     payment_method text,
     is_deleted boolean,
     primary key (id)
);

create table order_detail (
    price integer,
    created_date timestamp(6),
    last_modified_date timestamp(6),
    course_id uuid,
    "created_by_id" uuid,
    id uuid not null,
    "last_modified_by_id" uuid,
    order_id uuid,
    is_deleted boolean,
    primary key (id)
);

create table refresh_token (
    token text,
    expired_at date,
    created_date timestamp(6),
    last_modified_date timestamp(6),
    "created_by_id" uuid,
    id uuid not null,
    "last_modified_by_id" uuid,
    is_deleted boolean,
    primary key (id)
);

create table "user" (
    created_date timestamp(6),
    last_modified_date timestamp(6),
    "created_by_id" uuid,
    id uuid not null,
    "last_modified_by_id" uuid,
    password varchar(255) NOT NULL ,
    phone varchar(255) UNIQUE NOT NULL ,
    role varchar(255),
    avatar text,
    description text,
    email text UNIQUE NOT NULL,
    full_name text NOT NULL ,
    is_deleted boolean,
    primary key (id)
);

alter table
    if exists category
    add
        constraint FK_Category_User_CreatedBy foreign key ("created_by_id") references "user";

alter table
    if exists category
    add
        constraint FK_Category_User_LastModifiedBy foreign key ("last_modified_by_id") references "user";

alter table
    if exists category_course
    add
        constraint FK_CategoryCourse_Category foreign key (categories_id) references category;

alter table
    if exists category_course
    add
        constraint FK_CategoryCourse_Course foreign key (courses_id) references course;

alter table
    if exists chapter
    add
        constraint FK_Chapter_User_CreatedBy foreign key ("created_by_id") references "user";

alter table
    if exists chapter
    add
        constraint FK_Chapter_User_LastModifiedBy foreign key ("last_modified_by_id") references "user";

alter table
    if exists chapter
    add
        constraint FK_Chapter_Course foreign key (course_id) references course;

alter table
    if exists course
    add
        constraint FK_Course_User_CreatedBy foreign key ("created_by_id") references "user";

alter table
    if exists course
    add
        constraint FK_Course_User_LastModifiedBy foreign key ("last_modified_by_id") references "user";

alter table
    if exists course
    add
        constraint FK_Course_User_Teacher foreign key (teacher_id) references "user";

alter table
    if exists feedback
    add
        constraint FK_Feedback_User_CreateBy foreign key ("created_by_id") references "user";

alter table
    if exists feedback
    add
        constraint FK_Feedback_User_LastModifiedBy foreign key ("last_modified_by_id") references "user";

alter table
    if exists feedback
    add
        constraint FK_Feedback_Course foreign key (course_id) references course;

alter table
    if exists feedback
    add
        constraint Fk_Feedback_OrderDetail foreign key (order_detail_id) references order_detail;


alter table
    if exists "order"
    add
        constraint FK_Order_User_CreatedBy foreign key ("created_by_id") references "user";

alter table
    if exists "order"
    add
        constraint FK_Order_User_LastModifiedBy foreign key ("last_modified_by_id") references "user";


alter table
    if exists order_detail
    add
        constraint FK_OrderDetail_User_CreatedBy foreign key ("created_by_id") references "user";

alter table
    if exists order_detail
    add
        constraint FK_OrderDetail_User_LastModifiedBy foreign key ("last_modified_by_id") references "user";

alter table
    if exists order_detail
    add
        constraint FK_OrderDetail_Course foreign key (course_id) references course;

alter table
    if exists order_detail
    add
        constraint FK_OrderDetail_Order foreign key (order_id) references "order";

alter table
    if exists refresh_token
    add
        constraint FK_RefreshToken_User_CreatedBy foreign key ("created_by_id") references "user";

alter table
    if exists refresh_token
    add
        constraint FK_RefreshToken_User_LastModifiedBy foreign key ("last_modified_by_id") references "user";

alter table
    if exists "user"
    add
        constraint FK_RefreshToken_User_CreatedBy foreign key ("created_by_id") references "user";

alter table
    if exists "user"
    add
        constraint FK_RefreshToken_User_LastModifiedBy foreign key ("last_modified_by_id") references "user"