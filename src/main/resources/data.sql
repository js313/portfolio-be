-- Insert initial data only if no rows exist
INSERT INTO admin (name, password, created_by, modified_by)
SELECT 'Jeenit', '$2a$12$9wBxfeEmi3d1OaTf3spyR.62mGjybTareZaPm1fG4lMJcsbmDg9I6', NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM admin WHERE name = 'Jeenit');

-- Insert initial project types with display names
INSERT INTO project_type (name, display_name)
SELECT 'web', 'Web Projects'
WHERE NOT EXISTS (SELECT 1 FROM project_type WHERE name = 'web');

INSERT INTO project_type (name, display_name)
SELECT 'game', 'Game Projects'
WHERE NOT EXISTS (SELECT 1 FROM project_type WHERE name = 'game');

INSERT INTO project_type (name, display_name)
SELECT 'creative', 'Creative Projects'
WHERE NOT EXISTS (SELECT 1 FROM project_type WHERE name = 'creative');

INSERT INTO project_type (name, display_name)
SELECT 'misc', 'Miscellaneous Projects'
WHERE NOT EXISTS (SELECT 1 FROM project_type WHERE name = 'misc');

-- Inserting dummy project data

-- Project 1
INSERT INTO project (name, description, image, github_link, itch_io_link, project_link, type_id)
SELECT 'My Web Portfolio', 'A personal web portfolio showcasing my work and projects.', 'portfolio_image.jpg', 'https://github.com/jeenit/portfolio', NULL, 'https://jeenit.com', 1
WHERE NOT EXISTS (SELECT 1 FROM project WHERE name = 'My Web Portfolio');

-- Project 2
INSERT INTO project (name, description, image, github_link, itch_io_link, project_link, type_id)
SELECT 'Space Invaders Game', 'A simple Space Invaders clone built with HTML5 and JavaScript.', 'space_invaders.jpg', 'https://github.com/jeenit/space-invaders', NULL, 'https://jeenit.com/space-invaders', 2
WHERE NOT EXISTS (SELECT 1 FROM project WHERE name = 'Space Invaders Game');

-- Project 3
INSERT INTO project (name, description, image, github_link, itch_io_link, project_link, type_id)
SELECT '3D Art Gallery', 'An interactive 3D art gallery showcasing digital artwork created with WebGL.', 'art_gallery.jpg', 'https://github.com/jeenit/3d-art-gallery', NULL, 'https://jeenit.com/art-gallery', 3
WHERE NOT EXISTS (SELECT 1 FROM project WHERE name = '3D Art Gallery');

-- Project 4
INSERT INTO project (name, description, image, github_link, itch_io_link, project_link, type_id)
SELECT 'Weather App', 'A weather app that provides real-time weather updates using a public API.', 'weather_app.jpg', 'https://github.com/jeenit/weather-app', NULL, 'https://jeenit.com/weather-app', 1
WHERE NOT EXISTS (SELECT 1 FROM project WHERE name = 'Weather App');

-- Project 5
INSERT INTO project (name, description, image, github_link, itch_io_link, project_link, type_id)
SELECT 'AI Chess Game', 'An AI-powered chess game where you can play against the computer.', 'chess_game.jpg', 'https://github.com/jeenit/ai-chess', NULL, 'https://jeenit.com/chess-game', 2
WHERE NOT EXISTS (SELECT 1 FROM project WHERE name = 'AI Chess Game');

-- Project 6
INSERT INTO project (name, description, image, github_link, itch_io_link, project_link, type_id)
SELECT 'Creative Coding Art', 'Generative art created using Processing and p5.js, exploring creative algorithms.', 'creative_art.jpg', 'https://github.com/jeenit/creative-coding', NULL, 'https://jeenit.com/creative-coding', 3
WHERE NOT EXISTS (SELECT 1 FROM project WHERE name = 'Creative Coding Art');

-- Project 7
INSERT INTO project (name, description, image, github_link, itch_io_link, project_link, type_id)
SELECT 'Task Manager App', 'A task manager app to organize and track personal tasks.', 'task_manager.jpg', 'https://github.com/jeenit/task-manager', NULL, 'https://jeenit.com/task-manager', 1
WHERE NOT EXISTS (SELECT 1 FROM project WHERE name = 'Task Manager App');
