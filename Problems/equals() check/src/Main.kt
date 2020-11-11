import java.util.Scanner

data class Client(val name: String, val age: Int, val balance: Int) {
    
    override fun equals(other: Any?): Boolean {
        if (other is Client && this.name == other.name && this.age == other.age) return true
        return false
    }
    
}

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val client1 = Client(input.next(), input.nextInt(), input.nextInt())
    val client2 = Client(input.next(), input.nextInt(), input.nextInt())
    
    print(client1 == client2)
}