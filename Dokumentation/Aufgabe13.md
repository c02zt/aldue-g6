# [A13] MergeSort

## Aufgabe

Im Package *A13_MergeSort* finden Sie die Klasse *Person*. Erweitern Sie die Klasse um die Funktion

```java
public int compareTo(Person p)
```

Die Funktion soll anhand des Nachnamens, bei gleichen Nachnamen anhand des Vornamens, eine alphabetische Ordnung der beiden Objekte ermöglichen. Ähnlich der *compareTo()*-Funktion der String-Klasse soll eine negative Zahl retourniert werden, wenn die übergebene Person (*Nachname+Vorname*) später im Alphabet kommt, eine positive Zahl, wenn die übergebene Person früher im Alphabet kommt und Null, wenn Nachname und Vorname identisch sind. Aufbauend auf dieser Klasse implementieren Sie im Package die Klasse *MergeSort* die Methode

* `public void sort(Person[] personen)`

Im Package finden Sie auch passende JUnit-Tests. Diese leiten sich von der Klasse *PersonenSortTest* ab. Für die ausgeführten Tests müssen Sie also in dieser Klasse nachsehen.


## Lösung

Die Implementierung eines *Merge-Sort-Algorithmus* besteht aus zwei Teilen. Wie es schon aus dem Namen ersichtlich ist, sind diese zwei Teile die *Merge*- und die *Sort*-Methode.

In diesem Fall wird ein Feld, der Objekte zur Darstellung einer Person enthält. Die Objekte vom typ `Person` werden nach dem Nach- und Vornamen sortiert.

### Sort

Die Sort-Methode wurde mithilfe zweier gleichnamigen Methoden `sort` implementiert. Die erste dient nur als ein erster Aufruf der zweiten rekursiven Methode, um den richtigen Anfangs- und Endpunkt zu bestimmen. Somit ist der Algorithmus einfach aufrufbar auf einem beliebigem Feld.

Die rekursive Methode ist zuständig für die Halbierung des Feldes, solange dass unser ursprüngliche Feld in kleine Abschnitte zertilt wird. Die maximale Länge dieser Abschnitte ist ein Element.

Abschließend wird die Methode `merge` aufgerufen, um die sortierten Abschnitte in eine sortierte Einheit zusammenzufügen.

```java
public void sort(Person[] personen) {
  sort(personen, 0, personen.length - 1);
}
```
```java
public void sort(Person[] personen, int start, int end) {
  if (end <= start) return;
  int mitte = (start + end) / 2;
  sort(personen, start, mitte);
  sort(personen, mitte + 1, end);
  Person[] teil1 = Arrays.copyOfRange(personen, start, mitte + 1);
  Person[] teil2 = Arrays.copyOfRange(personen, mitte + 1, end + 1);
  merge(teil1, teil2, personen, start);
}
```

#### Laufzeit

Die Laufzeit der Methode `sort` beträgt *O(n log n)*.  Wo *n* die Länge des ursprünglichen Feldes darstellt. Dabei stammt *O(n)* aus der Laufzeit der Methode `merge` und *O(log n)* aus der rekursiven Halbierung.

##### Beispiel

Ein Beispiel mit einem Zahlenfeld der Länge sieben.

###### Aufteilung

Es ist sichtbar, dass die Höhe des Baumes, der die Halbierungen darstellt, proportional dem Logarithmus der Anzahl der Elemente ist.

```
            [6][2][4][1][5][7][3]
           /                     \
   [6][2][4]                     [1][5][7][3]
  /         \                   /            \
[6]         [2][4]         [1][5]            [7][3]
           /      \       /      \          /      \
         [2]      [4]   [1]      [5]      [7]      [3]
```

###### Zusammenfügen

Bei den Aufrufen der Methode `merge` wird ein Spiegelbild der Aufteilung ersichtlich. Das ist auch proportional zum Logarithmus.

```
         [2]      [4]   [1]      [5]      [7]      [3]
            \    /         \    /            \    /
[6]         [2][4]         [1][5]            [3][7]
   \       /                     \          /
   [2][4][6]                     [1][3][5][7]
            \                   /
            [1][2][3][4][5][6][7]   
```

### Merge

