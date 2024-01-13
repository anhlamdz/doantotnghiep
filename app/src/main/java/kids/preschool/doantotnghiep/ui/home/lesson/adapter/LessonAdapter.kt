package kids.preschool.doantotnghiep.ui.home.lesson.adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kids.preschool.doantotnghiep.data.models.LessonEntity
import kids.preschool.doantotnghiep.databinding.ItemLessonBinding
import kids.preschool.doantotnghiep.ui.home.lesson.startlesson.StartLessonAct

class LessonAdapter(
	val context : Context,
	val list : List<LessonEntity>
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
	 inner class LessonViewHolder(val binding : ItemLessonBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(lesson : LessonEntity){
			binding.apply {
				nameItem.text = context.getText(lesson.name)
				imageItem.setImageResource(lesson.image)
				val color = context.getColor(lesson.color)

				item.backgroundTintList = ColorStateList.valueOf(color)

				itemView.setOnClickListener {
					val intent = Intent(context,StartLessonAct::class.java)
					intent.putExtra("lesson",context.getString(lesson.name))
					context.startActivity(intent)
				}
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
		val binding = ItemLessonBinding.inflate(LayoutInflater.from(context),parent, false)
		return LessonViewHolder(binding)
	}

	override fun getItemCount(): Int {
		return list.size
	}

	override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
		val lesson = list[position]
		holder.bind(lesson)
	}
}