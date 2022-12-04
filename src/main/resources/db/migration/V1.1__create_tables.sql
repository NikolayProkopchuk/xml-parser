CREATE TABLE epaper_request (
    id BIGINT PRIMARY KEY,
    file_name TEXT NOT NULL,
    upload_time TIMESTAMP DEFAULT NOW()
);

CREATE TABLE screen_info (
    id BIGINT PRIMARY KEY,
    width INT NOT NULL,
    height INT NOT NULL,
    dpi INT NOT NULL,
    CONSTRAINT FK_screen_info_epaper_request FOREIGN KEY (id) REFERENCES epaper_request(id)
);

CREATE TABLE app_info (
    id BIGINT PRIMARY KEY,
    newspaper_name TEXT NOT NULL,
    CONSTRAINT FK_newspaper_epaper_request FOREIGN KEY (id) REFERENCES epaper_request(id)
);

CREATE SEQUENCE epaper_request_seq START WITH 1;
