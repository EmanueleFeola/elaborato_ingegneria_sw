TODO:
- db
    - https://www.swtestacademy.com/database-operations-javafx/
- nella classe Credentials il campo user deve essere unique, finch� gestiamo con serializable si pu� evitare di fare il controllo perch� tanto poi useremo un db
- le properties di javafx non sono serializzabili
    - ho aggiunto una classe (copiato da internet) che permette di serializzarle facendo qualche magia
    - va bene finch� non implementiamo il db 
- la classe ShowView serve per visualizzare una view che deve stare in primo piano come Login e ManagerDashboard