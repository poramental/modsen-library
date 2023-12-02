create table "books"(
    book_id UUID not null,
    ISBN varchar(50) not null,
    "name" varchar(50) not null,
    description varchar(120),
    genre varchar(40),
    author varchar(40) not null
);