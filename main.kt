import java.util.UUID

data class Product(
    val name: String,
    val price: Double
)

open class ShoppingCart {
    protected val items = mutableListOf<Product>()
    
    open fun addProduct(product: Product) {
        items.add(product)
        
        println("${product.name} added to shopping cart.")
    }
    
    open fun removeProduct(index: Int) {
        if(index in 1..items.size) {
            val removed = items.removeAt(index - 1)
            
            println("${remove.name} removed from shopping cart.")
        } else {
            println("Invalid number.")
        }
    }
    
    open fun showItems() {
        if(items.isEmpty()) {
            println("Empty shopping cart.")
        } else {
            println("\n=== Shopping Cart List ===")
            
            items.forEachIndexed {
                index, product -> println("${index + 1}. ${product.name} - R$${product.price}")
            }
            
            println("Subtotal: R$${calculateTotalWithoutDiscount()}")
            
            if(calculateDiscount() > 0) {
                println("Discount applied: R$${calculateDiscount()}")
            }
            
            println("Total: R$${calculateTotalWithDiscount()}")
        }
    }
    
    protected fun calculateTotalWithoutDiscount(): Double = items.sumOf { it.price }
    
    protected fun calculateDiscount(): Double {
        val total = calculateTotalWithoutDiscount()
        
        return if(total > 1000) total * 0.10 else 0.0
    }
    
    fun calculateTotalWithDiscount(): Double {
        val total = calculateTotalWithoutDiscount()
        
        return total - calculateDiscount()
    }
}

class Order(val client: Client) : ShoppingCart() {
    private val idOrder: String = UUID.randomUUID().toString()
    
    fun finishOrder() {
        if(items.isEmpty()) {
            println("It's not possible to finish an empty order!")
            
            return
        }
        
        println("\n=== Finished Order ===")
        println("Order ID: $idOrder")
        println("Client: ${client.name} (${client.email})")
        
        showItems()
        
        println("Thanks for buying!")
    }
}

class Client(val name: String, val email: String)

fun main() {
    val availableProducts = listOf(
        Product("Mouse Gamer", 120.0)
        Product("Mecanic Keyboard", 250.0)
        Product("Headset", 180.0)
        Product("Monitor 24\"", 900.0)
        Product("Gamer Chair", 1100.0)
        Product("Webcam Full HD", 300.0)
    )
    
    println("=== Welcome to Virtual Store ===")
    
    print("Name: ")
    val name = readLine() ?: "Client"
    
    print("E-mail:")
    val email = readLine() ?: "email@example.com"
    
    val client = Client(name, email)
    val order = Order(client)
    
    var keepRunning = true
    
    while(keepRunning) {
        println(
            """
            
            === MENU ===
            1 - Show products
            2 - Add product to shopping cart
            3 - Remove product from shopping cart
            4 - Show shopping cart
            5 - Finish order
            6 - Exit
            """.trimIndent()
        )
        
        print(" > ")
        when(readLine()) {
            "1" -> {
                println("\n=== Available Products ===")
                
                availableProducts.forEachIndexed {
                    index, product -> println("${index + 1}. ${product.name} - R$${product.price}")
                }
            }
            "2" -> {
                println("\nChoose your product:")
                
                availableProducts.forEachIndexed {
                    index, product -> println("${index + 1}. ${product.name} - R$${product.price}")
                }
                
                print(" > ")
                val option = readLine()?.toIntOrNull()
                
                if(option != null && option in 1..availableProducts.size) {
                    order.addProduct(availableProducts[option - 1])
                } else {
                    println("Invalid option.")
                }
            }
            "3" -> {
                order.showItems()
                
                if(order.calculateTotalWithDiscount() > 0) {
                    print("Enter the number of the product to remove: ")
                    val index = readLine()?.toIntOrNull(index)
                    
                    if(index != null) order.removeProduct(index)
                }
            }
            "4" -> order.showItems()
            "5" -> {
                order.finishOrder()
                
                keepRunning = false
            }
            "6" -> {
                println("Exiting...")
                
                keepRunning = false
            }
            else -> println("Invalid option.")
        }
    }
}
