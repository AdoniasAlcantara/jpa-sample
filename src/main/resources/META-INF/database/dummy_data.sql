INSERT INTO Owner(ownerId, name, birth, email) VALUES
	(1, 'Joana da Silva Nunes', '1972-10-25', 'joanasnunes@gmail.com'),
	(2, 'Pedro Augusto Prates', '1988-02-10', NULL),
	(3, 'Marcelino de Oliveira Baptista e Silva', '1969-01-01', 'oliveirabs@yahoo.com.br');
	
INSERT INTO Address(address, number, neighborhood, city, state, ownerId) VALUES
	('Rua das Flores', '123', 'São Cristóvão', 'Pinheirinhos', 'Tocantins', 1),
	('Avenida Nações Unidas', '23', 'Centro', 'Nova Florença', 'Santa Catarina', 2),
	('Praça do Moinho', 's/n', 'Centro', 'Cidade Velha', 'Sergipe', 3);
	
INSERT INTO OwnerPhone(number, ownerId) VALUES
	('1122334455', 1),
	('9911882233', 1),
	('9988776655', 2),
	('6655774488', 3);
		
INSERT INTO Vehicle(vehicleId, make, model, manufactureYear, modelYear, fuelType, price, ownerId) VALUES
	(1, 'Honda', 'Civic', 2012, 2013, 'FLEXIBLE', 71300.00, 1),
	(2, 'Fiat', 'Doblo', 2005, 2006, 'GASOLINE', 19000.00, 1),
	(3, 'Fiat', 'Uno', 2014, 2015, 'FLEXIBLE', 32000.00, 2),
	(4, 'Renault', 'Sandero', 2019, 2020, 'FLEXIBLE', 48890.00, 3),
	(5, 'Volkswagen', 'Fox Xtreme', 2019, 2020, 'FLEXIBLE', 60000.00, 3);
	