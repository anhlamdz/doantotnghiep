package kids.preschool.doantotnghiep.ui.home.test.starttest

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.ScoreCharacterEntity
import kids.preschool.doantotnghiep.data.models.AnswerEntity
import kids.preschool.doantotnghiep.data.models.QuestionInTestEntity
import kids.preschool.doantotnghiep.databinding.ActivityStartTestBinding
import kids.preschool.doantotnghiep.ui.home.test.TestCount
import kids.preschool.doantotnghiep.ui.home.test.TestEndAct
import kids.preschool.doantotnghiep.ui.home.test.starttest.adapter.StartTestAdapter
import kids.preschool.doantotnghiep.ui.home.test.viewmodel.TestViewModel
import kotlin.random.Random

class StartTestAct : BaseActivity<ActivityStartTestBinding>() {
	private lateinit var name : String
	private lateinit var testCount: TestCount
	private lateinit var testViewModel: TestViewModel
	val list1 = listOf(
		QuestionInTestEntity("Câu hỏi 1: Is this  _ pencil?",0, listOf(
			AnswerEntity("A. the"),
			AnswerEntity("B. one"),
			AnswerEntity("C. a"),
			AnswerEntity("D. an")
		)),
		QuestionInTestEntity("Câu hỏi 2: Choose the odd one out.",0, listOf(
			AnswerEntity("A. desk"),
			AnswerEntity("B. chair"),
			AnswerEntity("C. table"),
			AnswerEntity("D. teacher")
		)),
		QuestionInTestEntity("Câu hỏi 3: Loo… at the dog.",0, listOf(
			AnswerEntity("A. t"),
			AnswerEntity("B. k"),
			AnswerEntity("C. c"),
			AnswerEntity("D. b")
		)),
		QuestionInTestEntity("Câu hỏi 4: Look at the picture and choose the correct answer.",R.drawable.pig, listOf(
			AnswerEntity("A. a cat"),
			AnswerEntity("B. a duck"),
			AnswerEntity("C. a pig"),
			AnswerEntity("D. a horse")
		)),
		QuestionInTestEntity("Câu hỏi 5: Choose the INCORRECT sentence.",0,
			listOf(
				AnswerEntity("A. Are there two cats in the living room?"),
				AnswerEntity("B. This is my classmate, Phuong."),
				AnswerEntity("C. I chat with my friends at break time."),
				AnswerEntity("D. Is that your best friend.")
			)),
		QuestionInTestEntity("Câu hỏi 6: Choose the best answer: ",
			R.drawable.sitdown,
			listOf(
				AnswerEntity("A. Come in"),
				AnswerEntity("B. Sit Down"),
				AnswerEntity("C. Stand up"),
				AnswerEntity("D. Go out")
			)),
		QuestionInTestEntity("Câu hỏi 7: Choose the odd one out.",0,
			listOf(
				AnswerEntity("A. Goodbye"),
				AnswerEntity("B. Good morning"),
				AnswerEntity("C. See you soon"),
				AnswerEntity("D. See you again")
			)),
		QuestionInTestEntity(
			"Câu hỏi 8: Choose the CORRECT sentence.",
			0, listOf(
				AnswerEntity("A. What is you name?"),
				AnswerEntity("B. What name are you?"),
				AnswerEntity("C. What is your name?"),
				AnswerEntity("D. What is yours name?")
			)),
		QuestionInTestEntity(
			"Câu hỏi 9: _ _ _ _ _ _ _, Miss Nga.    – Goodbye, Nam.",
			0,
			listOf(
				AnswerEntity("A. Good morning"),
				AnswerEntity("B. Goodbye"),
				AnswerEntity("C. Good night"),
				AnswerEntity("D. Hello")
			)),
		QuestionInTestEntity(
			"Câu hỏi 10: Is that Lucy?",
			0,
			listOf(
				AnswerEntity("A. No, it is. It’s Ben."),
				AnswerEntity("B. No, it isn’t. It Ben."),
				AnswerEntity("C. No, it isn’t. It’s Ben."),
				AnswerEntity("D. Yes, it isn’t. It’s Ben.")
			)),
		QuestionInTestEntity(
			"Câu hỏi 11: What is it?"
			,R.drawable.clock,
			listOf(
				AnswerEntity("A. It’s a clock"),
				AnswerEntity("B. It’s a doll"),
				AnswerEntity("C. It’s a piture"),
				AnswerEntity("D. It’s a teddy bear")
			)),
		QuestionInTestEntity(
			"Câu hỏi 12: Choose the odd one out.",
			0,
			listOf(AnswerEntity("A. hat"), AnswerEntity("B. nine"), AnswerEntity("C. three"), AnswerEntity("D. four"))),
		QuestionInTestEntity(
			"Câu hỏi 13: Choose the CORRECT sentence.",0,
			listOf(
				AnswerEntity("A. Bye, see you son."), AnswerEntity("B. Goodbye, see you soon."),
				AnswerEntity("C. Goodbye, see you son."), AnswerEntity("D. Bye, se you soon.")
			)),
		QuestionInTestEntity(
			"Câu hỏi 14: _______ three books. ",
			0, listOf(AnswerEntity("A. It’s is"), AnswerEntity("B. They are "), AnswerEntity("C. There are"), AnswerEntity("D. It is"))),
		QuestionInTestEntity(
			"Câu hỏi 15: Six, _ _ _ _ _, eight, nine, ten.",
			0,
			listOf(AnswerEntity("A. two"), AnswerEntity("B. one"), AnswerEntity("C. four"), AnswerEntity("D. seven"))),
		QuestionInTestEntity(
			"Câu hỏi 16: eight - _____ = five",
			0,
			listOf(AnswerEntity("A. one"), AnswerEntity("B. four"), AnswerEntity("C. three"), AnswerEntity("D. two"))),
		QuestionInTestEntity("Câu hỏi 17: ........g x 4 = 860g. The missing number is:"
			,0, listOf(AnswerEntity("A. 205"), AnswerEntity("B. 235"), AnswerEntity("C. 225"), AnswerEntity("D. 215"))),
		QuestionInTestEntity(
			"Câu hỏi 18: Three years ago, mothers age is eight times as old as sons age. Mother is thirty five years old now. How old is son now?",
			0,
			listOf(AnswerEntity("A. 4"), AnswerEntity("B. 5"), AnswerEntity("C. 7"), AnswerEntity("D. 3"))),
		QuestionInTestEntity(
			"Câu hỏi 19: Mary has five packs of pencils. There are 9 pencils in each pack. John has 16 pencils. How many pencils do they have in all?",
			0,
			listOf(AnswerEntity("A. 51"), AnswerEntity("B. 56"), AnswerEntity("C. 66"), AnswerEntity("D. 61"))),
		QuestionInTestEntity("Câu hỏi 20: Find X such that 7 x X = 42",0, listOf(
			AnswerEntity("A. 6"),
			AnswerEntity("B. 4"),
			AnswerEntity("C. 7"),
			AnswerEntity("D. 8")
		)),
	)
	val list2 = listOf(
		QuestionInTestEntity(
			"Câu hỏi 1: Choose the correct answer: This is number ___.",
			R.drawable.number_eight,
			listOf(AnswerEntity("A. three"), AnswerEntity("B. eight"), AnswerEntity("C. nine"), AnswerEntity("D. seven"))),
		QuestionInTestEntity(
			"Câu hỏi 2: What colour is your skirt?",0,
			listOf(
				AnswerEntity("A. It’s new."),
				AnswerEntity("B. It’s fine."),
				AnswerEntity("C. It’s white."),
				AnswerEntity("D. It’s two years old.")
			)),
		QuestionInTestEntity(
			"Câu hỏi 3: Choose the odd one out:",0, listOf(
				AnswerEntity("A. Ben"),
				AnswerEntity("B. Lucy"),
				AnswerEntity("C. name"),
				AnswerEntity("D. Mai")
			)),
		QuestionInTestEntity(
			"Câu hỏi 4: That is my _ _ _ _ _. It’s red and blue.",R.drawable.train,
			listOf(AnswerEntity("A. train"), AnswerEntity("B. bike"), AnswerEntity("C. car"), AnswerEntity("D. bus"))),
		QuestionInTestEntity("Câu hỏi 5: Choose the letter(s) make a correct word: SEV_N",0,
			listOf(
				AnswerEntity("A. R"),
				AnswerEntity("B. E"),
				AnswerEntity("C. I"),
				AnswerEntity("D. N")
			)),
		QuestionInTestEntity(
			"Câu hỏi 6: This is my brother and this is ….. bike.",0, listOf(
				AnswerEntity("A. its"),
				AnswerEntity("B. his"),
				AnswerEntity("C. him"),
				AnswerEntity("D. he")
			)),
		QuestionInTestEntity("Câu hỏi 7: Choose the best answer:",R.drawable.standup,
			listOf(AnswerEntity("A. Stand Up"), AnswerEntity("B. Good morning"), AnswerEntity("C. See you soon"), AnswerEntity("D. See you again"))),
		QuestionInTestEntity(
			"Câu hỏi 8: Dad is … and son is … .",R.drawable.tall_short,
			listOf(
				AnswerEntity("A. tall – short"),
				AnswerEntity("B. short - tall"),
				AnswerEntity("C. long - short"),
				AnswerEntity("D. short - long")
			)),
		QuestionInTestEntity("Câu hỏi 9: Is your school ____?",0,
			listOf(AnswerEntity("A. play"), AnswerEntity("B. names"), AnswerEntity("C. those"), AnswerEntity("D. big"))),
		QuestionInTestEntity("Câu hỏi 10: Choose the odd one out:",0,
			listOf(AnswerEntity("A. shool"), AnswerEntity("B. sister"), AnswerEntity("C. mother"), AnswerEntity("D. father"))),
		QuestionInTestEntity("Câu hỏi 11: A: __________ - B: I’m ten.",0,
			listOf(
				AnswerEntity("A. What’s your name?"),
				AnswerEntity("B. What colour is it?"),
				AnswerEntity("C. How old are you?"),
				AnswerEntity("D. How are you?")
			)),
		QuestionInTestEntity("Câu hỏi 12: Are they your friends?",0,
			listOf(
				AnswerEntity("A. No, they are."),
				AnswerEntity("B. Yes, they do."),
				AnswerEntity("C. Yes, they aren’t."),
				AnswerEntity("D. Yes, they are.")
			)),
		QuestionInTestEntity("Câu hỏi 13: Choose the odd one out: ",0, listOf(
			AnswerEntity("A. Dog"),
			AnswerEntity("B. Cat "),
			AnswerEntity("C. Table"),
			AnswerEntity("D. Chicken")
		)),
		QuestionInTestEntity("Câu hỏi 14: Hello, how are you? – I am ___?",
			0,
			listOf(
				AnswerEntity("A. seven"),
				AnswerEntity("B. five"),
				AnswerEntity("C. fine"),
				AnswerEntity("D. student")
			)),
		QuestionInTestEntity("Câu hỏi 15: What animal has four legs?"
			,0,
			listOf(AnswerEntity("A. tiger"), AnswerEntity("B. chicken"), AnswerEntity("C. duck"), AnswerEntity("D. bird"))),
		QuestionInTestEntity("Câu hỏi 16: Calculate: 123 + 456",0, listOf(
			AnswerEntity("A. 350"),
			AnswerEntity("B. 579"),
			AnswerEntity("C. 600"),
			AnswerEntity("D. 479")
		)),
		QuestionInTestEntity("Câu hỏi 17: Each bag has 7 potatoes. How many potatoes are there in the nine bags?",
			0,
			listOf(AnswerEntity("A. 42"), AnswerEntity("B. 56"), AnswerEntity("C. 63"), AnswerEntity("D. 49"))),
		QuestionInTestEntity("Câu hỏi 18: five + ____ = six",
			0,
			listOf(AnswerEntity("A. four"), AnswerEntity("B. one"), AnswerEntity("C. nine"), AnswerEntity("D. two"))),
		QuestionInTestEntity("Câu hỏi 19: Which number makes the equation true? 163 + 100 = ........... + 102",
			0,
			listOf(AnswerEntity("A. 161"), AnswerEntity("B. 262"), AnswerEntity("C. 165"), AnswerEntity("D. 365"))),
		QuestionInTestEntity("Câu hỏi 20: The product of y and 9 is a two-digit number that has the digit 8 in the tens place. When y is divided by 9, the remainder is ................",
			0,
			listOf(AnswerEntity("A. 0"), AnswerEntity("B. 9"), AnswerEntity("C. 1"), AnswerEntity("D. 8"))),
	)
	private val startTestAdapter by lazy {
		StartTestAdapter(this,listTest(name))
	}
	override fun initView() {
		name = intent.getStringExtra("nameTest") as String
		testViewModel = ViewModelProvider(this)[TestViewModel::class.java]
		testCount = TestCount()
		binding.rcvTest.layoutManager = LinearLayoutManager(this)
		binding.rcvTest.adapter = startTestAdapter

	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnSave.setOnClickListener {
			val score = testCount.scoreTest(startTestAdapter.getAnswerList(),name,this)
			val id = Random.nextLong()
			val scoreTest = ScoreCharacterEntity(id,name,score,loadCharacter().id)
			if (startTestAdapter.getAnswerList().size <20){
				val builder = AlertDialog.Builder(this)
				builder.setMessage(resources.getString(R.string.save_score))

				builder.setPositiveButton(resources.getString(R.string.btn_yes)) { dialogInterface: DialogInterface, i: Int ->
					testViewModel.saveScore(scoreTest)
					val intent 	= Intent(this ,TestEndAct::class.java)
					intent.putExtra("score",score)
					startActivity(intent)
				}
				builder.setNegativeButton(resources.getString(R.string.btn_no)) { dialogInterface: DialogInterface, i: Int ->
					dialogInterface.dismiss()
				}

				builder.show()
			}
			else{
				testViewModel.saveScore(scoreTest)
				val intent 	= Intent(this ,TestEndAct::class.java)
				intent.putExtra("score",score)
				startActivity(intent)
			}
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_start_test
	}

	override fun bindViewModel() {

	}
	private fun listTest(name : String) : List<QuestionInTestEntity> {
		 return when(name){
			resources.getString(R.string.test1) -> list1
			resources.getString(R.string.test2) -> list2
			resources.getString(R.string.test3) -> emptyList()
			resources.getString(R.string.test4) -> emptyList()
			resources.getString(R.string.test5) -> emptyList()
			resources.getString(R.string.test6) -> emptyList()
			resources.getString(R.string.test7) -> emptyList()
			resources.getString(R.string.test8) -> emptyList()
			resources.getString(R.string.test9) -> emptyList()
			resources.getString(R.string.test10) -> emptyList()
		else ->emptyList()
	}
	}
	private fun loadCharacter(): CharacterEntity {
		val pref = this.getSharedPreferences("character", MODE_PRIVATE)
		val characterString = pref.getString("character", "")?:""
		return CharacterEntity.fromString(characterString)
	}
}