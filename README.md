Specs.

User
- Validar que no se puede crear un usuario sin email
- Validar que una cuenta de email es unica (no podemos crear 2 cuentas con el mismo email) ******

Users
- Validar que podemos ver index
- Validar que podemos ver edit
- Validar que update no solo se puede llamar con PUT

http://www.sienaproject.com/documentation-getting-started.html

BackHaul
Debe tener
 - Origin : Ciudad Origen
 - Destination: Ciudad Destino
 - OriginDate: Fecha en que sale del destino
 - CargoArea: Area disponible en m^2 (decimales)
 - CostByArea: Costo por m^2 (decimales)
 - CostByWeight: Costo por kilo (decimales)
 - Contact: Informacion de contacto 
 - User: Usuario que registra la oferta

Debemos validar que no sea nulo: Origin, Destination, Contact
Debemos validar que OriginDate > Hoy
Debemos validar que CargoArea, CostByWeight, CostByArea > 0

Index - no AU
Debemos mostrar ofertas > Hoy

New/Create - Si AU

Edit/Update - Si AU
Verificar que solo el usuario original pueda modificarlo
/backhaul/1/edit => user1

No tenemos destroy

Repositorio Github de Siena => gimenete

