Feature: CucumberJava
Description: Prueba BDD




Scenario: buscar el sitio web de Falabella en buscador Google e ingresar a la pagina validando que cargue correctamente.

Given Que tengo que invocar el navegador
When Necesite abrir el portal de busquedas de Google
Then Podre buscar e ingresar al sitio web de Falabella


Scenario: buscar producto, ver detalle y agregarlo a la bolsa de productos.

Given Que tengo que buscar un producto desde la barra de busqueda
And debo visualizar el detalle del producto seleccionado
Then lo Podre agregar a la bolsa de products


Scenario: aumentar a 2 productos, agregar garantía extendida de 2 años y dar clic al boton de Ir A compras.

Given Que tengo que aumentar en 2 el producto agregado en la bolsa de productos
And necesito agregarle una garantia extendida de 2 anios
Then Podre darle clic al boton de Ir A compras