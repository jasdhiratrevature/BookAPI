CREATE TABLE book (
	bookid int NOT NULL,
	title varchar NULL,
	author varchar NULL,
	available boolean NULL,
	returndate date NULL,
	CONSTRAINT book_pk PRIMARY KEY (bookid)
);


INSERT INTO book
(bookid, title, author, available, returndate)
VALUES(1, 'The Monk Who Sold His Ferrari', 'Robin Sharma', true, '2022-01-28');
