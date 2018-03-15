insert into Book values ('Gra o Tron', 'George R. R. Martin', '1230984567', false)
insert into Book values ('Achaja', 'Andrzej Ziemiański', '0009998881', false)
insert into Book values ('Pomnik Cesarzowej Achai', 'Andrzej Ziemiański', '9990000111', false)
insert into Book values ('Siewca Wiatru', 'Maja Lidia Kossakowska', '0987650987', false)
insert into Book values ('Kroniki Jakuba Wędrowycza', 'Andrzej Pilipiuk', '8295728192', false)

insert into Reader values ('be336259-f9bd-42cf-8c69-39d076f57bef', 'Jan Kowalski', 0)
insert into Reader values ('6e715385-c558-404c-b3f3-ca492d54c7f7', 'Stanisław Janowski', 0)
insert into Reader values ('66054f98-e9c6-4124-979f-72ff1d8078d1', 'Roman Stanisławski', 0)

insert into Rent values (1, '0009998881', 'be336259-f9bd-42cf-8c69-39d076f57bef', to_date('2018-03-11','YYYY-MM-DD'), true)
insert into Rent values (2, '8295728192', '6e715385-c558-404c-b3f3-ca492d54c7f7', to_date('2018-02-09','YYYY-MM-DD'), false)
