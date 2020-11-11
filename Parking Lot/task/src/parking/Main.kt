package parking

fun main() {
    
    var parkingLot: ParkingLot? = null
    var command = readLine()!!
    while (command != "exit") {
        
        val commands = command.split(" ")
        
        if (commands[0] == "create") {
            parkingLot = ParkingLot(commands[1].toInt())
            println("Created a parking lot with ${commands[1].toInt()} spots.")
        } else if (parkingLot == null) {
            println("Sorry, a parking lot has not been created.")
        } else if (commands[0] == "park") {
            val carToPark = Car(commands[1], commands[2])
            val position = parkingLot.parkCar(carToPark)
            if (position != null) {
                println("${commands[2]} car parked in spot $position.")
            } else {
                    println("Sorry, the parking lot is full.")
            }
        } else if (commands[0] == "leave") {
           val spotId = commands[1].toInt()
           println(if (parkingLot.leave(spotId)) "Spot $spotId is free." else "There is no car in spot $spotId.")
        } else if (commands[0] == "status") {
            print(parkingLot.getStatus())
        } else if (commands[0] == "reg_by_color") {
            println(parkingLot.regByColor(commands[1])?.joinToString()
                    ?: "No cars with color ${commands[1]} were found.")
        } else if (commands[0] == "spot_by_color") {
            println(parkingLot.spotByColor(commands[1])?.joinToString()
                    ?: "No cars with color ${commands[1]} were found.")
        } else if (commands[0] == "spot_by_reg") {
            println(parkingLot.spotByReg(commands[1])
                    ?: "No cars with registration number ${commands[1]} were found.")
        }
        
        command = readLine()!!
        
    }
    
}

data class Spot(val id: Int, var car: Car? = null)

class ParkingLot(size: Int) {
    
    private val spots = mutableListOf<Spot>()
    
    init {
        for (i in 1..size) {
            spots.add(Spot(i))
        }
    }
    
    fun parkCar(car: Car): Int? {
        for (spot in spots) {
            if (spot.car == null) {
                spot.car = car
                return spot.id
            }
        }
        
        return null
    }
    
    fun leave(spotId: Int): Boolean {
        val spotToLeave = spots[spotId - 1]
        return if (spotToLeave.car != null) {
            spotToLeave.car = null
            true
        } else false
    }
    
    fun getStatus(): String {
        var result = ""
        for (spot in spots) {
            if (spot.car != null) {
                result += "${spot.id} ${spot.car!!.registrationNumber} ${spot.car!!.color}\n"
            }
        }
        return if (result.isEmpty()) "Parking lot is empty.\n" else result
    }
    
    fun regByColor(color: String): List<String>? {
        val result = mutableListOf<String>()
        for (spot in spots) {
            if (spot.car != null && spot.car!!.color.toLowerCase() ==
                    color.toLowerCase()) result.add(spot.car!!.registrationNumber)
        }
        return if (result.isEmpty()) null else result
    }
    
    fun spotByColor(color: String): List<Int>? {
        val result = mutableListOf<Int>()
        for (spot in spots) {
            if (spot.car != null && spot.car!!.color.toLowerCase() ==
                    color.toLowerCase()) result.add(spot.id)
        }
        return if (result.isEmpty()) null else result
    }
    
    fun spotByReg(registrationNumber: String): Int? {
        for (spot in spots) {
            if (spot.car != null &&
                    spot.car!!.registrationNumber == registrationNumber) return spot.id
        }
        return null
    }
    
}

data class Car(val registrationNumber: String, val color: String)
