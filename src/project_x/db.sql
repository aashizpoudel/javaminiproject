pragma foreign_key=ON;
CREATE  TABLE  IF NOT exists Artists (
	ArtistId integer PRIMARY KEY AUTOINCREMENT,
	name text
);

CREATE TABLE IF NOT exists ArtistDetails (
	ArtistId integer,
	YearsActive integer,
	Website text,
	genre text,
	FOREIGN KEY (ArtistId) references Artist(ArtistId)
);

CREATE TABLE IF NOT exists Albums (
	AlbumId integer PRIMARY KEY AUTOINCREMENT,
	ArtistId integer,
	AlbumName text,
	AlbumYear integer,
	FOREIGN KEY (ArtistId) references Artist(ArtistId)
);

CREATE TABLE IF NOT exists Songs (
	SongId integer PRIMARY KEY AUTOINCREMENT,
	AlbumId integer,
	Song text,
	link text,
	FOREIGN KEY (AlbumId) references Albums(AlbumId)
);

CREATE TABLE IF NOT exists Users(
UserId integer PRIMARY Key AUTOINCREMENT,
Name text,
username text unique,
password text
);
