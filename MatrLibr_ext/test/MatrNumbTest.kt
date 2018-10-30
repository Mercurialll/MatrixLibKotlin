import org.junit.Assert
import org.junit.Assert.*
import kotlin.test.assertEquals

class MatrNumbTest {

    @org.junit.Test
    fun setSize_getSize() {
        println("------CheckForDOUBLE--setSize---->>>>>>>>>>>>>>>>>>>>")
        var new3_4d = MatrNumb(arrayOf(1.0, 1.0, 1.0, 1.0, 1.0, 34.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), mdim = 1, ndim = 12)
        new3_4d.show()
        assertEquals(1, new3_4d.getSize()[0], message="WRONG")
        assertEquals(12, new3_4d.getSize()[1], message="WRONG")
        assertEquals(12, new3_4d.getSize()[2], message="WRONG")
        new3_4d.setSize(3,4)
        new3_4d.show()
        assertEquals(3, new3_4d.getSize()[0], message="WRONG")
        assertEquals(4, new3_4d.getSize()[1], message="WRONG")
        assertEquals(12, new3_4d.getSize()[2], message="WRONG")
    }

    @org.junit.Test
    fun setNewValues() {
        println("------CheckForFLOAT---all new values in matrix--->>>>>>>>>>>>>>>>>>>>")

        var dop1: Float = 1.toFloat()
        val dop2: Float = 25.toFloat()
        var new3_4f = MatrNumb(arrayOf(dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1), mdim = 3, ndim = 4)
        new3_4f.show()
        dop1 = dop1 * dop2
        new3_4f.setNewValues(arrayOf(dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1,
                dop1, dop1), 4, 4 )
        new3_4f.show()
        for ((_, value) in new3_4f.arr.withIndex()) {
            assertEquals(25.toFloat(), value, message="WRONG")
        }
    }

    @org.junit.Test
    fun get() {
        println("------CheckForDOUBLE--GET---->>>>>>>>>>>>>>>>>>>>")
        var new3_4d = MatrNumb(arrayOf(1.0, 1.0, 1.0, 1.0, 1.0, 34.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), mdim = 1, ndim = 12)
        new3_4d.show()
        assertEquals(34.toDouble(), new3_4d[0, 5], message="WRONG")
    }

    @org.junit.Test
    fun set() {
        println("------CheckForDOUBLE--SET---->>>>>>>>>>>>>>>>>>>>")
        var new3_4d = MatrNumb(arrayOf(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), mdim = 1, ndim = 12)
        new3_4d[0, 10] = 2500.toDouble()
        new3_4d.show()

        assertEquals(2500.toDouble(), new3_4d[0, 10], message="WRONG")
        //println("")
    }

    @org.junit.Test
    fun times() {
        println("------CheckForDOUBLE--Vector*vector=scalar---->>>>>>>>>>>>>>>>>>>>")
        val new3_4d = MatrNumb(arrayOf(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), mdim = 1, ndim = 12)
        val new4_4d = MatrNumb(arrayOf(2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0), mdim = 12, ndim = 1)
        var new4_4dD : MatrNumb
        new4_4dD = new3_4d*new4_4d
        new4_4dD.show()

        assertEquals(24.toDouble(), new4_4dD.arr[0], message="WRONG")
        //println("")
    }

    @org.junit.Test
    fun times1() {
        println("------CheckForFLOAT---Matrix*number--->>>>>>>>>>>>>>>>>>>>")

        val dop1: Float = 1.toFloat()
        val dop2: Float = 5.toFloat()
        var new3_4f = MatrNumb(arrayOf(dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1), mdim = 3, ndim = 4)
        new3_4f = new3_4f*dop2
        new3_4f.show()
        for ((_, value) in new3_4f.arr.withIndex()) {
            assertEquals(5.toFloat(), value, message="WRONG")
        }
    }

    @org.junit.Test
    fun plus() {
        println("------CheckForINT---matrix PLUS matrix--->>>>>>>>>>>>>>>>>>>>")

        val dop1: Int = 1 //.toFloat()
        val dop2: Int = 2 //.toFloat()
        val new3_4f = MatrNumb(arrayOf(dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1), mdim = 3, ndim = 4)
        val new3_4fD = MatrNumb(arrayOf(dop2, dop2, dop2, dop2, dop2, dop2, dop2, dop2, dop2, dop2, dop2, dop2), mdim = 3, ndim = 4)
        var new3_4fT : MatrNumb
        new3_4fT = new3_4f+new3_4fD
        println("--res-->>>>>>>>>>>>>>>>>>>>")
        new3_4fT.show()
        for ((_, value) in new3_4fT.arr.withIndex()) {
            assertEquals(3, value, message="WRONG")
        }
    }

    @org.junit.Test
    fun minus() {
        println("------CheckForINT--matrix MINUS matrix--->>>>>>>>>>>>>>>>>>>>")

        val dop1: Int = 100//.toFloat()
        val dop2: Int = 200 //.toFloat()
        val new3_4f = MatrNumb(arrayOf(dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1, dop1), mdim = 3, ndim = 4)
        val new3_4fD = MatrNumb(arrayOf(dop2, dop2, dop2, dop2, dop2, dop2, dop2, dop2, dop2, dop2, dop2, dop2), mdim = 3, ndim = 4)
        var new3_4fT : MatrNumb
        new3_4fT = new3_4f - new3_4fD
        new3_4fT.show()
        for ((_, value) in new3_4fT.arr.withIndex()) {
            assertEquals(-100, value, message="WRONG")
        }
    }

}