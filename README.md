# MatrixLibKotlin
Library for simple matrix operations

    // get array of sizes : (m,n,l) m = rows , n = columns, l=m*n (quantity of numbers in matrix)
    fun getSize(): IntArray

    // set new sizes for matrix
    fun setSize(m : Int, n : Int)

    //  get value[i,j] from matrix
    operator fun get(i: Int, j: Int): Number 

    //  set new value[i,j] for matrix
    operator fun set(i: Int, j: Int, value: Number)

    //  set all new values for matrix
    fun setNewValues(values: Array<Number>, mdim: Int, ndim: Int)

    // show Matrix in Matrix form
    fun show()

    // multiplying Matrix by Number
    operator fun times(numb: Number): MatrNumb

    // scalar multiplying Matrix by Matrix
    operator fun times(matr2: MatrNumb): MatrNumb
 
    // plus function Matrix by Matrix
    operator fun plus(matr2: MatrNumb): MatrNumb

    // minus function Matrix by Matrix
    operator fun minus(matr2: MatrNumb): MatrNumb
   
