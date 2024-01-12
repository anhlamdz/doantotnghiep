package kids.preschool.doantotnghiep.ui.home.test.starttest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kids.preschool.doantotnghiep.data.models.QuestionInTestEntity
import kids.preschool.doantotnghiep.data.models.TestAnswerEntity
import kids.preschool.doantotnghiep.databinding.ItemStartTestBinding

class StartTestAdapter (
	val context : Context,
	val list : List<QuestionInTestEntity>
): RecyclerView.Adapter<StartTestAdapter.StartTestViewHolder>() {
	private val listAnswer = mutableListOf<TestAnswerEntity>()
	inner class StartTestViewHolder(val binding : ItemStartTestBinding): RecyclerView.ViewHolder(binding.root) {
		fun bind(questionInTestEntity: QuestionInTestEntity){
			binding.apply {
				question.text = questionInTestEntity.question
				answer.layoutManager = GridLayoutManager(context,2)
				val questionAdapter = QuestionAdapter(context,questionInTestEntity.list,object : QuestionAdapter.CheckAnswerListener{
					override fun onCheckItem(text: String,isChecked : Boolean) {
						updateWorkoutList(questionInTestEntity.question,isChecked,text,position+1)
					}
				})
				answer.adapter = questionAdapter
				if (questionInTestEntity.image == 0) {
					imageQues.visibility = ViewGroup.INVISIBLE
				}else {
					imageQues.visibility = ViewGroup.VISIBLE
					imageQues.setImageResource(questionInTestEntity.image)
				}
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartTestAdapter.StartTestViewHolder {
		val binding = ItemStartTestBinding.inflate(LayoutInflater.from(context),parent,false)
		return StartTestViewHolder(binding)
	}

	override fun onBindViewHolder(holder: StartTestAdapter.StartTestViewHolder, position: Int) {
		val question = list[position]
		holder.bind(question)
	}

	override fun getItemCount(): Int {
		return list.size
	}
	fun updateWorkoutList(name : String, isChecked: Boolean, answer : String, position: Int) {
		if (isChecked) {
			val end = TestAnswerEntity(name, answer,position)
			listAnswer.add(end)
		} else {
			val removedAnswer = listAnswer.find { it.name == name && it.answer == (answer) }
			removedAnswer?.let {
				listAnswer.remove(it)
			}
		}
		listAnswer.sortBy { it.position }
		notifyDataSetChanged()
	}
	fun getAnswerList() : List<TestAnswerEntity>{
		return listAnswer
	}
}