Die Merge-Methode ist in `merge` implementiert. Diese Methode fügt die Elemente zweier Felder `pers1` und `pers2` zusammen.

Zu diesem Zweck wird kein neues Feld erstellt, sondern wird das ursprüngliche Feld `result`ab dem Startpunkt `start` mit den zusammengefügten bzw. sortierten Personenobjekten aufgefüllt. Der richtige Startpunkt wird in der Methode `sort` berechnet.

Am Ende ist ein sortierter Abschnitt vorhanden, der im `start` anfängt. Seine Länge gleicht der Summe der Längen der beiden Felder `pers1` und `pers2`.

```java
public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) {
  int i = 0;
  int j = 0;
  while (i < pers1.length && j < pers2.length) {
    if (pers1[i].compareTo(pers2[j]) < 0) {
      result[start] = pers1[i];
      i++;
    } else {
      result[start] = pers2[j];
      j++;
    }
    start++;
  }
  while (i < pers1.length) {
    result[start] = pers1[i];
    i++;
    start++;
  }
  while (j < pers2.length) {
    result[start] = pers2[j];
    j++;
    start++;
  }
}
}
```

#### Laufzeit

Die Laufzeit der Methode `merge` beträgt *O(n)*.  Wo *n* die Länge des sortierten Abschnittes darstellt.

#### Beispiel

Wie das Zusammenfügen funktioniert wird für den letzten Schritt des vorigen Beispiels gezeigt. Es wird ersichtlich, dass das Zusammenfügen eine lineare Laufzeit, die proportional der Länge des Sortierten Abschnittes ist. Das heißt sieben Schritte für sieben Elemente.

###### Anfang
```
pers1 := [2][4][6]    pers2 := [1][3][5][7]
          ^                     ^
result := [2][4][6][1][3][5][7]
           ^
```

###### Schritt 1
```
pers1 := [2][4][6]    pers2 := [1][3][5][7]
          ^                     ^
result := [2][4][6][1][3][5][7]
           ^

2 < 1 ? Nein!

pers1 := [2][4][6]    pers2 := [1][3][5][7]
          ^                        ^
result := [1][4][6][1][3][5][7]
              ^
```

###### Schritt 2
```
pers1 := [2][4][6]    pers2 := [1][3][5][7]
          ^                        ^
result := [1][4][6][1][3][5][7]
              ^

2 < 3 ? Ja!

pers1 := [2][4][6]    pers2 := [1][3][5][7]
             ^                     ^
result := [1][2][6][1][3][5][7]
                 ^
```

###### Schritt 3
```
pers1 := [2][4][6]    pers2 := [1][3][5][7]
             ^                     ^
result := [1][2][6][1][3][5][7]
                 ^

4 < 3 ? Nein!

pers1 := [2][4][6]    pers2 := [1][3][5][7]
             ^                        ^
result := [1][2][3][1][3][5][7]
                    ^
```

###### Schritt 4
```
pers1 := [2][4][6]    pers2 := [1][3][5][7]
             ^                        ^
result := [1][2][3][1][3][5][7]
                    ^

4 < 5 ? Ja!

pers1 := [2][4][6]    pers2 := [1][3][5][7]
                ^                     ^
result := [1][2][3][4][3][5][7]
                       ^
```

###### Schritt 5
```
pers1 := [2][4][6]    pers2 := [1][3][5][7]
                ^                     ^
result := [1][2][3][4][3][5][7]
                       ^

6 < 5 ? Nein!

pers1 := [2][4][6]    pers2 := [1][3][5][7]
                ^                        ^
result := [1][2][3][4][5][5][7]
                          ^
```

###### Schritt 6
```
pers1 := [2][4][6]    pers2 := [1][3][5][7]
                ^                        ^
result := [1][2][3][4][5][5][7]
                          ^

6 < 7 ? Ja!

pers1 := [2][4][6]    pers2 := [1][3][5][7]
                  ^                      ^
result := [1][2][3][4][5][6][7]
                             ^
```

###### Schritt 7
```
pers2 := [1][3][5][7]
                   ^
result := [1][2][3][4][5][6][7]
                             ^

pers2 := [1][3][5][7]
                     ^
result := [1][2][3][4][5][6][7]
                               ^
```
