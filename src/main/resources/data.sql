insert into Book values ('Gra o Tron', 'George R. R. Martin', 1, true)
insert into Book values ('Achaja', 'Andrzej Ziemiański', 2, true)
insert into Book values ('Pomnik Cesarzowej Achai', 'Andrzej Ziemiański', 3, false)
insert into Book values ('Siewca Wiatru', 'Maja Lidia Kossakowska', 4, false)
insert into Book values ('Kroniki Jakuba Wędrowycza', 'Andrzej Pilipiuk', 5, false)

insert into Reader values (1, 'Jan Kowalski', 0)
insert into Reader values (2, 'Stanisław Janowski', 0)
insert into Reader values (3, 'Roman Stanisławski', 2)

insert into Rent values (1, 1, 3, to_date('2018-03-11','YYYY-MM-DD'), true)
insert into Rent values (2, 2, 3, to_date('2018-02-09','YYYY-MM-DD'), false)
