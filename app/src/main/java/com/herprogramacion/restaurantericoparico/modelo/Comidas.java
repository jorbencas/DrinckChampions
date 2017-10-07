package com.herprogramacion.restaurantericoparico.modelo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.herprogramacion.restaurantericoparico.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/03/17.
 */

public class Comidas {
    public static List<ToDoItem> getPrincipales() {
        return Principales;
    }

    public static List<ToDoItem> getBEBIDAS() {
        return Especiales;
    }

    public static List<ToDoItem> getPOSTRES() {return Universales;
    }

    public static List<ToDoItem> getPLATILLOS() {
        return Prinicpal2;
    }

    public static final List<ToDoItem> Principales = new ArrayList<>();
    public static final List<ToDoItem> Especiales = new ArrayList<>();
    public static final List<ToDoItem> Universales = new ArrayList<>();
    public static final List<ToDoItem> Prinicpal2 = new ArrayList<>();


    /*
    static {
        Principales.add(new Comida(5.0f, "Heineken",R.drawable.heineken, "La cerveza más distribuida del mundo.\n" +
                "Heineken es una cerveza tipo Pilsen producida\n" +
                "siguiendo estándares de la más alta calidad y\n" +
                "elaborada utilizando exclusivamente ingredientes\n" +
                "naturales, según un proceso que data de 1873 y\n" +
                "que preserva la mejor tradición cervecera europea.\n" +
                "Combina un perfil de producto con cuerpo para\n" +
                "los que aprecian la buena cerveza, a la vez que es\n" +
                "ligera, suave y fácil de beber.\n" +
                "Presente en más de 170 países,\n" +
                "es una cerveza Premium, abierta, cosmopolita y\n" +
                "moderna, que se dirige a los consumidores\n" +
                "de forma natural e inteligente. 5% Vol."
                ,5f,1,"http://www.heineken.com/es","https://www.google.es/maps/place/Heineken+Experience/@52.357982,4.8891749,16z/data=!4m12!1m6!3m5!1s0x47c609ed3c25194b:0x4fd3caab9141ac0a!2sHeineken+Experience!8m2!3d52.3578313!4d4.8918249!3m4!1s0x47c609ed3c25194b:0x4fd3caab9141ac0a!8m2!3d52.3578313!4d4.8918249","u-iTIdijV7lIH7RpeL8HOw"));

      Principales.add(new Comida(2.0f, "Amstel Radler", R.drawable.amstelradler,"Amstel Radler está compuesta de un 40% cerveza\n" +
              "Amstel y 60% zumo de limón natural, sin aditivos\n" +
              "artificiales, con apariencia turbia y 2% alcohol. La\n" +
              "mejor alternativa de la clara con limón.\n" +
              "La tradición de Radler tiene su origen en Baviera\n" +
              "cuando en 1922 miles de ciclistas bajan a un Pub\n" +
              "de Munich y el dueño, Franz Xaver Kugler, para no\n" +
              "quedarse sin producto mezcla la cerveza con\n" +
              "zumo de limón ¡nace así una nueva categoría,\n" +
              "“Radler” (ciclista en alemán)!",5f,1,"http://www.amstel.es/nuestras-cervezas/amstel-radler-con-limon/","https://www.google.es/maps/@39.4728629,-0.5098965,3a,87.1y,4.57h,88.13t/data=!3m10!1e1!3m8!1sN93jMC79ff0HV85rBC08Cg!2e0!6s%2F%2Fgeo2.ggpht.com%2Fcbk%3Fpanoid%3DN93jMC79ff0HV85rBC08Cg%26output%3Dthumbnail%26cb_client%3Dmaps_sv.tactile.gps%26thumb%3D2%26w%3D203%26h%3D100%26yaw%3D3.0669267%26pitch%3D0%26thumbfov%3D100!7i13312!8i6656!9m2!1b1!2i50!6m1!1e1",""));

        Principales.add(new Comida(5.9f, "Cruzcampo Cruzial", R.drawable.cruzial,"Cruzcampo, desde 1.904, es una empresa de\n" +
                "tradición centenaria, siendo la marca líder en\n" +
                "España en hostelería y en cerveza de Barril.\n" +
                "Cruzcampo es 100% natural en todos sus\n" +
                "ingredientes: malta “pilsen” de cebada, lúpulo,\n" +
                "maíz, agua y levadura con una graduación\n" +
                "alcohólica del 4,8% en Vol.\n" +
                "Por su bebestibilidad es la cerveza ideal\n" +
                "para acompañar las tapas.",5f,1,"http://www.cruzcampo.es/maestros-cerveceros/cruzcampo-cruzial/","https://www.google.es/maps/place/Heineken/@37.7825953,-3.8256733,17z/data=!4m21!1m15!4m14!1m6!1m2!1s0x0:0x7d6536b69e3af8f4!2sHeineken!2m2!1d-3.825227!2d37.7843146!1m6!1m2!1s0xd6dd72e69cee795:0x7d6536b69e3af8f4!2sHeineken,+Carretera+C%C3%B3rdoba,+km+3,+23005+Ja%C3%A9n!2m2!1d-3.825227!2d37.7843146!3m4!1s0xd6dd72e69cee795:0x7d6536b69e3af8f4!8m2!3d37.7843146!4d-3.825227","Xorsl5ckGPYSLfQFOjs9JQ"));


        Principales.add(new Comida(6.2f, "Amstel Oro", R.drawable.amsteloro,"Desde su elaboración en 1956, si existe una cerveza que brilla por sí misma, esa es la Amstel Oro. Menos mal que al probarla cae bien, y con el tiempo hasta se hace querer.\n" +
                "\n" +
                "Sin duda, tiene todos los ingredientes para ser la más pretenciosa de nuestra gama, pero prefiere ser prudente. Dicen que no todo lo que brilla es oro. Estamos de acuerdo, es Amstel Oro.",5f,1,"http://www.amstel.es/nuestras-cervezas/amstel-oro/","https://www.google.es/maps/place/Dr.+A.H.+Heineken+Stichting+voor+de+Kunst/@52.359315,4.8930613,546m/data=!3m1!1e3!4m6!3m5!1s0x47c609ecd2f17039:0xbc418f55080b8534!4b1!8m2!3d52.359278!4d4.8930807","8DBdGW0_5Y7VLuoeXLchiA"));


        Principales.add(new Comida(0.0f, "Heineken 0,0", R.drawable.heinekenazul,"Nuestros maestros cerveceros empezaron desde cero y dedicaron años a explorar, elaborar y probar innumerables recetas hasta encontrar una que se define por sus refrescantes notas afrutadas y su suave cuerpo con sabor a malta, perfectamente equilibrado. \n" +
                "Una receta digna de llevar la marca Heineken. Por supuesto, manteniendo sus reconocibles señas de identidad desde 1873: ingredientes 100% naturales y su propia levadura Tipo A.\n" +
                "Fue difícil, pero no imposible. ",5f,1,"http://www.heineken.com/es"," https://www.google.es/maps/place/Heineken+Experience/@52.2392757,1.5778884,6.5z/data=!4m5!3m4!1s0x47c609ed3c25194b:0x4fd3caab9141ac0a!8m2!3d52.3578313!4d4.8918249","o_vVR3s4N7Ew_0_7GK9GcQ"));

        Principales.add(new Comida(5.9f, "Desperados", R.drawable.desperados2,"Desperados es una cerveza única en el mercado,\n" +
                "aromatizada con Tequila, lo que contribuye\n" +
                "a su sabor distinto, suave, y que la sitúa en el\n" +
                "límite entre la cerveza y los espirituosos, con una\n" +
                "graduación alcohólica del 5,9%.",5f,1,"https://www.desperados.com/es-es/products/original","https://www.google.es/maps/place/Brasserie+Fischer/@48.6001344,7.7406544,17z/data=!3m1!4b1!4m5!3m4!1s0x4796c871babe4f27:0xabfe0b36ead47cdc!8m2!3d48.6001344!4d7.7428431",""));



        Prinicpal2.add(new Comida(2.0f, "La cerveza más distribuida del mundo.\n" +
                "Heineken es una cerveza tipo Pilsen producida\n" +
                "siguiendo estándares de la más alta calidad y\n" +
                "elaborada utilizando exclusivamente ingredientes\n" +
                "naturales, según un proceso que data de 1873 y\n" +
                "que preserva la mejor tradición cervecera europea.\n" +
                "Combina un perfil de producto con cuerpo para\n" +
                "los que aprecian la buena cerveza, a la vez que es\n" +
                "ligera, suave y fácil de beber.\n" +
                "Presente en más de 170 países,\n" +
                "es una cerveza Premium, abierta, cosmopolita y\n" +
                "moderna, que se dirige a los consumidores\n" +
                "de forma natural e inteligente. 5% Vol.",R.drawable.heineken,"",3f,2,"http://www.heineken.com/es"," https://www.google.es/maps/place/Heineken+Experience/@52.2392757,1.5778884,6.5z/data=!4m5!3m4!1s0x47c609ed3c25194b:0x4fd3caab9141ac0a!8m2!3d52.3578313!4d4.8918249",""));


        Prinicpal2.add(new Comida(3.2f, "Amstel Radler", R.drawable.amstelradler," Amstel Radler está compuesta de un 40% cerveza\n" +
                "Amstel y 60% zumo de limón natural, sin aditivos\n" +
                "artificiales, con apariencia turbia y 2% alcohol. La\n" +
                "mejor alternativa de la clara con limón.\n" +
                "La tradición de Radler tiene su origen en Baviera\n" +
                "cuando en 1922 miles de ciclistas bajan a un Pub\n" +
                "de Munich y el dueño, Franz Xaver Kugler, para no\n" +
                "quedarse sin producto mezcla la cerveza con\n" +
                "zumo de limón ¡nace así una nueva categoría,\n" +
                "“Radler” (ciclista en alemán)!",5f,2,"http://www.amstel.es/nuestras-cervezas/amstel-radler-con-limon/","https://www.google.es/maps/place/Dr.+A.H.+Heineken+Stichting+voor+de+Kunst/@52.359315,4.8930613,546m/data=!3m1!1e3!4m6!3m5!1s0x47c609ecd2f17039:0xbc418f55080b8534!4b1!8m2!3d52.359278!4d4.8930807",""));


        Prinicpal2.add(new Comida(12f, "Cruzcampo Cruzial", R.drawable.cruzial,"Se trata de una cerveza con lúpulo 100% seleccionado, un producto\n" +
                "equilibrado, redondo y al mismo tiempo muy refrescante y fácil de beber.\n" +
                "El nombre responde a la descripción del producto, ya que se trata de la\n" +
                "unión de palabras Cruzcampo Especial.",3f,2,"http://www.cruzcampo.es/maestros-cerveceros/cruzcampo-cruzial/","https://www.google.es/maps/place/Heineken/@37.7825953,-3.8256733,17z/data=!4m21!1m15!4m14!1m6!1m2!1s0x0:0x7d6536b69e3af8f4!2sHeineken!2m2!1d-3.825227!2d37.7843146!1m6!1m2!1s0xd6dd72e69cee795:0x7d6536b69e3af8f4!2sHeineken,+Carretera+C%C3%B3rdoba,+km+3,+23005+Ja%C3%A9n!2m2!1d-3.825227!2d37.7843146!3m4!1s0xd6dd72e69cee795:0x7d6536b69e3af8f4!8m2!3d37.7843146!4d-3.825227",""));

        Prinicpal2.add(new Comida(9, "Amstel Oro", R.drawable.amsteloro,"Desde su elaboración en 1956, si existe una cerveza que brilla por sí misma, esa es la Amstel Oro. Menos mal que al probarla cae bien, y con el tiempo hasta se hace querer.\n" +
                "\n" +
                "Sin duda, tiene todos los ingredientes para ser la más pretenciosa de nuestra gama, pero prefiere ser prudente. Dicen que no todo lo que brilla es oro. Estamos de acuerdo, es Amstel Oro.",3f,2,"http://www.amstel.es/nuestras-cervezas/amstel-oro/","https://www.google.es/maps/place/Dr.+A.H.+Heineken+Stichting+voor+de+Kunst/@52.359315,4.8930613,546m/data=!3m1!1e3!4m6!3m5!1s0x47c609ecd2f17039:0xbc418f55080b8534!4b1!8m2!3d52.359278!4d4.8930807",""));

        Prinicpal2.add(new Comida(5.9f, "Paulaner", R.drawable.paulaner,"\n" + "La cerveza blanca de levadura número 1 en Alemania y una de las más apreciadas del mundo. De apariencia turbia, se presenta en el vaso con un brillante color dorado aterciopelado, bajo una robusta corona de espuma que verdaderamente merece este nombre. Ya desde el comienzo de su preparación emana de este clásico de la cerveza blanca de Múnich un ligero aroma a plátano. El conocedor aprecia toques de mango y de piña y ensalza el hermoso equilibrio de dulzor y amargor. Los amantes de la cerveza disfrutan del fino aroma de levadura y de su fabulosa suavidad burbujeante. Es la típica cerveza para tomar al aire libre, capaz de unir a personas de todo el mundo. ",5f,9,"com/herprogramacion/restaurantericoparico/modelo/Comidas.java:50","https://www.google.es/maps/place/Paulaner+Brauerei/@48.1695474,11.4403963,12z/data=!4m8!1m2!2m1!1sPaulaner+factory,+Munich,+Germany!3m4!1s0x0:0x6d43a6fd084a0978!8m2!3d48.1767608!4d11.4365959?hl=en",""));

        Prinicpal2.add(new Comida(34f, "Desperados", R.drawable.desperados2,"Desperados es una cerveza única en el mercado,\n" +
                "aromatizada con Tequila, lo que contribuye\n" +
                "a su sabor distinto, suave, y que la sitúa en el\n" +
                "límite entre la cerveza y los espirituosos, con una\n" +
                "graduación alcohólica del 5,9%.",3f,2,"https://www.desperados.com/es-es/products/original","https://www.google.es/maps/place/Brasserie+Fischer/@48.6001344,7.7406544,17z/data=!3m1!4b1!4m5!3m4!1s0x4796c871babe4f27:0xabfe0b36ead47cdc!8m2!3d48.6001344!4d7.7428431",""));


        Especiales.add(new Comida(5, "Affligem Double", R.drawable.affligem,"Una capa sedosa corona una combinación compleja de sabores de clavo y anís. El profundo sabor a clavo del primer sorbo se transforma en un sabor acaramelado más suave al recorrer el paladar. Después de un rápido giro de la copa, la Affligem Double libera agradables aromas de lúpulo y notas de plátano.",3f,3,"https://www.affligembeer.com/es/beers/double/","https://www.google.es/maps/place/Affligem,+B%C3%A9lgica/@50.9052823,4.0921915,13z/data=!4m5!3m4!1s0x47c3bc581abc6ae7:0xf409a1b458f98103!8m2!3d50.9084!4d4.11281",""));

        Especiales.add(new Comida(5, "Affligem Triple", R.drawable.affligemtriple, "Riqueza terrosa en un brillante cuerpo ámbar cubierto por una capa cremosa. Al alzar la copa nos invade el aroma a pan horneado, plátano y fruta madura. La dulzura del azúcar residual se mezcla con el sabor a albaricoque y melocotón en cada sorbo. De cuerpo medio y suave en la boca, la Affligem Triple tiene un final especiado y amargo.\n",3f,3,"https://www.affligembeer.com/es/beers/triple/","https://www.google.es/maps/place/Affligem,+B%C3%A9lgica/@50.9052823,4.0921915,13z/data=!4m5!3m4!1s0x47c3bc581abc6ae7:0xf409a1b458f98103!8m2!3d50.9084!4d4.11281",""));

        Especiales.add(new Comida(24, "Paulaner Salvator", R.drawable.paulanersalvator,"Con esta bebida comienza la historia de nuestra fábrica de cerveza. Y también la historia de la cerveza fuerte en Baviera, ya que fueron los monjes paulinos los inventores de esta bebida estacional de alto contenido alcohólico y baja fermentación. Desde hace ya más de 375 años fabricamos la cerveza Salvator siguiendo la receta tradicional, que hemos ido perfeccionando con el paso de los años: con una corona de espuma color caramelo, esta cerveza de tonalidad castaña, combinada con su seductor aroma de chocolate, despliega su agradable intensidad en cada sorbo. A ella se une la más fina malta de Múnich, completada por una ligera nota de lúpulo en el paladar. Tantas veces copiada pero nunca conseguida: ¡la madre de todas las cervezas estacionales de alto contenido alcohólico, designadas tradicionalmente con el sufijo “-ator“!",5f,3,"https://www.paulaner.com/es","https://www.google.es/maps/place/Paulaner+Brauerei/@48.1695474,11.4403963,12z/data=!4m8!1m2!2m1!1sPaulaner+factory,+Munich,+Germany!3m4!1s0x0:0x6d43a6fd084a0978!8m2!3d48.1767608!4d11.4365959?hl=en",""));

        Especiales.add(new Comida(30, "Cruzcampo Gran Reserva", R.drawable.granreserva,"De la inspiración de los maestros cerveceros de\n" +
                "Cruzcampo nace Cruzcampo Gran Reserva:\n" +
                "una exquisita combinación de lúpulos aromáticos\n" +
                "y la mejor selección de maltas, de sabor intenso\n" +
                "y cuerpo equilibrado fruto de un tradicional y\n" +
                "pausado reposo en bodega.",3f,3,"http://www.cruzcampo.es/maestros-cerveceros/conoce-mas-sobre-nuestros-productos/","https://www.google.es/maps/place/Heineken/@37.7825953,-3.8256733,17z/data=!4m21!1m15!4m14!1m6!1m2!1s0x0:0x7d6536b69e3af8f4!2sHeineken!2m2!1d-3.825227!2d37.7843146!1m6!1m2!1s0xd6dd72e69cee795:0x7d6536b69e3af8f4!2sHeineken,+Carretera+C%C3%B3rdoba,+km+3,+23005+Ja%C3%A9n!2m2!1d-3.825227!2d37.7843146!3m4!1s0xd6dd72e69cee795:0x7d6536b69e3af8f4!8m2!3d37.7843146!4d-3.825227",""));

        Especiales.add(new Comida(30, "Amstel Oro", R.drawable.amsteloro,"Desde su elaboración en 1956, si existe una cerveza que brilla por sí misma, esa es la Amstel Oro. Menos mal que al probarla cae bien, y con el tiempo hasta se hace querer.\n" +
                "\n" +
                "Sin duda, tiene todos los ingredientes para ser la más pretenciosa de nuestra gama, pero prefiere ser prudente. Dicen que no todo lo que brilla es oro. Estamos de acuerdo, es Amstel Oro.",3f,3,"http://www.amstel.es/nuestras-cervezas/amstel-oro/","https://www.google.es/maps/place/Dr.+A.H.+Heineken+Stichting+voor+de+Kunst/@52.359315,4.8930613,546m/data=!3m1!1e3!4m6!3m5!1s0x47c609ecd2f17039:0xbc418f55080b8534!4b1!8m2!3d52.359278!4d4.8930807","Xorsl5ckGPYSLfQFOjs9JQ"));

        Especiales.add(new Comida(12, "Guinness", R.drawable.guinness,"El sabor de Guinness Original es tan profundo como su color. La cebada bien definida se abre paso entre los lúpulos. Su fuerza atrae y persisten sabores atrevidos. Combinación agridulce. Un sabor generoso y refrescante. Elaborada con talento. Creada para durar.",3f,15,"https://www.guinness.com/es-es/","https://www.google.es/maps/place/Guinness+Storehouse/@53.3418772,-6.2889033,17z/data=!3m1!4b1!4m5!3m4!1s0x48670e8440c5056b:0xb31933927505e7a2!8m2!3d53.341874!4d-6.2867093?hl=en","caPfrwbiOOav5b9f2-W_Kg"));


        Universales.add(new Comida(2, "Dos x", R.drawable.dosx,"Es una cerveza con suave sabor a malta y ligero aroma a cítrico. De bajo resabio. Esta cerveza ofrece una experiencia agradable y refrescante al paladar de quien la prueba. Se trata de una bebida tipo pale lager, que produce muy poca y blanca espuma. Es de cuerpo de ligero a medio. Su sabor es dulce, con notas frutales y de caramelo. Es difícil distinguir las notas del lúpulo por su bajo amargor.",3f,4,"https://www.dosequis.com/","https://www.google.es/maps/place/M%C3%A9xico/@22.5574704,-120.805595,4z/data=!3m1!4b1!4m5!3m4!1s0x84043a3b88685353:0xed64b4be6b099811!8m2!3d23.634501!4d-102.552784",""));

        Universales.add(new Comida(3, "Mort Subite", R.drawable.mortsubitextreme,"Este es un estilo, con más de 400 años, sólo utilizado en Bélgica. Su origen está en el valle del río Zenne (región de Payottenland y provincia de Brabante), único lugar donde se pueden localizar las dos principales especies de levaduras responsables de la fermentación de las lambic. Con muy buen criterio en 1992 Europa registró los términos ‘lambic’ y ‘gueuze’, para que sólo los utilizaran en esta región",3f,4,"http://www.mort-subite.be/","https://www.google.es/maps/place/Italia/@40.9560908,3.5549479,5z/data=!3m1!4b1!4m5!3m4!1s0x12d4fe82448dd203:0xe22cf55c24635e6f!8m2!3d41.87194!4d12.56738",""));

        Universales.add(new Comida(5.2f, "Paulaner", R.drawable.paulaner,"La cerveza blanca de levadura número 1 en Alemania y una de las más apreciadas del mundo. De apariencia turbia, se presenta en el vaso con un brillante color dorado aterciopelado, bajo una robusta corona de espuma que verdaderamente merece este nombre. Ya desde el comienzo de su preparación emana de este clásico de la cerveza blanca de Múnich un ligero aroma a plátano. El conocedor aprecia toques de mango y de piña y ensalza el hermoso equilibrio de dulzor y amargor. Los amantes de la cerveza disfrutan del fino aroma de levadura y de su fabulosa suavidad burbujeante. Es la típica cerveza para tomar al aire libre, capaz de unir a personas de todo el mundo,5f,4,https://www.paulaner.com/es","https://www.google.es/maps/place/Paulaner+Brauerei/@48.1695474,11.4403963,12z/data=!4m8!1m2!2m1!1sPaulaner+factory,+Munich,+Germany!3m4!1s0x0:0x6d43a6fd084a0978!8m2!3d48.1767608!4d11.4365959?hl=e,yUHWPkDrI34otxr086Siqg"));


        Universales.add(new Comida(4, "Murphy's", R.drawable.murphys,"Es una cerveza dry stout (o estilo irlandés) elaborada en el Condado de Cork, Irlanda, siguiendo la receta original de la Cervecería de Murphy's desde 1856.En comparación con sus competidores, más amargos y más pesados, principalmente Guinness y Beamish, Murphy's es un stout ligero y más dulce. Su sabor evoca caramelo y malta, y es descrito como \" un pariente lejano de leche con chocolate",3f,4,"http://www.murphys.com/beer/murphys-irish-red/","https://www.google.es/maps/place/Irlanda/@53.4097442,-10.5771598,7z/data=!3m1!4b1!4m5!3m4!1s0x4859bae45c4027fb:0xcf7c1234cedbf408!8m2!3d53.41291!4d-8.24389",""));

        Universales.add(new Comida(5, "Birra Moreti", R.drawable.birramoreti,"La Birra Moretti es el nombre de una cerveza italiana. La compañía de fabricación fue fundada en Udine en 1859 por Luigi Moretti, y en 1996 fue vendido a la empresa holandesa Heineken International, actual propietaria de la marca. La cerveza Moretti es una de las cervezas más vendidas en Italia y es muy famosa en el extranjero.",5f,4,"http://www.birramoretti.com/","https://www.google.es/maps/place/Italia/@40.9560908,3.5549479,5z/data=!3m1!4b1!4m5!3m4!1s0x12d4fe82448dd203:0xe22cf55c24635e6f!8m2!3d41.87194!4d12.56738",""));

        Universales.add(new Comida(5, "Foster's", R.drawable.fosters,"La Foster's es una cerveza australiana, tipo lager y de fermentación baja, de color rubio y de espuma blanca y medianamente duradera, con un 5% de alcohol en volumen, de sabor bueno y suave, bastante refrescante y ligera, de un regusto lejanamente amargo y final fácil, delicada y agradable.",3f,4,"http://www.fostersbeer.com/av?url=http://www.fostersbeer.com/","https://www.google.es/maps/place/Australia/@-21.1895625,95.4746877,3z/data=!3m1!4b1!4m5!3m4!1s0x2b2bfd076787c5df:0x538267a1955b1352!8m2!3d-25.274398!4d133.775136"," "));
    }
*/

    public static ToDoItem getCourseByPosition(List<ToDoItem> items, int position) {
        if(items == Universales){
            return items.get(position);
        }else if(items == Prinicpal2){
            return items.get(position);
        }else if(items == Especiales){
            return items.get(position);
        }else{
            return Principales.get(position);
        }

    }
}


