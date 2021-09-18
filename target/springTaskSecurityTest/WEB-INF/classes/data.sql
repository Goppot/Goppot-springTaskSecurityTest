-- Table Users
insert ignore into users set id=1, age=0, email='admin', name='Admin', password='$2y$12$GyJcjdfJMKjf7C7kTXyRouZmI3PgUTqO1b/dwmGGN6CpWy/fBNqJS';

-- Table Rols
insert ignore into roles set id=1, name='ROLE_ADMIN';
insert ignore into roles set id=2, name='ROLE_USER';

-- Table users_roles
insert ignore into users_roles set users_id=1, roles_id=1;

