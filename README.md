![imagen no disponible](https://firebasestorage.googleapis.com/v0/b/drinkchampions-c2df8.appspot.com/o/ic_launcher.png?alt=media&token=e2584bc5-1740-4613-80ee-6a0161738489)

Logo de la app:ic_launcher.png (esta en res/mipmap-hdpi)
url de la api:http://ergast.com/api/f1/
Nom de la app: Drinkchampions

Funcionalitat de la app:
Per primer si entrem a la nostra app per primera vegada, tota la informació de la app com per exemple les imatges, la informació(Dummes) es descarregaran de firebase,
al principi ens apareixerà una llista de cerveses les quals son les mes demanades i si polsem en una de elles sens obrira una pestanya on sens mostrar informació
detallada de la cervesa la qual hem accedit, allí podrem veure una xicoteta descripció de la cervesa, podrem guardar la cervesa com a favorit, podrem
obtindre mes informació de la cervesa anant al boto de mes informació (Anirà a la pagina principal de la cervesa),i no en totes en algunes cerveses podrem polsar en
la opció de «street view» la qual en mostra una vista de la de la fabrica on s'ha fabricat la cervesa la qual estem mirant i per ultim podrem prémer en la geolocalització
mitjançant Google Mapa que ens mostrar la ubicació via satèl·lit.
En el apartat de categoria ens apareixeran 3 conjunts de cerveses, nacionals,tostaes i universals, i si accedim a una de elles
podrem fer tot el que he descrit anteriorment.
En el apartat de configuració podem configurar de quin any volem que apareguen els pilots de formula 1 des de 2008 i 2017, una vegada
configurat anem a el apartat de  recerca  i allí ens apareixeran els distints pilots de la f1 de el any que nosaltres hem configurat, també tenim la opció
per a buscar un piloto en concret, i si accedim a ell el que podem fer o veure  es el següent: podem guardar al nostre piloto com
a favorit, vorer la seua imatge, el seu nom i apellido,la seua nacionalitat i també ens permet la opció de accedir a  la seua Wikipedia.
En el apartat de localització podrem veure un mapa de espanya i uns marcadors , les quals les localitzacions en roig son
les ciutats natals dels pilots de la F1 que son de espanya mentre que els verds son les fabrique de cervesa que estan en espanya.
i si polsem a algun marcador podrem veure un xicotet text.
En el apartat de favorits podrem veure una llista del nostres pilots i cerveses favorits, poden accedir altra vegada als seus
detalls, estòs favorits estan guardats en SQL Lite ja que no tots els usuaris tenen els mateixos favorits i gracies a guardar-los en
SQL cada usuari te els seus propis favorits
En el apartat de registre, es una apartat per a un administrador, i quan es registre apareixera una finestra, en la qual tindrà tota
la informació guardada en firebase per tant podrà  accedir a ella i fer un crud de la informació.
En el apartat de Twitter, es una apartat per a que es registre un un usuari , una vegada s`ha registrat podrà sign out o anar al menú principal
i tindrà unes certes Avantatges, com per exemple quan accedix-ca a la informació de una cervesa, després podar anar al apartat del meu conter
i allí podrà trobar-se amb descontes pensats per a les cerveses a les quals ell a accedit, i en eixe aparta també pot trobar-se amb una xicoteta informació
del seu conter.
I per ultim tindrà la opció de canviar la opció del idioma a Angles, València i Espanyol

EL QUE A FET CADA UNO:

Marcos : connexió en l'api, poder llistar tots els pilots, comptador dels resultats dels pilots, poder triar un any de l'api, adaptar el detalls dels pilots
Ajustar la llista d'inici de ric pa ric i la de categories i direccions (actualment descomptes) , ajustar detalls cerveses, afegir botó de geolocalització i mes informació a uns direcció web, el mateix
en pilots,Afegir camps a sql per a poder guardar pilots i cerveses en favorits i de manera offline
i adaptar els detalls  amb la mateixa vista que la de pilots (Adaptació prèvia realitzada per jorge i adaptació final per llore),
també poder guardar-los en firebase (realitzada l'entrada només per a administradors per llore) i poder modificar qualsevol  camp de les cerveses que posteriorment s'actualitzara en la visió de tots els usuaris,
ja que les dades es s'obtenen  de firebase, Anàlisi de l'usuari  indicant per a cada cervesa que haja visitat amb mes freqüència un descompte personalitzat,
i per ultim la adaptació de  la funció per que els imatges puguen ser guardades  de manera local o arreplega-es  per mitjà d'una direcció web


Jorge: Implementació del Google Map amb Rico Pa Rico , fusió de la implementació del booksearch  amb la implantació del booksearch, implementació del SQLlite amb el Rico Pa Rico (Va ser necessari crear un Floatincactionbutton per ha crear els elements del sqllite),
implementació de un boto en els detalls, per ha accedir ha la localització de les fabriques, recerca de la localització de les fabriques principals de les cerveses, amb la seua pagina web oficial, implementació del lang de tota l'aplicació, els idiomes disponibles son Castella, València i Angles,
implementació del registre del clients per Twitter, implementació de la localització, hem utilitzat 2 tipus de marcadors, els marcadors de color roig, son la localització dels pilots de F1 de origen Espanyol, i els marcadors de color verd, son les fabriques de cerveses Espanyoles,
al púlsar el marcador, es mostra el nom del pilot o la fabrica de la cervesa, Implementació dels Settings, a  utilitzat un Spinner, ja que no podia  utilitzar el disseny de la app que  teniem de model.
La principal funció dels Settings es la de poder elegir un any, el qual la api utilitza per ha mostrar els pilots de eixe any i per ultim També  creació del  compte de correu i de Twitter oficial de Drinkchampions

Llorenç: Implementació del book search a la app , creació del logo de la app, implementació del firebase a la app, implementació
del logo i el nom de la app, implementació de fel firebase juntament amb el sql lite, creació de manuals que  posteriorment
ens han servit de gran ajuda, solucionant de alguns errors de llibreries, implementació del apartat dels favorits juntament amb marcos, afegiment
del Splas_screen, implementació de la funció de registrar-se per correu per a un usuari autoritzat, configuració del
firebase per al tema de les persones autoritzades,afegiment del streetview i afegiments del panoID de les fabriques de Cervera i
afegiment de les descripcións de les cerveses juntament amb marcos,implementació del registre per a un administrador per a que puja
fer un CRUD, fet per marcos i acoplament del contact amb el pilots i les cerveses de espanya fet per jorge, implementació de uns botons
per a que cuan un usuari estiga registrat puja anar al menú principal o tancar la sessio, implementació de una funcio per a que
un usuari que no estiga registrat no puga entrar en el apartat dels  descontes i el llance a la vista del login, implementació
i creació de toolbars, implementació del settings a la app i per ultim creació del README
