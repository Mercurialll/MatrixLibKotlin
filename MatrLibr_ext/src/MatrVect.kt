// import jdk.nashorn.api.tree.ArrayAccessTree
// import javax.management.openmbean.ArrayType
import kotlin.Number

class MatrNumb(values: Array<Number>, val mdim: Int, val ndim: Int){

    // m = rows
    // n = columns

    var arr = values //arrayOf(values)
    private var m: Int = mdim
    private var n: Int = ndim
    private var len: Int = arr.size
    val type1: String? = arr[0].javaClass.kotlin.qualifiedName

    init{
        if (len != m * n){
            throw IllegalArgumentException("size of array mismatches a quantity of numbers : $m * $n != $len \n" +
                    "set new M(rows) and N(columns) via using setSize(m, n)")
        }
        else{

        }
    }

    // get array of sizes : (m,n,l) m = rows , n = columns, l=m*n (quantity of numbers in matrix)
    fun getSize(): IntArray{
        val sizes = intArrayOf(m, n, len)
        return(sizes)
    }

    // set new sizes for matrix
    fun setSize(m : Int, n : Int){
        if (m * n == len){
            this.m = m
            this.n = n
        } else {
            throw IllegalArgumentException("size of array mismatches a quantity of numbers : $m * $n != $len, check N, M")
        }
    }

    //  get value[i,j] from matrix
    operator fun get(i: Int, j: Int): Number {
        if ((i >= 0) && (i < m) && (j >= 0) && (j < n)) {
            return (arr[i * n + j])
        } else {
            throw IllegalArgumentException("Out of matrix bounds [$m * $n] , where i=$i, j=$j")
        }
    }

    //  set new value[i,j] for matrix
    operator fun set(i: Int, j: Int, value: Number){
        val type2 = value.javaClass.kotlin.qualifiedName
        if(type1 != type2){
            throw IllegalArgumentException("Matrix and new value have different types: $type1 , $type2 ")
        } else {
            if ((i >= 0)&&(i < m)&&(j >= 0)&&(j < n)) {
                arr[i * n + j] = value
            }else{
                throw IllegalArgumentException("Out of matrix bounds [$m * $n] , where i=$i, j=$j")
            }
        }
    }

    //  set all new values for matrix
    fun setNewValues(values: Array<Number>, mdim: Int, ndim: Int){
        if (values.size != ndim * mdim) {
            throw IllegalArgumentException("size of array mismatches a quantity of numbers : $m * $n != $len, check N, M")
        } else {
            val type2 = values[0].javaClass.kotlin.qualifiedName
            if(type1 != type2){
                throw IllegalArgumentException("Matrix and new values have different types: $type1 , $type2 ")
            }else{
                arr = values
                len = arr.size
                m = mdim
                n = ndim
            }
        }
    }

    // show Matrix in Matrix form
    fun show(){
        if (len == m * n){
            for ((index, value) in arr.withIndex()) {
                // println("the element at $index is $value")
                print("$value ")
                if (((index+1) % n == 0)&&(index != 0)) println("")
            }
        } else {
            throw IllegalArgumentException("size of array mismatches a quantity of numbers : $m * $n != $len \n" +
                    "set new M(rows) and N(columns) via using setSize(m, n)")
        }
    }

    // multiplying Matrix by Number
    operator fun times(numb: Number): MatrNumb{
        val type1: String? = this.arr[0].javaClass.kotlin.qualifiedName
        val n: Int = this.n
        val m: Int = this.m
        val len: Int = this.len
        var arr3_ = Array<Number>(m * n, {0})
        var matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)
        val type2 = numb.javaClass.kotlin.qualifiedName

