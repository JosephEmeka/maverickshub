truncate table users cascade;
truncate table media cascade;

insert into users(id, email, password, time_created) values
(200, 'johndoe@gmail.com', 'password', '2024-06-04T15:03:03.792009700' ),
(201, 'jamesdoe@gmail.com', 'password', '2024-06-04T15:03:03.792009700' ),
(202, 'janedoe@gmail.com', 'password', '2024-06-04T15:03:03.792009700' ),
(203, 'johnnydoe@gmail.com', 'password', '2024-06-04T15:03:03.792009700');

insert into media(id, category, description, url, time_created, uploader_id) values
(100, 'ACTION', 'media 1', 'https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700', 200),
(101, 'SCI_FI', 'media 2', 'https://www.cloudinary.com/media2', '2024-06-04T15:03:03.792009700', 200),
(102, 'ROMANCE', 'media 3', 'https://www.cloudinary.com/media3', '2024-06-04T15:03:03.792009700', 201),
(103, 'FAMILY', 'media 4', 'https://www.cloudinary.com/media4', '2024-06-04T15:03:03.792009700', 200),
(104, 'COMEDY', 'media 5', 'https://www.cloudinary.com/media5', '2024-06-04T15:03:03.792009700', 201);