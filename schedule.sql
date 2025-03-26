CREATE TABLE `users`
(
    id Long AUTO_INCREMENT PRIMARY KEY,
    name String NOT NULL,
    pw String NOT NULL,
    date DateTime DEFAULT CURRENT_TIMESTAMP,
    email String NOT NULL UNIQUE
);

CREATE TABLE `scedule`
(
    id Long AUTO_INCREMENT PRIMARY KEY,
    title varchar(200) NOT NULL,
    content varchar(500) NULL,
    create_date DateTime DEFAULT CURRENT_TIMESTAMP,
    update_date DateTime NOT NULL,
    user_id Long NOT NULL,
    FOREIGN KEY(user_id) REFERENCES Users(id)
);
