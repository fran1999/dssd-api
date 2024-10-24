# dssd-api

### API docs
http://localhost:8080/swagger-ui/index.html

# Pasos para ejecutar el proyecto por primera vez

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


