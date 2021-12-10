INSERT INTO roles (name) VALUES
('ROLE_ENTREPRENEUR'),
('ROLE_ADMIN'),
('ROLE_INVESTISSEUR');

INSERT INTO users ( id, email, password, presentation, username)
VALUES (1, 'admin@mail.com', '$2a$10$YCdwtaJloPlmt8Ry4aOkY.dMClxQpYYjHv7AxvVTF5f7Em1GXR.my',
        'je suis l une des jumelles de farima', 'user');
-- INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES ('1', '2');


/*INSERT INTO users ( id, email, password, presentation, username, roles) VALUES (1, 'admin@mail.com', '$2a$10$WV0jq6Bkl0LLIafD0de0NuSDooBWLCXzIT1tf/VW9BtaOLyOADy1e',
'je suis l'une des jumelles de farima', 'user', 'ROLE_ADMIN');*/


/*INSERT INTO users ( id, email, password, username) VALUES (1, 'admin@mail.com',
'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjI4NjA3NzI4LCJleHAiOjE2Mjg2OTQxMjh9.TcqtvZ7B6HlTh_N8YeUlsFzlBIAVRb0462MUgA-O57NxMvqxS-KbMqfCpRYK56keOjVEIYTXuev84sSiBqzvyg'
, 'user')*/

select 1;