### **Visão Geral**

Este código implementa uma **simulação de loja virtual** no console, onde o usuário pode visualizar produtos disponíveis, adicioná-los a um **carrinho de compras**, visualizar o conteúdo do carrinho, e finalizar o pedido.
O sistema é orientado a objetos e utiliza os princípios de **encapsulamento**, **herança** e **composição**.

---

### **Classes Principais**

#### 🛒 `data class Product`

Representa um produto disponível na loja.

```kotlin
data class Product(
    val name: String,
    val price: Double
)
```

**Atributos:**

* `name`: Nome do produto.
* `price`: Preço do produto.

**Descrição:**
A classe `Product` é uma *data class*, ideal para representar objetos de dados, pois fornece automaticamente métodos como `toString()`, `equals()` e `hashCode()`.

---

#### 🧺 `open class ShoppingCart`

Gerencia a lista de produtos que estão no carrinho de compras.

```kotlin
open class ShoppingCart {
    protected val items = mutableListOf<Product>()
    
    open fun addProduct(product: Product) { ... }
    open fun showItems() { ... }
    fun calculateTotal(): Double { ... }
}
```

**Atributos:**

* `items`: Lista mutável contendo os produtos adicionados ao carrinho.

**Métodos:**

* `addProduct(product: Product)`:
  Adiciona um produto ao carrinho e exibe uma mensagem de confirmação.

* `showItems()`:
  Exibe todos os produtos no carrinho e o valor total da compra.
  Caso o carrinho esteja vazio, exibe a mensagem “Empty shopping cart!”.

* `calculateTotal()`:
  Retorna a soma de todos os preços dos produtos no carrinho.

**Herança:**
Esta classe é aberta (`open`) para ser estendida por outras classes, como `PurchaseOrder`.

---

#### 🧾 `class PurchaseOrder(val client: Client) : ShoppingCart()`

Representa o pedido de compra de um cliente.
Herdando de `ShoppingCart`, possui todas as funcionalidades de um carrinho, com o acréscimo de dados do cliente.

```kotlin
class PurchaseOrder(val client: Client) : ShoppingCart() {
    fun finishOrder() { ... }
}
```

**Atributos:**

* `client`: Instância da classe `Client`, representando o comprador.

**Métodos:**

* `finishOrder()`:
  Exibe o resumo final do pedido, com os dados do cliente, os itens do carrinho e o valor total.

---

#### 👤 `class Client`

Representa um cliente que realiza a compra.

```kotlin
class Client(val name: String, val email: String)
```

**Atributos:**

* `name`: Nome do cliente.
* `email`: E-mail do cliente.

---

### **🧠 Função Principal (`main`)**

```kotlin
fun main() {
    // lista de produtos disponíveis
    val availableProducts = listOf(
        Product("Mouse Gamer", 120.0),
        Product("Mecanic Keyboard", 250.0),
        Product("Headset", 180.0),
        Product("Monitor 24\"", 900.0),
        Product("Gamer Chair", 1100.0)
    )
    
    println("=== Welcome to the Virtual Store ===")
    
    // coleta de dados do cliente
    print("Enter your name: ")
    val name = readLine() ?: "Client"
    print("Enter your e-mail: ")
    val email = readLine() ?: "email@example.com"
    
    val client = Client(name, email)
    val order = PurchaseOrder(client)
    
    // loop principal do menu
    var keepRunning = true
    while(keepRunning) {
        println("""
            === MENU ===
            1 - Show products
            2 - Add product to the shopping cart
            3 - Show shopping cart
            4 - Finish order
            5 - Exit
        """.trimIndent())
        
        print(" > ")
        when(readLine()) {
            "1" -> { ... } // mostra produtos disponíveis
            "2" -> { ... } // adiciona produto ao carrinho
            "3" -> order.showItems()
            "4" -> { order.finishOrder(); keepRunning = false }
            "5" -> { println("Exiting..."); keepRunning = false }
            else -> println("Invalid option.")
        }
    }
}
```

**Fluxo de execução:**

1. O sistema exibe uma saudação e solicita nome e e-mail do cliente.
2. Mostra um menu com 5 opções:

   * **1:** Mostrar produtos disponíveis.
   * **2:** Adicionar produto ao carrinho.
   * **3:** Mostrar conteúdo do carrinho.
   * **4:** Finalizar o pedido.
   * **5:** Sair do programa.
3. O loop continua até que o usuário finalize o pedido ou escolha sair.

---
