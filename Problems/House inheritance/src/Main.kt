fun main() {
    val rooms = readLine()!!.toInt()
    val price = readLine()!!.toInt()
    val house = House(rooms, price)
    println(totalPrice(house))
}

data class House(val rooms: Int, val price: Int)

fun totalPrice(house: House): Int {
    var price = when {
        house.rooms <= 1 -> house.price
        house.rooms in 2..3 -> (house.price * 1.2).toInt()
        house.rooms == 4 -> (house.price * 1.25).toInt()
        house.rooms in 5..7 -> (house.price * 1.4).toInt()
        else -> (house.price * 1.6).toInt()
    }
    
    when {
        price < 0 -> price = 0
        price > 1_000_000 -> price = 1_000_000
    }
    
    return price
}