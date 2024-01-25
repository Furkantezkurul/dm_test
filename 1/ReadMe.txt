Aufgabe 1.6:

Problem: Dateien sind nicht in target/messages gelandet
Lösung: uri war auf "file:/src/data?noop=true" gesetzt. Als man das mavenProject aber gebaut hat, wurde ein extra folder hinzugefügt (in meinem Fall mit dem Namen "1") daher musstd die uri: zu "file:1/src/data?noop=true" geändert werden

Aufgabe 2:
Problem: uk erschien nicht im Ordner
Lösung: Hatte wieder vergessen 1/ vor /src zu setzen

Aufgabe 3:

Problem: Wenn man die Komponente verändert, sodass sie via JSON Path funktioniert, können die anderen Messages nicht mehr gelesen werden
Lösung: Entweder die anderen Messages löschen oder die Komponente so umbauen, dass JSON UND XML aktieptier (check ob Json oder XML). Ich habe mich für die letzte Option entschieden

+ camel-json dependencies mussten zur POM hinzugefügt werden


Aufgabe 4:

+ HTTP dependecy musste in POM hinzugefügt werden

Problem: .to() resultierte immer in eine leere Datei
Lösung: getContext().setStreamCaching(true); - wahrscheinlich, weil ich die Antwort einmal logge und dannach der Stream nicht mehr gecached ist.






Camel Java Router Project
=========================

To build this project use

    mvn install

To run this project from within Maven use

    mvn exec:java

For more help see the Apache Camel documentation

    http://camel.apache.org/

