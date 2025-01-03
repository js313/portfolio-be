-- Create tables if not present
CREATE TABLE IF NOT EXISTS admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    created_by INT DEFAULT NULL,
    modified_by INT DEFAULT NULL,
    CONSTRAINT fk_admin_created_by FOREIGN KEY (created_by) REFERENCES admin (id),
    CONSTRAINT fk_admin_modified_by FOREIGN KEY (modified_by) REFERENCES admin (id)
);

CREATE TABLE IF NOT EXISTS project_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    display_name VARCHAR(255) NOT NULL,
    priority INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by INT DEFAULT NULL,
    modified_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    modified_by INT DEFAULT NULL,
    CONSTRAINT fk_project_type_created_by FOREIGN KEY (created_by) REFERENCES admin(id),
    CONSTRAINT fk_project_type_modified_by FOREIGN KEY (modified_by) REFERENCES admin(id)
);


CREATE TABLE IF NOT EXISTS project (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    description VARCHAR(255),
    github_link VARCHAR(255) NOT NULL,
    itch_io_link VARCHAR(255),
    project_link VARCHAR(255),
    type_id INT NOT NULL,
    highlight BOOLEAN NOT NULL DEFAULT FALSE,
    supports_rendering BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    created_by INT DEFAULT NULL,
    modified_by INT DEFAULT NULL,
    CONSTRAINT fk_project_type FOREIGN KEY (type_id) REFERENCES project_type (id),
    CONSTRAINT fk_project_created_by FOREIGN KEY (created_by) REFERENCES admin (id),
    CONSTRAINT fk_project_modified_by FOREIGN KEY (modified_by) REFERENCES admin (id)
);

CREATE TABLE IF NOT EXISTS contact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    message TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by INT DEFAULT NULL,
    modified_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    modified_by INT DEFAULT NULL,
    CONSTRAINT fk_contact_created_by FOREIGN KEY (created_by) REFERENCES admin(id),
    CONSTRAINT fk_contact_modified_by FOREIGN KEY (modified_by) REFERENCES admin(id)
);