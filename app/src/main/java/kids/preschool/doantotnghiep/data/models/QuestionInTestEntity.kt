package kids.preschool.doantotnghiep.data.models

data class QuestionInTestEntity (
	val question : String,
	val image : Int = 0,
	val list : List<AnswerEntity>
)