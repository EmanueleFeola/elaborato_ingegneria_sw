### DESCRIZIONE FUNZIONAMENTO PROTOTIPO

Durante lo sviluppo del prototipo ci siamo ritrovati ad implementare le varie funzionalità utilizzando nel modo più efficiente i vari pattern studiati.
Partiamo dalla <b>gestione dei dati</b>. 
Per questo aspetto abbiamo utilizzato il <u>Data Access Object pattern</u>, dove da un'interfaccia abbiamo implementato un classe astratta che contiene il codice che fa funzionare gran parte dei metodi, eccetto un paio che vengono implementati in seguito per ogni tipologia di dato che necessità di una gestione dei dati.
Per la <b>gestione delle immagini</b> abbiamo utilizzato il <u>Proxy  pattern</u>, visto a lezione.
Il modello adottato per l'implementazione è quello <b>MVC</b>.
Il <b>Model</b> contiene l'implementazione degli 'attori' principali come: prodotto, carrello, utente, manager, spesa, consegna, ...
Le <b>View</b> sono classi FXML, implementate tramite l'utilizzo di <u>Scene Builder</u> che facilità di molto la costruzione delle interfacce grafiche.
I <b>Controllori</b> gestiscono il funzionamento delle rispettive view, e quindi anche l'interazione con l'utente.

<b>TODO:</b> aggiungere roba...