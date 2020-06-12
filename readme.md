TODO:
- [DONE] rinominare le view
- [DONE] quando manager va nella view prodotti ci deve essere il campo path dell img
- [DONE] quando un manager seleziona un img per prenderne il path deve poter scegliere solo immagini, non altri tipi di file
- [DONE] quando un manager seleziona un img per prenderne il path non deve poter selezionare una img esterna alla cartella settata come base, se no puo selezionare un img tipo sul desktop che non sarà disponibile sul pc di qualcun altro (sia di un manager, sia di un user)
- se un prodotto non è available non può essere aggiunto nel carrello dall'utente, il fatto che sia possibile visualizzarlo e vederne i dettagli è opinabile, secondo me è giusto cosi
- quando c'è da settare l'id univoco di un oggetto, invece di usare random, mettere un id progressivo (1....N)
    - si potrebbe anche prendere come id l'indice dell'oggetto nel'array in cui è memorizzato
        - tanto quando si aggiunge un nuovo ogg ad un array lo si fa in push, quindi quando viene serializzato si mette in coda al file
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

- [NEW] nel DAO delle expenses ho aggiunto un metodo getItemById che mi restituisce una spesa in base all'id univoco. Serve per poter mostrare nel dettaglio i prodotti di ogni spesa.
    Non si può filtrare le spese in base all'utente perchè quest'ultimo può fare più spese
- [NEW] ho impostato il campo user di Credentials a WriteableObject per poterlo visualizzare nella tabella delle spese vista dai manager

TODO documentazione finale

- spiegazione di come abbiamo collaborato con git
    - tanti e piccoli commmit + push, aggiornamento dei branch & merge
- sezione con possibili miglioramenti del progetto