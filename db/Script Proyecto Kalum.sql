CREATE DATABASE kalum2021_prod;

USE kalum2021_prod;


-- Creación de usuario
CREATE USER 'kalum-java'@'localhost' IDENTIFIED BY 'Inicio.2021';

-- Asignación de permisos
GRANT ALL PRIVILEGES ON *.* TO 'kalum-java'@'localhost';


INSERT INTO role_app (nombre) VALUES ("ROLE_ADMIN");

SELECT * FROM usuario_app;

SELECT * FROM usuario_role;

SELECT * FROM usuario_app;

INSERT INTO usuario_app (apellidos, direccion, email, enabled, nombres, password, telefono, username) 
VALUES ('Pérez', 'Guatemala', 'jorgeluisperez@kinal.edu.gt', true, 'Jorge Luis', '$2a$10$OhGDanndZgmHPUO9tuwM9uoPAVE0M5n4.lxphEeBik93YVYoAc.YK', '33466082', 'jperez');

INSERT INTO usuario_app (apellidos, direccion, email, enabled, nombres, password, telefono, username) 
VALUES ('Pérez', 'Guatemala', 'jorgeluisperez@kinal.edu.gt', true, 'Jorge Luis', '$2a$10$qQYR.phN46HfCEjTN25cPeqobPUeKvRo3SKRrEuKAnPnuSEeL18Ky', '33466082', 'jperez');
																				 
UPDATE `kalum2021_prod`.`usuario_app` SET `password` = '$2a$10$KVbchmfA6kLySBg7E4hmruNlzXIEGQUiZrFYnjae00Y2AhguDZcc.' WHERE (`id` = '1');

-- $2a$10$xJCLxcRl.aZJeXPTbiRZgOpv06Lk5bnaZ8xLy94aAoN9kMkLur/fS

SELECT * FROM role_app;
SELECT * FROM usuario_app;
-- INSERT INTO usuario_role (usuario_id, role_id) VALUES (1,1);
SELECT * FROM usuario_role;



INSERT INTO carrera_tecnica VALUES (uuid(), "Desarrollo de aplicaciones basado en microservicios con JavaEE");
INSERT INTO carrera_tecnica VALUES (uuid(), "Publicidad con especialidad en diseño gráfico");
INSERT INTO carrera_tecnica VALUES (uuid(), "Desarrollo BackEnd con Java");
SELECT * FROM carrera_tecnica; -- e5b639d0-4fa6-11ec-b4e0-9828a626f71d

INSERT INTO horario (horario_id, hoario_final, horario_inicio) VALUES(uuid(), '12:30', '08:00');
INSERT INTO horario (horario_id, hoario_final, horario_inicio) VALUES(uuid(), '17:30', '13:00');
INSERT INTO horario (horario_id, hoario_final, horario_inicio) VALUES(uuid(), '21:00', '19:00');
select * from horario; -- c971f9d5-4fa6-11ec-b4e0-9828a626f71d

INSERT INTO instructor VALUES (uuid(), "Tumax", "Instructor de programación y redes", "Guatemala", "1","default.png","Edwin","50289654785");
INSERT INTO instructor VALUES (uuid(), "Alvaro Alvarez", "Instructor de diseño gráfico", "Guatemala", "1","default.png","Irma Ortencia","50257896454");
SELECT * FROM instructor; -- d91f4085-4fa6-11ec-b4e0-9828a626f71d

INSERT INTO salon VALUES (uuid(), 20, "Salón de programación", "C-28");
INSERT INTO salon VALUES (uuid(), 20, "Salón de diseño gráfico", "C-27");
SELECT * FROM salon; -- dffe3ee4-4fa6-11ec-b4e0-9828a626f71d

INSERT INTO clase (clase_id, ciclo, cupo_maximo, cupo_minimo, descripcion, codigo_carrera, horario_id, instructor_id, salon_id)
VALUES (
uuid(),
2021,
20,
5,
'Jornada Matutina - Desarrollo de software con Java',
'e5b639d0-4fa6-11ec-b4e0-9828a626f71d',
'c971f9d5-4fa6-11ec-b4e0-9828a626f71d',
'd91f4085-4fa6-11ec-b4e0-9828a626f71d',
'dffe3ee4-4fa6-11ec-b4e0-9828a626f71d'
);

INSERT INTO clase (clase_id, ciclo, cupo_maximo, cupo_minimo, descripcion, codigo_carrera, horario_id, instructor_id, salon_id)
VALUES (
uuid(),
2021,
20,
5,
'Jornada Matutina - Diseño gráfico',
'e5a8af29-4fa6-11ec-b4e0-9828a626f71d',
'c965e905-4fa6-11ec-b4e0-9828a626f71d',
'd9410e40-4fa6-11ec-b4e0-9828a626f71d',
'e00940ed-4fa6-11ec-b4e0-9828a626f71d'
);



select * from clase;


INSERT INTO `kalum2021_prod`.`alumno` (`carne`, `apellidos`, `email`, `no_expediente`, `nombres`) VALUES ('2021001', 'Pérez', 'jorgeluisperez@kinal.edu.gt', 'E2021-001', 'Jorge');
INSERT INTO `kalum2021_prod`.`alumno` (`carne`, `apellidos`, `email`, `no_expediente`, `nombres`) VALUES ('2021002', 'Can', 'antonio@gmail.com', 'E2021-002', 'Antonio');
INSERT INTO `kalum2021_prod`.`alumno` (`carne`, `apellidos`, `email`, `no_expediente`, `nombres`) VALUES ('2021003', 'Masilla', 'masilla@gmail.com', 'E2021-003', 'Erika');
INSERT INTO `kalum2021_prod`.`alumno` (`carne`, `apellidos`, `email`, `no_expediente`, `nombres`) VALUES ('2021004', 'Hernandez', 'hcarol@gmail.com', 'E2021-004', 'Carolina');


SELECT * FROM alumno;
-- kalum-java

SELECT * FROM usuario_app;
SELECT * FROM alumno;

SELECT * FROM asignacion_alumno;

select * from clase;

INSERT INTO asignacion_alumno VALUES (UUID(), "2021-12-04", '2021001', '4cdde9d8-4fa7-11ec-b4e0-9828a626f71d')