case class Queen(row: Int, column: Int)

def isSafe(queen: Queen, others: Seq[Queen]): Boolean =
  others.forall(!isAttacked(queen, _))

def isAttacked(q1: Queen, q2: Queen): Boolean = {
  q1.row == q2.row ||
  q1.column == q2.column ||
  (q2.row - q1.row).abs == (q2.column - q1.column).abs
}

def queens(n: Int): Seq[Seq[Queen]] = {

  def placeQueens(row: Int): Seq[Seq[Queen]] = {
    if (row == 0) {
      Seq(Seq())
    } else {
      for {
        queens <- placeQueens(row - 1)
        column <- 1 to n
        queen = Queen(row, column) if isSafe(queen, queens)
      } yield queens :+ queen

    }
  }
  placeQueens(n)
}


val allSolutions = queens(8)
println(s"Number of solutions: ${allSolutions.size}")
allSolutions.foreach(solution => println(s"Solution: $solution"))
