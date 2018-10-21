-- MySQL Script generated by MySQL Workbench
-- Sat Oct 20 18:54:03 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema spotitube
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `spotitube` ;

-- -----------------------------------------------------
-- Schema spotitube
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spotitube` DEFAULT CHARACTER SET utf8 ;
USE `spotitube` ;

-- -----------------------------------------------------
-- Table `spotitube`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`account` (
  `user` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `full_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spotitube`.`track`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`track` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `performer` VARCHAR(255) NOT NULL,
  `duration` INT NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  `playcount` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spotitube`.`playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`playlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account_user` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`, `account_user`),
  INDEX `fk_playlist_account1_idx` (`account_user` ASC),
  CONSTRAINT `fk_playlist_account`
    FOREIGN KEY (`account_user`)
    REFERENCES `spotitube`.`account` (`user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spotitube`.`token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`token` (
  `token` VARCHAR(255) NOT NULL,
  `account_user` VARCHAR(255) NOT NULL,
  `expiry_date` DATETIME NOT NULL,
  PRIMARY KEY (`token`, `account_user`),
  INDEX `fk_token_account1_idx` (`account_user` ASC),
  CONSTRAINT `fk_token_account`
    FOREIGN KEY (`account_user`)
    REFERENCES `spotitube`.`account` (`user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spotitube`.`tracksInPlaylist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`tracksInPlaylist` (
  `playlist_id` INT NOT NULL,
  `track_id` INT NOT NULL,
  `offline_available` TINYINT NOT NULL,
  PRIMARY KEY (`playlist_id`, `track_id`),
  INDEX `fk_tracksInPlaylist_track1_idx` (`track_id` ASC),
  CONSTRAINT `fk_tracksInPlaylist_playlist`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `spotitube`.`playlist` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tracksInPlaylist_track`
    FOREIGN KEY (`track_id`)
    REFERENCES `spotitube`.`track` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spotitube`.`song`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`song` (
  `track_id` INT NOT NULL,
  `album` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`track_id`),
  CONSTRAINT `fk_song_track`
    FOREIGN KEY (`track_id`)
    REFERENCES `spotitube`.`track` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spotitube`.`video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`video` (
  `track_id` INT NOT NULL,
  `publication_date` DATE NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`track_id`),
  CONSTRAINT `fk_video_track`
    FOREIGN KEY (`track_id`)
    REFERENCES `spotitube`.`track` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `spotitube` ;

-- -----------------------------------------------------
-- Placeholder table for view `spotitube`.`songs_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`songs_view` (`id` INT, `title` INT, `performer` INT, `duration` INT, `url` INT, `playcount` INT, `album` INT);

-- -----------------------------------------------------
-- Placeholder table for view `spotitube`.`videos_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`videos_view` (`id` INT, `title` INT, `performer` INT, `duration` INT, `url` INT, `playcount` INT, `publication_date` INT, `description` INT);

-- -----------------------------------------------------
-- View `spotitube`.`songs_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spotitube`.`songs_view`;
USE `spotitube`;
CREATE  OR REPLACE VIEW `songs_view` AS
SELECT track.id,
track.title,
track.performer,
track.duration,
track.url,
track.playcount,
song.album
FROM song
LEFT JOIN track
ON song.track_id = track.id;

-- -----------------------------------------------------
-- View `spotitube`.`videos_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spotitube`.`videos_view`;
USE `spotitube`;
CREATE  OR REPLACE VIEW `videos_view` AS
SELECT track.id,
track.title,
track.performer,
track.duration,
track.url,
track.playcount,
video.publication_date,
video.description
FROM video
LEFT JOIN track
	ON video.track_id = track.id;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `spotitube`.`account`
-- -----------------------------------------------------
START TRANSACTION;
USE `spotitube`;
INSERT INTO `spotitube`.`account` (`user`, `password`, `full_name`) VALUES ('randy', 'randypassword', 'Randy Grouls');
INSERT INTO `spotitube`.`account` (`user`, `password`, `full_name`) VALUES ('kevin', 'kevinpassword', 'Kevin van Schaijk');
INSERT INTO `spotitube`.`account` (`user`, `password`, `full_name`) VALUES ('jim', 'jimpassword', 'Jim van de Ligt');

COMMIT;


-- -----------------------------------------------------
-- Data for table `spotitube`.`track`
-- -----------------------------------------------------
START TRANSACTION;
USE `spotitube`;
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (1, 'Hellfire', 'Vonikk', 234, 'https://www.youtube.com/watch?v=F893BPo3YnQ', 5);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (2, 'Lost in Time', 'Extra Terra & Urbanstep', 278, 'https://www.youtube.com/watch?v=PlARe5i9rDc', 8);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (3, 'The Great Deception', 'PsoGnar', 328, 'https://www.youtube.com/watch?v=bSxKKYeEoL4', 5);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (4, 'Try This', 'Pegboard Nerds', 225, 'https://www.youtube.com/watch?v=bSxKKYeEoL4', 12);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (5, 'Colorblind', 'Panda Eyes', 289, 'https://www.youtube.com/watch?v=FtT2r8pFUj4', 15);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (6, 'Crystal Cave', 'Panda Eyes', 359, 'https://www.youtube.com/watch?v=4cFD-5w5cyA', 10);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (7, 'Steam Phunk', 'Easy', 231, 'https://www.youtube.com/watch?v=Cm1jassQKrQ', 25);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (8, 'Precious Time', 'Ollie Crowe', 251, 'https://www.youtube.com/watch?v=11Dby5Osk5w', 18);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (9, 'Back Again', 'Archie', 320, 'https://www.youtube.com/watch?v=ynf7CoXssTE', 30);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (10, '#Bounce', 'Worimi', 241, 'https://www.youtube.com/watch?v=M8CGr9nuf2Q', 19);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (11, 'WOHO', 'Blackstripe', 255, 'https://www.youtube.com/watch?v=cAgIU9D6MZg', 35);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (12, 'Bounce That', 'Reece Low & New World Sound', 234, 'https://www.youtube.com/watch?v=jxFdtJs0kmw', 40);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (13, 'Party Rockin', 'DJ THT', 221, 'https://www.youtube.com/watch?v=xd_42mrcA8U', 14);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (14, 'Lost in Space', 'Audio Paradyne', 328, 'https://www.youtube.com/watch?v=MkzLQJ4KW5E', 28);
INSERT INTO `spotitube`.`track` (`id`, `title`, `performer`, `duration`, `url`, `playcount`) VALUES (15, 'The Pressure', 'Stonebank', 249, 'https://www.youtube.com/watch?v=fJzS2wCbEmw', 26);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spotitube`.`playlist`
-- -----------------------------------------------------
START TRANSACTION;
USE `spotitube`;
INSERT INTO `spotitube`.`playlist` (`id`, `account_user`, `name`) VALUES (1, 'randy', 'Dubstep');
INSERT INTO `spotitube`.`playlist` (`id`, `account_user`, `name`) VALUES (2, 'randy', 'Melbourne Bounce');
INSERT INTO `spotitube`.`playlist` (`id`, `account_user`, `name`) VALUES (3, 'jim', 'Drumstep');
INSERT INTO `spotitube`.`playlist` (`id`, `account_user`, `name`) VALUES (4, 'kevin', 'Future House');
INSERT INTO `spotitube`.`playlist` (`id`, `account_user`, `name`) VALUES (5, 'kevin', 'Hard Dance');

COMMIT;


-- -----------------------------------------------------
-- Data for table `spotitube`.`tracksInPlaylist`
-- -----------------------------------------------------
START TRANSACTION;
USE `spotitube`;
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (1, 1, 1);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (1, 2, 0);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (1, 3, 0);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (2, 4, 1);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (2, 5, 1);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (2, 6, 0);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (3, 7, 1);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (3, 8, 1);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (3, 9, 0);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (4, 10, 0);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (4, 11, 1);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (4, 12, 1);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (5, 13, 0);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (5, 14, 1);
INSERT INTO `spotitube`.`tracksInPlaylist` (`playlist_id`, `track_id`, `offline_available`) VALUES (5, 15, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spotitube`.`song`
-- -----------------------------------------------------
START TRANSACTION;
USE `spotitube`;
INSERT INTO `spotitube`.`song` (`track_id`, `album`) VALUES (1, 'Dubstep');
INSERT INTO `spotitube`.`song` (`track_id`, `album`) VALUES (2, 'Dubstep');
INSERT INTO `spotitube`.`song` (`track_id`, `album`) VALUES (3, 'Dubstep');
INSERT INTO `spotitube`.`song` (`track_id`, `album`) VALUES (4, 'Drumstep');
INSERT INTO `spotitube`.`song` (`track_id`, `album`) VALUES (5, 'Drumstep');
INSERT INTO `spotitube`.`song` (`track_id`, `album`) VALUES (6, 'Drumstep');
INSERT INTO `spotitube`.`song` (`track_id`, `album`) VALUES (7, 'Future House');

COMMIT;


-- -----------------------------------------------------
-- Data for table `spotitube`.`video`
-- -----------------------------------------------------
START TRANSACTION;
USE `spotitube`;
INSERT INTO `spotitube`.`video` (`track_id`, `publication_date`, `description`) VALUES (8, '2015-10-29', 'Precious Time by Ollie Crowe');
INSERT INTO `spotitube`.`video` (`track_id`, `publication_date`, `description`) VALUES (9, '2015-07-27', 'Back Again by Archie');
INSERT INTO `spotitube`.`video` (`track_id`, `publication_date`, `description`) VALUES (10, '2014-11-14', '#Bounce by Worimi');
INSERT INTO `spotitube`.`video` (`track_id`, `publication_date`, `description`) VALUES (11, '2014-06-26', 'WOHO By Blackstripe');
INSERT INTO `spotitube`.`video` (`track_id`, `publication_date`, `description`) VALUES (12, '2015-07-19', 'Bounce That by Reece Low & New World Sound');
INSERT INTO `spotitube`.`video` (`track_id`, `publication_date`, `description`) VALUES (13, '2015-02-27', 'Party Rockin by DJ THT');
INSERT INTO `spotitube`.`video` (`track_id`, `publication_date`, `description`) VALUES (14, '2014-12-12', 'Lost in Space by Audio Paradyne');
INSERT INTO `spotitube`.`video` (`track_id`, `publication_date`, `description`) VALUES (15, '2015-05-31', 'The Pressure by Stonebank');

COMMIT;

