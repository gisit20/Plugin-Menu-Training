CREATE OR REPLACE FUNCTION spStock_Void_Training (
	__Sadjno VARCHAR
) RETURNS SETOF SPSTATUS AS $BODY$
DECLARE
	_retval SPSTATUS%ROWTYPE;
	_spret SPSTATUS%ROWTYPE;
	
	cSAdjD	REFCURSOR;

	_ItemID		VARCHAR;
	_WhID		VARCHAR;
	_Qty	NUMERIC(19, 4);
	_Unit	VARCHAR;
	_Conv	NUMERIC(19,4);
	_Qty1	NUMERIC(19,4);


BEGIN
	
	OPEN cSAdjD FOR
		SELECT itemid, whid, qty, unit FROM sadjd WHERE sadjno=__SadjNo;
	LOOP
		FETCH cSAdjD INTO _ItemID, _WhID, _Qty, _Unit;
		EXIT WHEN NOT FOUND;

		_Conv:=fItemUnit_Conv(_ItemID, _Unit);
		_Qty1:=_Qty*_Conv;
		
		IF NOT EXISTS
		(SELECT 1 itemid
		FROM stock WHERE itemid =_ItemID AND _WhID=_WhID) THEN 
			INSERT INTO STOCK(itemid, whid, qty) VALUES (_ItemID, _WhID, _Qty1);
		ELSE 
			UPDATE STOCK SET qty=qty+(_Qty1*-1) WHERE itemid=_ItemID AND _WhID=_WhID;
		END IF;

	END LOOP;
	CLOSE cSAdjD;

	DELETE FROM sadjd WHERE sadjno=__Sadjno;
	DELETE FROM sadj WHERE sadjno=__Sadjno;

	_retval:=(0,’OK’);
	RETURN NEXT _retval;
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE;