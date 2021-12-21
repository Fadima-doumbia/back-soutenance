INSERT INTO roles (name) VALUES
('ROLE_ENTREPRENEUR'),
('ROLE_ADMIN'),
('ROLE_INVESTISSEUR');

INSERT INTO users ( id, email, password, presentation, username)
VALUES (1, 'admin@mail.com', '$2a$10$YCdwtaJloPlmt8Ry4aOkY.dMClxQpYYjHv7AxvVTF5f7Em1GXR.my',
        'je suis l une des jumelles de farima', 'user');
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 2);

INSERT INTO users ( id, email, password, presentation, username)
VALUES (2, 'mari@mail.com', '$2a$10$YCdwtaJloPlmt8Ry4aOkY.dMClxQpYYjHv7AxvVTF5f7Em1GXR.my',
        'je suis mari entrepreneuse', 'mari');
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 1);

INSERT INTO users ( id, email, password, presentation, username)
VALUES (3, 'sylvain@mail.com', '$2a$10$YCdwtaJloPlmt8Ry4aOkY.dMClxQpYYjHv7AxvVTF5f7Em1GXR.my',
        'je suis sylvain investisser', 'sylvain');
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (3, 3);

INSERT INTO `projet` (`id`, `besoin`, `dated`, `description`, `name`, `user_id`)
VALUES (1, 'argent', '2021-12-01 21:54:31', 'projet associatif', 'faso', 2);

INSERT INTO `projet` (`id`, `besoin`, `dated`, `description`, `name`, `user_id`)
VALUES (2, 'marteriel', '2021-12-01 21:54:31', 'kalaban koura', 'bozola', 2);
