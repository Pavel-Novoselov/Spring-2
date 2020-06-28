INSERT INTO `users` (`username`, `password`)
VALUE ('admin', '$2a$10$s6HVjC958jdLpJnotp49ceqW8Q/D9f4qts4jGDcS7NuWhVJXy75iy'),
      ('guest', '$2a$10$s6HVjC958jdLpJnotp49ceqW8Q/D9f4qts4jGDcS7NuWhVJXy75iy'),
      ('root', '$2a$10$s6HVjC958jdLpJnotp49ceqW8Q/D9f4qts4jGDcS7NuWhVJXy75iy');
GO

INSERT INTO `roles` (`name`)
VALUE ('ROLE_ADMIN'), ('ROLE_GUEST');
GO

INSERT INTO `users_roles` (`user_id`, `role_id`)
SELECT (SELECT id from `users` WHERE `username` = 'admin'), (SELECT id from `roles` WHERE `name` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id from `users` WHERE `username` = 'guest'), (SELECT id from `roles` WHERE `name` = 'ROLE_GUEST');
GO