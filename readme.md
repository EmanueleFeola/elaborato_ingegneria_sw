TODO:

- carta fedelt�
- per gestire le immagini usare il pattern proxy
	- evitare di caricare la stessa img due volte

NOTE:
- db
  
    - https://www.swtestacademy.com/database-operations-javafx/
    
- nella classe Credentials il campo user deve essere unique, finch� gestiamo con serializable si pu� evitare di fare il controllo perch� tanto poi useremo un db
shopping cart controller (una volta implementato il db)

	// non ci dovrebbero essere due campi separati, ma uno unico (dao) che si riflette in automatico sulla view
	// questo non � possibile perch� il campo products di ShoppinCart e'un arraylist e non un ObservableList
	// ObservableList non si serialliza nemmeno con la classe magica
	// si potrebbe risolvere serializzando uno ad uno i prodotti dentro i cart, per� bisognerebbe riscrivere i 
	// metodi di write e read solo per la classe shoppingCart e non ne vale la pena perch� troppo incasinato
	// questo si puo risolvere con db, dato che in quel caso non serializziamo una minchia
	
- le properties di javafx non sono serializzabili
    - ho aggiunto una classe (copiato da internet) che permette di serializzarle facendo qualche magia
    - va bene finch� non implementiamo il db 

- [NEW] nel DAO delle expenses ho aggiunto un metodo getItemById che mi restituisce una spesa in base all'id univoco. Serve per poter mostrare nel dettaglio i prodotti di ogni spesa.
    Non si pu� filtrare le spese in base all'utente perch� quest'ultimo pu� fare pi� spese
- [NEW] ho impostato il campo user di Credentials a WriteableObject per poterlo visualizzare nella tabella delle spese vista dai manager

TODO documentazione finale

- sezione con possibili miglioramenti del progetto