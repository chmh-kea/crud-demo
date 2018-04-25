CREATE DEFINER=`root`@`localhost` PROCEDURE `transactionexample`()
BEGIN
DECLARE `_rollback` BOOL DEFAULT 0;
DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET `_rollback` = 1;
START TRANSACTION;
#INSERT INTO someobjects (someString,someInt) VALUES ('?', 123);
#INSERT INTO someobjects (someStriang) VALUES (123);
#DELETE FROM someobjects WHERE id=18;
#DELETE FROM someobjects WHERE id=15;
UPDATE someobjects SET someString='Transactonexample4' WHERE id=10;
UPDATE someobjects SET someInt='Transactonexample4' WHERE id=13;
SELECT ROW_COUNT() INTO @rows_affected;
IF `_rollback` THEN
    ROLLBACK;
ELSE
    COMMIT;
END IF;
#IF (rows_affected = 2) THEN
	#COMMIT;
#ELSE
	#ROLLBACK;
#END IF;
#ROLLBACK;
SELECT * FROM someobjects;
END