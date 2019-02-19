CREATE OR REPLACE FUNCTION ДАТА_ТЕМА_АВТОЗАП_ФУНК() RETURNS TRIGGER AS 
$ДАТА_ТЕМА_АВТОЗАП_ФУНК_$
	BEGIN
	
		IF NEW.ТЕМА IS NULL THEN
			NEW.ТЕМА = '(Без темы)';
		END IF;
		
		NEW.ДАТА = CURRENT_DATE;
		RETURN NEW;
	END;
$ДАТА_ТЕМА_АВТОЗАП_ФУНК_$ LANGUAGE plpgsql;

CREATE TRIGGER ДНЕВНИК_АВТОЗАП
	BEFORE INSERT OR UPDATE ON ДНЕВНИК
	FOR EACH ROW EXECUTE PROCEDURE ДАТА_ТЕМА_АВТОЗАП_ФУНК();
	
CREATE TRIGGER СООБЩЕНИЕ_АВТОЗАП
	BEFORE INSERT OR UPDATE ON СООБЩЕНИЕ
	FOR EACH ROW EXECUTE PROCEDURE ДАТА_ТЕМА_АВТОЗАП_ФУНК();

	


CREATE OR REPLACE FUNCTION МЕСТО_АВТОЗАП_ФУНК() RETURNS TRIGGER AS 
$МЕСТО_АВТОЗАП_ФУНК_$
	BEGIN
	
		IF NEW.МЕСТО IS NULL THEN
			NEW.МЕСТО = 'Сделать самому';
		END IF;
		
		RETURN NEW;
	END;
$МЕСТО_АВТОЗАП_ФУНК_$ LANGUAGE plpgsql;

CREATE TRIGGER БЛЮДО_АВТОЗАП
	BEFORE INSERT OR UPDATE ON БЛЮДО
	FOR EACH ROW EXECUTE PROCEDURE МЕСТО_АВТОЗАП_ФУНК();

DROP TRIGGER БЛЮДО_АВТОЗАП ON БЛЮДО;
	
	
	


CREATE OR REPLACE FUNCTION ВРЕМЯ_РЕГИСТРАЦИИ() RETURNS TRIGGER AS $ВРЕМЯ_РЕГИСТРАЦИИ_$
    BEGIN
		IF (TG_OP = 'INSERT') THEN
            INSERT INTO ИНФО(ИД_ЛИЧНОСТЬ, ДАТА_РЕГИСТР) VALUES(NEW.ИД_ЛИЧНОСТЬ, CURRENT_DATE);
            RETURN NEW;
        END IF;
        RETURN NULL;
    END;
$ВРЕМЯ_РЕГИСТРАЦИИ_$ LANGUAGE plpgsql;

CREATE TRIGGER ВРЕМЯ_РЕГИСТРАЦИИ
	AFTER INSERT ON ЛИЧНОСТЬ
    FOR EACH ROW EXECUTE PROCEDURE ВРЕМЯ_РЕГИСТРАЦИИ();






CREATE OR REPLACE FUNCTION ЖАЛОБА_ВРЕМЯ() RETURNS TRIGGER AS
$ЖАЛОБА_ВРЕМЯ_$
BEGIN

	IF NEW.ВРЕМЯ IS NULL THEN
		NEW.ВРЕМЯ = CURRENT_TIMESTAMP;
	END IF;

	RETURN NEW;
END;
$ЖАЛОБА_ВРЕМЯ_$ LANGUAGE plpgsql;

CREATE TRIGGER ЖАЛОБА_ВРЕМЯ__
	BEFORE INSERT OR UPDATE ON ЖАЛОБА
	FOR EACH ROW EXECUTE PROCEDURE ЖАЛОБА_ВРЕМЯ();



