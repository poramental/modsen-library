create table "books"(
    book_id UUID not null,
    ISBN varchar(50),
    "name" varchar(50),
    description varchar(120),
    genre varchar(40),
    author varchar(40)
);