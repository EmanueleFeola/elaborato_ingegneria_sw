TODO:
- visualizzare le spese nella view rispettiva per un utente
- aggiungere la gestione spese per i manager
- sistemare gestione carrello per gli utenti
- sistemare problemi con la quantit� di alcuni prodotti
- altre cosette...

NOTE:
- db
    - https://www.swtestacademy.com/database-operations-javafx/
- nella classe Credentials il campo user deve essere unique, finch� gestiamo con serializable si pu� evitare di fare il controllo perch� tanto poi useremo un db
- le properties di javafx non sono serializzabili
    - ho aggiunto una classe (copiato da internet) che permette di serializzarle facendo qualche magia
    - va bene finch� non implementiamo il db 
- la classe ShowView serve per visualizzare una view che deve stare in primo piano come Login e ManagerDashboard
- quando l utente modifica il suo profilo bisogna dire al dao di ricaricare la lista di utenti con le robe nuove aggiornate
- la get di un prodotto viene fatta in base al campo name, mentre la get di una persona viene fatta in base al campo user del campo credentials (credentials.getUser())
    - quindi sono stati implementati due getItem diversi 

TODO documentazione finale

- sezione con possibili miglioramenti del progetto