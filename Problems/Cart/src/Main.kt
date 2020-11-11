fun main() {
    val productType = readLine()!!
    val price = readLine()!!.toInt()
    val product = Product(price, productType)
    println(totalPrice(product))
}

data class Product(val price: Int, val type: String)

fun totalPrice(product: Product): Int {
    return when (product.type) {
        "headphones" -> (product.price * 1.11).toInt()
        "smartphone" -> (product.price * 1.15).toInt()
        "tv" -> (product.price * 1.17).toInt()
        "laptop" -> (product.price * 1.19).toInt()
        else -> product.price
    }
}