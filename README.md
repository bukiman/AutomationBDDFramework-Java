# 🧪 Framework de Automatización en Java — Selenium | Rest Assured | Cucumber | TestNG | Allure

Este proyecto es un **framework de automatización** desarrollado en **Java**, diseñado para ejecutar pruebas **UI (Selenium)** y **API (Rest Assured)** bajo un enfoque **BDD con Cucumber**, utilizando **TestNG** como motor de ejecución y **Maven** para la gestión de dependencias y ejecución desde consola.  
Además, integra **Allure Reports** para la generación de reportes profesionales y visuales.

---

## 🚀 Tecnologías Principales
- **Java 11+**
- **Selenium WebDriver** para pruebas UI
- **Rest Assured** para pruebas API
- **Cucumber (Gherkin)** para BDD
- **TestNG** como test runner
- **Allure Reports** para reportes detallados
- **Maven** para ejecución y gestión de dependencias

---

## ▶️ Ejecución de Pruebas con Maven
- **Ejecutar todas las pruebas**
```
mvn clean test
```

- **Ejecutar solo pruebas de UI**
```
mvn clean test -Dcucumber.filter.tags="@UIs"
```

- **Ejecutar solo pruebas de API**
```
mvn clean test -Dcucumber.filter.tags="@APIs"
```

- **Ejecutar un feature en específico**
```
mvn clean test -Dcucumber.features="src/test/resources/features/login.feature"
```

---
## 📊 Generar reporte Allure
El proyecto genera reportes compatibles con Allure, los cuales pueden visualizarse con los siguientes comandos:

```
mvn allure:report
```

Levantar un servidor local para visualizar los reportes

```
mvn allure:serve
```
Con este comando, el reporte se abrirá automáticamente en tu navegador.