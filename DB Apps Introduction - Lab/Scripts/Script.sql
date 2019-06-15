USE minions_db;

SELECT * FROM minions
WHERE id IN (1, 2, 3, 4, 5, 6);

use minions_db;
UPDATE minions
SET age := age + 1, name := UPPER(name)
WHERE id IN (1, 2, 3, 4, 5, 6);

SELECT * FROM TABLE WHERE COL in (SELECT * FROM TABLE(split('1,1')))

CREATE PROCEDURE usp_get_older (minion_id INT)
BEGIN
	UPDATE minions
	SET age := age + 1
	WHERE id = minion_id
END


