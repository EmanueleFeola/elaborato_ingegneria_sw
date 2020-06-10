TODO:
Prima di andare avanti con nuovo codice sistemiamo il codice scritto fin ora

1- quando l utente modifica il suo profilo bisogna dire al dao di ricaricare la lista di utenti con le robe nuove aggiornate

2- una volta fatto il processo Deliver che il carrello diventa effettivamente una spesa bisogna fare in modo che la view si aggiorni perchè il carrello non c'è piu

Dopo aver fatto i primi 2 punti...	    
- nella view delle spese per un utente, bisogna poter visualizzare in dettaglio i prodotti di ogni spesa
- aggiungere la gestione spese per i manager
- carta fedeltà
- per gestire le immagini usare il pattern proxy
	- evitare di caricare la stessa img due volte

NOTE:
- db
    
    - https://www.swtestacademy.com/database-operations-javafx/
- nella classe Credentials il campo user deve essere unique, finchè gestiamo con serializable si può evitare di fare il controllo perchè tanto poi useremo un db
shopping cart controller (una volta implementato il db)

	// non ci dovrebbero essere due campi separati, ma uno unico (dao) che si riflette in automatico sulla view
	// questo non è possibile perchè il campo products di ShoppinCart e'un arraylist e non un ObservableList
	// ObservableList non si serialliza nemmeno con la classe magica
	// si potrebbe risolvere serializzando uno ad uno i prodotti dentro i cart, però bisognerebbe riscrivere i 
	// metodi di write e read solo per la classe shoppingCart e non ne vale la pena perchè troppo incasinato
	// questo si puo risolvere con db, dato che in quel caso non serializziamo una minchia
- le properties di javafx non sono serializzabili
    - ho aggiunto una classe (copiato da internet) che permette di serializzarle facendo qualche magia
    - va bene finchè non implementiamo il db 

TODO documentazione finale

- sezione con possibili miglioramenti del progetto