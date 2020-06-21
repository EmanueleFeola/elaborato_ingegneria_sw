# Progetto di ingegneria del software 2020

> #### Introduzione
>

Si vuole progettare un sistema informatico per gestire il servizio di spesa on-line di un supermercato.

### Documentazione

La documentazione è presente nella cartella *src/documentation*

### Struttura delle directory

- *Package dataManager*: contiene l'implementazione del pattern Data Access Object
- *Package imageProxy*: contiene l'implementazione del pattern Proxy
- *Package model*: contiene l'implementazione delle classi che rappresentano le entità che interagiscono nel sistema
- *Package utils*: contiene l'implementazione di alcune funzioni utilizzate spesso nel codice di tutto il progetto e che è utile tenere in file separati
- *Package view*: contiene file .java e file .fxml per lo sviluppo dell'interfaccia grafica
  - Possiede anche una sottocartella "images", contenente le immagini dei prodotti
- *Cartella diagrams*: composta di due sottocartelle, la prima *img*, contenente le immagini in formato jpg usate per la documentazione, la seconda *src* contenente i file creati coi vari software di sviluppo.

### Note

Abbiamo scelto di inserire 3/4 prodotti per ogni categoria alimentare, tuttavia si possono aggiungere più di prodotti in modo tale da simulare meglio la gestione del supermercato online. 

Ci eravamo prefissati di gestire i dati dell'app tramite un database per avere una fonte di dati centralizzata, ma non siamo riusciti per motivi di tempo

### Licenza 

L'app è rilasciata sotto licenza [MIT](https://opensource.org/licenses/MIT)

### Autori

Studenti Università di Verona:

- Feola Emanuele

- Carra Mattia

- Timofte Robert Octavian

### Software & Linguaggi utilizzati

- Git + GitHub
- Eclipse
- Java
- JavaFX (+ Scene Builder)
- yEd Graph Editor
- Typora
- Trello (per la gestione del progetto)