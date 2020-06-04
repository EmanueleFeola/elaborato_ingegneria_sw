TODO:
- un manager può poter aggiungere, modificare ed eliminare prodotti
    - view come quella per aggiungere, modificare ed eliminare managers
        - per adesso come path delle immagini mettiamone uno sempre uguale
- popolare le tabelle dei prodotti in modo che l utente possa selezionarli e vederne i particolari
- implementare la gestione del carrello per l utente

NOTE:
- db
    - https://www.swtestacademy.com/database-operations-javafx/
- nella classe Credentials il campo user deve essere unique, finchè gestiamo con serializable si può evitare di fare il controllo perchè tanto poi useremo un db
- le properties di javafx non sono serializzabili
    - ho aggiunto una classe (copiato da internet) che permette di serializzarle facendo qualche magia
    - va bene finchè non implementiamo il db 
- la classe ShowView serve per visualizzare una view che deve stare in primo piano come Login e ManagerDashboard
- quando l utente modifica il suo profilo bisogna dire al dao di ricaricare la lista di utenti con le robe nuove aggiornate
- la get di un prodotto viene fatta in base al campo name, mentre la get di una persona viene fatta in base al campo user del campo credentials (credentials.getUser())
    - quindi sono stati implementati due getItem diversi 
 
TODO documentazione finale
- sezione con possibili miglioramenti del progetto