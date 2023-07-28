insert into recetas (rec_nombre, rec_categoria, rec_preparacion, rec_imagen, rec_estado) values('MATAMBRITO DE CERDO A LA PIZZA CON BATATAS Y MANZANAS SALTEADAS','Carnes','1. Marinar el matambrito con el jugo del limón, la naranja y los granos de pimienta. 2. En una chapa cocinar de un lado y al dar vuelta cubrí con mozzarella, y por encima las cebollas. 3. Cuando esté listo el matambre y queso fundido agregar el verdeo por encima. 4. Guarnición: En una sartén con manteca dorar las batatas y las manzanas, agregar el azúcar para glasear y el romero picado. Condimentar y servir.','matambredecerdo.jpg',1);
insert into recetas (rec_nombre, rec_categoria, rec_preparacion, rec_imagen, rec_estado) values('PAN LACTAL','Pan','1. Mezclar los ingredientes secos y realizar una corona, agregar la levadura, manteca, malta y de a poco el agua. 2. Amasar hasta lograr una masa bien integrada y lisa. Dejar leudar hasta que duplique su volumen. 3. Cortar piezas de 250g a 750g según se prefiera. Estirar cada una en forma rectangular y enrollar presionando. Colocar los panes en moldes tipo budín ingles enmantecadas. 4. Dejar leudar nuevamente. 5. Pintar con doradura. 6. Hornear a 180 grados de 20 a 30 minutos, según el tamaño. ','panlactal.jpg',1);
insert into recetas (rec_nombre, rec_categoria, rec_preparacion, rec_imagen, rec_estado) values('BOMBONES','Dulces','1. Tostar los frutos secos en horno a 150 grados durante 15 minutos. Dejarlos enfriar. 2. Fundir el chocolate a elección y templarlo según su curva. Si utilizan baños de repostería sólo deben fundirlo. 3. Colocarlo en una manga de repostería y realizar pequeños botones (2 cm de diámetro) sobre un papel acetato. 4. De inmediato colocar sobre cada botón un mix de frutos secos y desecados según más les guste. 5. Dejar endurecer (cristalizar) el chocolate a temperatura ambiente. Despegar cuidadosamente con ayuda de guantes para no marcarlos, y reservarlos en caramelera. 6. No debemos guardarlos en heladera, ya que transite mucha humedad a los bombones. Lugar fresco y seco (alacena, Caba de vino).','bombones.jpg',1);

insert into ingredientes (ingre_nombre,ingre_estado,rec_id) values('1 matambre de cerdo',1,1);
insert into ingredientes (ingre_nombre,ingre_estado,rec_id) values('200 g de mozzarella',1,1);
insert into ingredientes (ingre_nombre,ingre_estado,rec_id) values('1 Cebolla morada caramelizada',1,1);
insert into ingredientes (ingre_nombre,ingre_estado,rec_id) values('Harina 1 kg',1,2);
insert into ingredientes (ingre_nombre,ingre_estado,rec_id) values('Azúcar 100 g',1,2);
insert into ingredientes (ingre_nombre,ingre_estado,rec_id) values('Chocolate semi-amargo 100 g.',1,3);
insert into ingredientes (ingre_nombre,ingre_estado,rec_id) values('Chocolate cobertura semi-amargo 400 g.',1,3);
insert into ingredientes (ingre_nombre,ingre_estado,rec_id) values('1 verde de verdeo picado',1,1);

insert into unidadmedida (nombre,estado) values('gramos',1);
insert into unidadmedida (nombre,estado) values('kilos',1);
insert into unidadmedida (nombre,estado) values('tazas',1);
