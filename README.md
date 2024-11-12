# Dssd-api

### API docs
http://localhost:8080/swagger-ui/index.html

### Base de datos en cloud
url: dpg-cs3s1pg8fa8c73dglro0-a.oregon-postgres.render.com
user: api_db_oys9_user
db: api_db_oys9

Conexión a db
```bash
psql -h dpg-cs3s1pg8fa8c73dglro0-a.oregon-postgres.render.com -U api_db_oys9_user api_db_oys9
```

Backup tablas y datos
```bash
pg_dump -U api_db_o4iu_user -h dpg-cspjdu52ng1s73d1ti40-a.oregon-postgres.render.com -p 5432 -d api_db_o4iu > backup-api.sql
```

### Pasos para ejecutar el proyecto por primera vez

Dentro de la carpeta "dssd-api"
1. Crear el ejecutable (.jar)  
```bash
mvn clean package
```
2. Crear las imágenes y contenedores de docker
```bash
docker-compose up --build
```
### Correr el proyecto
Con ejecutar el siguiente comando es suficiente 
```bash
docker-compose up --build
```

### Posibles errores
Puede que aparezca un error, indicando el puerto 5432 este en uso
con el siguiente comando se soluciona
```bash
sudo systemctl stop postgresql
```


