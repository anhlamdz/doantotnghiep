package kids.preschool.doantotnghiep.ui.home.test.starttest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.models.AnswerEntity
import kids.preschool.doantotnghiep.databinding.ItemAnswerBinding

class QuestionAdapter(
	val context : Context,
	val list : List<AnswerEntity>,
	private val listener : CheckAnswerListener
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {
	inner class QuestionViewHolder(val binding : ItemAnswerBinding) :RecyclerView.ViewHolder(binding.root) {
		fun bind(text : AnswerEntity){
			binding.apply {
				answer.text = text.answer
				updateColor(text.isChecked)
				check.setOnClickListener{
					selectAnswer(text)
					listener.onCheckItem(text.answer,text.isChecked)
				}
			}
		}
		private fun updateColor(isChecked :Boolean) {
			with(binding) {
				when(isChecked){
					true -> {
						check.setImageResource(R.drawable.bg_btn_selected)
					}
					false-> {
						check.setImageResource(R.drawable.bg_btn_unselected)
					}
				}
			}
		}
		private fun selectAnswer(selectedAnswer: AnswerEntity) {
			for (answer in list) {
				answer.isChecked = (answer == selectedAnswer)
			}
			notifyDataSetChanged()
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.QuestionViewHolder {
		val binding = ItemAnswerBinding.inflate(LayoutInflater.from(context),parent,false)
		return QuestionViewHolder(binding)
	}

	override fun onBindViewHolder(holder: QuestionAdapter.QuestionViewHolder, position: Int) {
		val item = list[position]
		holder.bind(item)
	}

	override fun getItemCount(): Int {
		return list.size
	}
	interface CheckAnswerListener{
		fun onCheckItem(text: String,isChecked: Boolean)
	}

}