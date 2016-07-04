DROP TABLE IF EXISTS POST;
create table POST (
        id INT NOT NULL,
        user_id INT default NULL,
        title VARCHAR(200) default NULL,
        body  VARCHAR(2000) default NULL,
        PRIMARY KEY (id)
        );