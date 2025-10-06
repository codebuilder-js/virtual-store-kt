data class Product(
    val name: String,
    val price: Double
)

open class ShoppingCart {
    protected val items = mutableListOf<Product>()
    
    open fun addProduct(product: Product) {
        items.add(product)
        println("${product.name} added on shopping cart.")
    }
    
    open fun showItems() {
        if(items.isEmpty()) {
            println("Empty shopping cart!")
        } else {
            println("\n=== Shopping Cart ===")
            
            items.forEachIndexed {
                index, product -> println("${index + 1}. ${product.name} - R$${product.price}")
            }
            
            println("Total: R$${calculateTotal()}")
        }
    }
    
    fun calculateTotal(): Double {
        return items.sumOf { it.price }
    }
}

class PurchaseOrder(val client: Client) : ShoppingCart() {
    fun finishOrder() {
        println("\n === Purchase Order Finished ===")
        println("Client: ${client.name} (${client.email})")
        
        showItems()
        
        println("Thanks for the purchase!\n")
    }
}

class Client(val name: String, val email: String)

fun main() {
    val availableProducts = listOf(
        Product("Mouse Gamer", 120.0),
        Product("Mecanic Keyboard", 250.0),
        Product("Headset", 180.0),
        Product("Monitor 24\"", 900.0),
        Product("Gamer Chair", 1100.0)
    )
    
    println("=== Welcome to the Virtual Store ===")
    print("Enter your name: ")
    val name = readLine() ?: "Client"
    print("Enter your e-mail: ")
    val email = readLine() ?: "email@example.com"
    
    val client = Client(name, email)
    val order = PurchaseOrder(client)
    
    var keepRunning = true
    
    while(keepRunning) {
        println(
            """
            
            === MENU ===
            1 - Show products
            2 - Add product to the shopping cart
            3 - Show shopping cart
            4 - Finish order
            5 - Exit
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
                println("\nEnter the number of product to add: ")
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
            "3" -> order.showItems()
            "4" -> {
                order.finishOrder()
                keepRunning = false
            }
            "5" -> {
                println("Exiting...")
                keepRunning = false
            }
            else -> println("Invalid option.")
        }
    }
}
