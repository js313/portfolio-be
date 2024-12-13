-- Create tables if not present
CREATE TABLE IF NOT EXISTS admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by INT DEFAULT NULL,
    modified_by INT DEFAULT NULL,
    CONSTRAINT fk_admin_created_by FOREIGN KEY (created_by) REFERENCES admin (id),
    CONSTRAINT fk_admin_modified_by FOREIGN KEY (modified_by) REFERENCES admin (id)
);

CREATE TABLE IF NOT EXISTS project_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    display_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS project (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    description TEXT,
    github_link VARCHAR(255) NOT NULL,
    itch_io_link VARCHAR(255),
    project_link VARCHAR(255),
    type_id INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by INT,
    modified_by INT,
    CONSTRAINT fk_project_type FOREIGN KEY (type_id) REFERENCES project_type (id),
    CONSTRAINT fk_project_created_by FOREIGN KEY (created_by) REFERENCES admin (id),
    CONSTRAINT fk_project_modified_by FOREIGN KEY (modified_by) REFERENCES admin (id)
);