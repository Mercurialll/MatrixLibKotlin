object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("------DOUBLE------>>>>>>>>>>>>>>>>>>>>")
        val new3_4d = MatrNumb(arrayOf(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), mdim = 3, ndim = 4)
        val new4_4d = MatrNumb(arrayOf(2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0), mdim = 4, ndim = 4)

        var new4_4dD: MatrNumb
        new4_4dD = new3_4d * new4_4d
        new4_4dD.show()
    }
}