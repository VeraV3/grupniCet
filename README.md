Klijent-server aplikacija
Server i klijenti koji se povezuju na njega. Klijenti medjusobno komuniciraju putem servera. 

Kad je server pokrenut i slusa na svom portu, i kad se potom pokrene neki broj klijenata, oni se unosenjem svog imena konektuju na server.
Od servera svako pri konekciji dobija spisak konektovanih korisnika. Server, osim sto tek konektovanom klijentu vrati ko je povezan na mrezu, vrati i ostalim povezanim
klijentima da je konektovan novi klijent i njegovo ime. 
Klijenti komuniciraju u cetu. Prekidaju komunikaciju unoseci "bye". Tada server ostalima salje informaciju da je klijent napustio cet i uklanja ga iz liste aktivnih korisnika.
