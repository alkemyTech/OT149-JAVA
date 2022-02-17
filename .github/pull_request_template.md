### ¿Por qué es necesario este cambio?
<!--
- Descripción breve que incluya detalles sobre la solicitud de Jira
-->

### Tipo de cambio
- [ ] New feature
- [ ] New Tests
- [ ] Bug fix
- [ ] Refactor

### Checklist

- [ ] Existe trazabilidad entre este cambio y Jira.
- [ ] Se corrió un ```mvn clean install``` y todos los test terminaron exitosamente.
- [ ] No existen variables y/o imports sin utilizar en las clases modificadas.
- [ ] No hay imports en las clases modificadas anotadas con asterisco. Ej: ```com.somepackage.*```
- [ ] Se utilizó ```Slf4j``` para el log de la aplicación y no ```System.out```.
- [ ] Se retorna ```java.util.Optional<T>``` en métodos que puedan devolver objetos nulos.
- [ ] Los métodos public están documentados con ```javadoc```.
