# RA1 Accès a Arxius i Gestió d'Interrupcions

### Objectius

* Gestió d'arxius Seqüencials i Aleatoris
* Gestió d'excepcions

### El Registre de Dades

Un registre serà una **unitat de dades lògica**. Per exemple, les dades personals d'una persona com (nom, cognom1, cognom2, adreça, telèfon) conformen un registre de dades.

Els registres poden tenir una **longitud fixa** o una **longitud variable**. Això afectarà al mètode d'accedir a les dades.

### Accès Seqüencial vs. Aleatori

Segons els tipus de registre l'accés a les dades podrà ser seqüencial o aleatori.

L'accés **seqüencial** vol dir que per llegir el registre `n`, s'han hagut de llegir tots els registres anteriors, `1..n-1`.

En canvi, en l'accés **aleatori**, accedir al registre `n`, es pot calcular seguint un càlcul matemàtic.

El càlcul seria: size\_register \* (n-1).
