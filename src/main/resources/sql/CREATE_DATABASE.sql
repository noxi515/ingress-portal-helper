CREATE DATABASE `ing_app`
  CHARACTER SET utf8mb4;
USE `ing_app`;

CREATE TABLE `portal` (
  `id`          BINARY(16)   NOT NULL,
  `title`       VARCHAR(255) NOT NULL,
  `image`       VARCHAR(255) NOT NULL,
  `location`    GEOMETRY     NOT NULL,
  `last_update` DATETIME     NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `location_u` (`location`(25)),
  SPATIAL KEY `location` (`location`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8mb4;
