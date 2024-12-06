-- Insertar registros en la tabla cita cumpliendo con las reglas de negocio
INSERT INTO cita (consultorio_id, doctor_id, horario_consulta, nombre_paciente) VALUES
(1, 1, '2024-12-06 09:00', 'Paciente A'),
(2, 2, '2024-12-06 10:00', 'Paciente B'),
(3, 3, '2024-12-06 11:00', 'Paciente C'),
(4, 4, '2024-12-06 12:00', 'Paciente D'),
(5, 5, '2024-12-06 13:00', 'Paciente E');

-- Ejemplo de citas adicionales que cumplen con las reglas de negocio
INSERT INTO cita (consultorio_id, doctor_id, horario_consulta, nombre_paciente) VALUES
(1, 1, '2024-12-06 14:00', 'Paciente F'),
(2, 2, '2024-12-06 15:00', 'Paciente G'),
(3, 3, '2024-12-06 16:00', 'Paciente H'),
(4, 4, '2024-12-06 17:00', 'Paciente I'),
(5, 5, '2024-12-06 18:00', 'Paciente J');