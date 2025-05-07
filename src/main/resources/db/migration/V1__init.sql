create table "books" (
    "id" UUID default RANDOM_UUID() primary key,
    "title" varchar(255),
    "author" varchar(255),
    "publication_Year" int);
