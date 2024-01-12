package kids.preschool.doantotnghiep.ui.home.test

import android.content.Context
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.models.TestAnswerEntity

class TestCount {
	private val list1 : List<TestAnswerEntity> = listOf(
		TestAnswerEntity("Câu hỏi 1: Is this  _ pencil?","C. a",1),
		TestAnswerEntity("Câu hỏi 2: Choose the odd one out.","D. teacher",2),
		TestAnswerEntity("Câu hỏi 3: Loo… at the dog.","B. k",3),
		TestAnswerEntity("Câu hỏi 4: Look at the picture and choose the correct answer.","C. a pig",4),
		TestAnswerEntity("Câu hỏi 5: Choose the INCORRECT sentence.","D. Is that your best friend.",5),
		TestAnswerEntity("Câu hỏi 6: Choose the best answer: ","B. Sit down",6),
		TestAnswerEntity("Câu hỏi 7: Choose the odd one out.","C. See you soon",7),
		TestAnswerEntity("Câu hỏi 8: Choose the CORRECT sentence.", "C. What is your name?",8),
		TestAnswerEntity("Câu hỏi 9: _ _ _ _ _ _ _, Miss Nga.    – Goodbye, Nam.","B. Goodbye",9),
		TestAnswerEntity("Câu hỏi 10: Is that Lucy?","C. No, it isn’t. It’s Ben.",10),
		TestAnswerEntity("Câu hỏi 11: What is it?","A. It’s a clock",11),
		TestAnswerEntity("Câu hỏi 12: Choose the odd one out.", "B. nine",12),
		TestAnswerEntity("Câu hỏi 13: Choose the CORRECT sentence.","B. Goodbye, see you soon.",13),
		TestAnswerEntity("Câu hỏi 14: _______ three books. ","C. There are",14),
		TestAnswerEntity("Câu hỏi 15: Six, _ _ _ _ _, eight, nine, ten.","B. one",15),
		TestAnswerEntity("Câu hỏi 16: eight - _____ = five","C. three",16),
		TestAnswerEntity("Câu hỏi 17: ........g x 4 = 860g. The missing number is:","D. 215",17),
		TestAnswerEntity(
			"Câu hỏi 18: Three years ago, mothers age is eight times as old as sons age. Mother is thirty five years old now. How old is son now?", "C. 7",18),
		TestAnswerEntity(
			"Câu hỏi 19: Mary has five packs of pencils. There are 9 pencils in each pack. John has 16 pencils. How many pencils do they have in all?", "D. 61",19),
		TestAnswerEntity("Câu hỏi 20: Find X such that 7 x X = 42","A. 6",20),
		)
	private val list2 : List<TestAnswerEntity> = listOf(
		TestAnswerEntity("Câu hỏi 1: Choose the correct answer: This is number ___.", "B. eight",1),
		TestAnswerEntity("Câu hỏi 2: What color is your skirt?","C. It’s white.",2),
		TestAnswerEntity("Câu hỏi 3: Choose the odd one out:","C. name",3),
		TestAnswerEntity(
			"Câu hỏi 4: That is my _ _ _ _ _. It’s red and blue.","A. train",4),
		TestAnswerEntity("Câu hỏi 5: Choose the letter(s) make a correct word: SEV_N","B. E",5),
		TestAnswerEntity(
			"Câu hỏi 6: This is my brother and this is ….. bike.","B. his",6),
		TestAnswerEntity("Câu hỏi 7: Choose the best answer:","A. Stand Up",7),
		TestAnswerEntity("Câu hỏi 8: Dad is … and son is … .","A. tall – short",8),
		TestAnswerEntity("Câu hỏi 9: Is your school ____?","D. big",9),
		TestAnswerEntity("Câu hỏi 10: Choose the odd one out:","A. shool",10),
		TestAnswerEntity("Câu hỏi 11: A: __________ - B: I’m ten.","C. How old are you?",11),
		TestAnswerEntity("Câu hỏi 12: Are they your friends?","A. No, they are.",12),
		TestAnswerEntity("Câu hỏi 13: Choose the odd one out: ","C. Table",13),
		TestAnswerEntity("Câu hỏi 14: Hello, how are you? – I am ___?","C. fine",14),
		TestAnswerEntity("Câu hỏi 15: What animal has four legs?","A. tiger",15),
		TestAnswerEntity("Câu hỏi 16: Calculate: 123 + 456","B. 579",16),
		TestAnswerEntity("Câu hỏi 17: Each bag has 7 potatoes. How many potatoes are there in the nine bags?", "C. 63",17),
		TestAnswerEntity("Câu hỏi 18: five + ____ = six","B. one",18),
		TestAnswerEntity("Câu hỏi 19: Which number makes the equation true? 163 + 100 = ........... + 102","A. 161",19),
		TestAnswerEntity("Câu hỏi 20: The product of y and 9 is a two-digit number that has the digit 8 in the tens place. When y is divided by 9, the remainder is ................",
			"D. 8",20),
	)
	fun scoreTest(useAnswer : List<TestAnswerEntity>, name : String, context : Context) :Int {
		var score  = 0
		val correctAnswer = listAnswerTest(name,context)
		for (i in useAnswer.indices){
			val useAnswerTest = useAnswer[i]
			val correctAnswerTest = correctAnswer.find { it.position == useAnswerTest.position  && it.name == useAnswerTest.name}

			if (correctAnswerTest != null && useAnswerTest.answer == correctAnswerTest.answer) {
				score += 5
			}
		}
		return score
	}
	private fun listAnswerTest(name : String, context : Context) : List<TestAnswerEntity> {
		return when(name){
			context.resources.getString(R.string.test1) -> list1
			context.resources.getString(R.string.test2) -> list2
			context.resources.getString(R.string.test3) -> emptyList()
			context.resources.getString(R.string.test4) -> emptyList()
			context.resources.getString(R.string.test5) -> emptyList()
			context.resources.getString(R.string.test6) -> emptyList()
			context.resources.getString(R.string.test7) -> emptyList()
			context.resources.getString(R.string.test8) -> emptyList()
			context.resources.getString(R.string.test9) -> emptyList()
			context.resources.getString(R.string.test10) -> emptyList()
			else ->emptyList()
		}
	}

}