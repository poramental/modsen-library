create table "books"(
    book_id UUID not null,
    ISBN varchar(50) not null,
    "name" varchar(50) not null,
    description varchar(120),
    genre varchar(40),
    author varchar(40) not null,
    took_date varchar(80) not null,
    return_date varchar(80) not null
);