        if (type1!=type2) {
            // println("wrong types : $type1 , $type2")
            throw IllegalArgumentException("Matrix and Number have different types: $type1 , $type2 ")
        }else{
            if (type1 == "kotlin.Double") {
                arr3_ = Array(size = m * n, init = { 0.0.toDouble() })
                // matrix type convert :
                var dop1s: String;
                var dop1d: Double
                var arr1d = DoubleArray(n * m)
                for (i: Int in 0..(len - 1)) {
                    dop1s = this.arr[i].toString(); dop1d = dop1s.toDouble()
                    arr1d[i] = dop1d
                }
                // multiplying :
                for (i: Int in 0..(len - 1)) {
                    arr3_[i] = arr1d[i] * numb.toDouble()
                }
                matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)
            } else {
                if (type1 == "kotlin.Int") {
                    arr3_ = Array(size = m * n, init = { 0 })
                    // matrix type convert :
                    var dop1s: String;
                    var dop1i: Int
                    var arr1i = IntArray(n * m)
                    for (i: Int in 0..(len - 1)) {
                        dop1s = this.arr[i].toString(); dop1i = dop1s.toInt()
                        arr1i[i] = dop1i
                    }
                    // multiplying :
                    for (i: Int in 0..(len - 1)) {
                        arr3_[i] = arr1i[i] * numb.toInt()
                    }
                    matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)

                } else {
                    if (type1 == "kotlin.Float") {
                        arr3_ = Array(size = m * n, init = { 0.toFloat() })
                        // matrix type convert :
                        var dop1s: String;
                        var dop1f: Float
                        var arr1f = FloatArray(n * m)
                        for (i: Int in 0..(len - 1)) {
                            dop1s = this.arr[i].toString(); dop1f = dop1s.toFloat()
                            arr1f[i] = dop1f
                        }
                        // multiplying :
                        for (i: Int in 0..(len - 1)) {
                            arr3_[i] = arr1f[i] * numb.toFloat()
                        }
                        matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)
                    }
                }
            }
        }
        return (matr3)
    }

    // scalar multiplying Matrix by Matrix
    operator fun times(matr2: MatrNumb): MatrNumb{
        val type1: String? = this.arr[0].javaClass.kotlin.qualifiedName
        val n: Int = this.n
        val m: Int = this.m
        val len: Int = this.len

        val type2: String? = matr2.arr[0].javaClass.kotlin.qualifiedName
        val n1: Int = matr2.n
        val m1: Int = matr2.m
        val len1: Int = matr2.len

        var arr3_ = Array<Number>(m*n1, {0})
        var matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n1)

        if (type1!=type2){
            // println("wrong types : $type1 , $type2")
            throw IllegalArgumentException("Matrices have different types: $type1 , $type2 ")
        }else{
            if(n!=matr2.m){
                throw IllegalArgumentException("Matrices not match for multiplying: [$m * $n] , [$m1 * $n1]")
                // println("wrong dimensions: $n * $m , $n1 * $m1")
            }
            else{
                if (type1 == "kotlin.Double"){
                    arr3_ = Array(size = m*matr2.n, init = {0.0.toDouble()} )
                    // multiplying :
                    var dop1s: String; var dop1d: Double
                    var dop2s: String; var dop2d: Double
                    var arr1d = DoubleArray(n*m)
                    var arr2d = DoubleArray(n1*m1)
                    for (i: Int in 0..(len-1)){
                        dop1s = this.arr[i].toString();  dop1d = dop1s.toDouble()
                        arr1d[i] = dop1d
                    }
                    for (i: Int in 0..(len1-1)){
                        dop2s = matr2.arr[i].toString();  dop2d = dop2s.toDouble()
                        arr2d[i] = dop2d
                    }
                    // multiplying :
                    for (i: Int in 0..(m-1)){
                        for (j: Int in 0..(n1-1)){
                            var s: Double = 0.0
                            for (k: Int in 0..(n-1)){
                                s += arr1d[k+i*n]*arr2d[k*n1+j]
                            }
                            arr3_[i*n1+j] = s
                        }
                    }
                    matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n1)

                }else {
                    if (type1 == "kotlin.Int") {
                        arr3_ = Array(size = m * matr2.n, init = { 0 })
                        // matrix type convert :
                        var dop1s: String; var dop1i: Int
                        var dop2s: String; var dop2i: Int
                        var arr1i = IntArray(n*m)
                        var arr2i = IntArray(n1*m1)
                        for (i: Int in 0..(len-1)){
                            dop1s = this.arr[i].toString();  dop1i = dop1s.toInt()
                            arr1i[i] = dop1i
                        }
                        for (i: Int in 0..(len1-1)){
                            dop2s = matr2.arr[i].toString();  dop2i = dop2s.toInt()
                            arr2i[i] = dop2i
                        }
                        // multiplying :
                        for (i: Int in 0..(m-1)){
                            for (j: Int in 0..(n1-1)){
                                var s: Int = 0
                                for (k: Int in 0..(n-1)){
                                    s += arr1i[k+i*n]*arr2i[k*n1+j]
                                }
                                arr3_[i*n1+j] = s
                            }
                        }
                        matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n1)

                    } else {
                        if (type1 == "kotlin.Float") {
                            arr3_ = Array(size = m * matr2.n, init = {0.toFloat()})
                            // matrix type convert :
                            var dop1s: String; var dop1f: Float
                            var dop2s: String; var dop2f: Float
                            var arr1f = FloatArray(n*m)
                            var arr2f = FloatArray(n1*m1)
                            for (i: Int in 0..(len-1)){
                                dop1s = this.arr[i].toString();  dop1f = dop1s.toFloat()
                                arr1f[i] = dop1f
                            }
                            for (i: Int in 0..(len1-1)){
                                dop2s = matr2.arr[i].toString();  dop2f = dop2s.toFloat()
                                arr2f[i] = dop2f
                            }
                            // multiplying :
                            for (i: Int in 0..(m-1)){
                                for (j: Int in 0..(n1-1)){
                                    var s: Float = 0.toFloat()
                                    for (k: Int in 0..(n-1)){
                                        s += arr1f[k+i*n]*arr2f[k*n1+j]
                                    }
                                    arr3_[i*n1+j] = s
                                }
                            }
                            matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n1)


                        }
                    }
                }

            }
        }
    return (matr3)
    }

    // plus function Matrix by Matrix
    operator fun plus(matr2: MatrNumb): MatrNumb{
        val type1: String? = this.arr[0].javaClass.kotlin.qualifiedName
        val n: Int = this.n
        val m: Int = this.m
        val len: Int = this.len

        val type2: String? = matr2.arr[0].javaClass.kotlin.qualifiedName
        val n1: Int = matr2.n
        val m1: Int = matr2.m
        val len1: Int = matr2.len

        var arr3_ = Array<Number>(m * n, {0})
        var matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)

        if (type1 != type2){
            // println("wrong types : $type1 , $type2")
            throw IllegalArgumentException("Matrices have different types: $type1 , $type2 ")
        }else{
            if ((n == n1)and(m == m1)){
                if (type1 == "kotlin.Double"){
                    arr3_ = Array(size = m*n, init = {0.0.toDouble()} )
                    // matrix type convert :
                    var dop1s: String; var dop1d: Double
                    var dop2s: String; var dop2d: Double
                    var arr1d = DoubleArray(n*m)
                    var arr2d = DoubleArray(n1*m1)
                    for (i: Int in 0..(len-1)){
                        dop1s = this.arr[i].toString();  dop1d = dop1s.toDouble()
                        arr1d[i] = dop1d
                    }
                    for (i: Int in 0..(len1-1)){
                        dop2s = matr2.arr[i].toString();  dop2d = dop2s.toDouble()
                        arr2d[i] = dop2d
                    }
                    // plus :
                    for (i: Int in 0..(len-1)){
                        arr3_[i] = arr1d[i] + arr2d[i]
                    }
                    matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)

                }else{
                    if (type1 == "kotlin.Int") {
                        arr3_ = Array(size = m * n, init = { 0 })
                        // matrix type convert :
                        var dop1s: String; var dop1i: Int
                        var dop2s: String; var dop2i: Int
                        var arr1i = IntArray(m * n)
                        var arr2i = IntArray(m1 * n1)
                        for (i: Int in 0..(len-1)){
                            dop1s = this.arr[i].toString();  dop1i = dop1s.toInt()
                            arr1i[i] = dop1i
                        }
                        for (i: Int in 0..(len1-1)){
                            dop2s = matr2.arr[i].toString();  dop2i = dop2s.toInt()
                            arr2i[i] = dop2i
                        }
                        // plus :
                        for (i: Int in 0..(len-1)){
                            arr3_[i] = arr1i[i] + arr2i[i]
                        }
                        matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)

                    } else {
                        if (type1 == "kotlin.Float") {
                            arr3_ = Array(size = m * n, init = {0.toFloat()})
                            // matrix type convert :
                            var dop1s: String; var dop1f: Float
                            var dop2s: String; var dop2f: Float
                            var arr1f = FloatArray(m * n)
                            var arr2f = FloatArray(m1 * n1)
                            for (i: Int in 0..(len-1)){
                                dop1s = this.arr[i].toString();  dop1f = dop1s.toFloat()
                                arr1f[i] = dop1f
                            }
                            for (i: Int in 0..(len1-1)){
                                dop2s = matr2.arr[i].toString();  dop2f = dop2s.toFloat()
                                arr2f[i] = dop2f
                            }
                            // plus :
                            for (i: Int in 0..(len-1)){
                                arr3_[i] = arr1f[i] + arr2f[i]
                            }
                            matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)
                        }
                    }
                }
            }else{
                // println("wrong dimensions: $n * $m , $n1 * $m1")
                throw IllegalArgumentException("Matrices not match for plus function: [$m * $n] , [$m1 * $n1]")
            }
        }
        return (matr3)
    }

    // minus function Matrix by Matrix
    operator fun minus(matr2: MatrNumb): MatrNumb{
        val type1: String? = this.arr[0].javaClass.kotlin.qualifiedName
        val n: Int = this.n
        val m: Int = this.m
        val len: Int = this.len

        val type2: String? = matr2.arr[0].javaClass.kotlin.qualifiedName
        val n1: Int = matr2.n
        val m1: Int = matr2.m
        val len1: Int = matr2.len

        var arr3_ = Array<Number>(m * n, {0})
        var matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)

        if (type1 != type2){
            // println("wrong types : $type1 , $type2")
            throw IllegalArgumentException("Matrices have different types: $type1 , $type2 ")
        }else{
            if ((n == n1)and(m == m1)){
                if (type1 == "kotlin.Double"){
                    arr3_ = Array(size = m * n, init = {0.0.toDouble()} )
                    // matrix type convert :
                    var dop1s: String; var dop1d: Double
                    var dop2s: String; var dop2d: Double
                    var arr1d = DoubleArray(m * n)
                    var arr2d = DoubleArray(m1 * n1)
                    for (i: Int in 0..(len-1)){
                        dop1s = this.arr[i].toString();  dop1d = dop1s.toDouble()
                        arr1d[i] = dop1d
                    }
                    for (i: Int in 0..(len1-1)){
                        dop2s = matr2.arr[i].toString();  dop2d = dop2s.toDouble()
                        arr2d[i] = dop2d
                    }
                    // plus :
                    for (i: Int in 0..(len-1)){
                        arr3_[i] = arr1d[i] - arr2d[i]
                    }
                    matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)

                }else{
                    if (type1 == "kotlin.Int") {
                        arr3_ = Array(size = m * n, init = { 0 })
                        // matrix type convert :
                        var dop1s: String; var dop1i: Int
                        var dop2s: String; var dop2i: Int
                        var arr1i = IntArray(m * n)
                        var arr2i = IntArray(m1 * n1)
                        for (i: Int in 0..(len-1)){
                            dop1s = this.arr[i].toString();  dop1i = dop1s.toInt()
                            arr1i[i] = dop1i
                        }
                        for (i: Int in 0..(len1-1)){
                            dop2s = matr2.arr[i].toString();  dop2i = dop2s.toInt()
                            arr2i[i] = dop2i
                        }
                        // plus :
                        for (i: Int in 0..(len-1)){
                            arr3_[i] = arr1i[i] - arr2i[i]
                        }
                        matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)

                    } else {
                        if (type1 == "kotlin.Float") {
                            arr3_ = Array(size = m * n, init = {0.toFloat()})
                            // matrix type convert :
                            var dop1s: String; var dop1f: Float
                            var dop2s: String; var dop2f: Float
                            var arr1f = FloatArray(m * n)
                            var arr2f = FloatArray(m1 * n1)
                            for (i: Int in 0..(len-1)){
                                dop1s = this.arr[i].toString();  dop1f = dop1s.toFloat()
                                arr1f[i] = dop1f
                            }
                            for (i: Int in 0..(len1-1)){
                                dop2s = matr2.arr[i].toString();  dop2f = dop2s.toFloat()
                                arr2f[i] = dop2f
                            }
                            // plus :
                            for (i: Int in 0..(len-1)){
                                arr3_[i] = arr1f[i] - arr2f[i]
                            }
                            matr3 = MatrNumb(values = arr3_, mdim = m, ndim = n)
                        }
                    }
                }
            }else{
                // println("wrong dimensions: $n * $m , $n1 * $m1")
                throw IllegalArgumentException("Matrices not match for minus function: [$m * $n] , [$m1 * $n1]")
            }
        }
        return (matr3)
    }

}


