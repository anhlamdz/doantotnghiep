package kids.preschool.doantotnghiep.ui.home.test.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kids.preschool.doantotnghiep.data.database.entities.ScoreCharacterEntity
import kids.preschool.doantotnghiep.databinding.ItemScoreBinding

class HistoryScoreAdapter(
	val context: Context,
	val list: List<ScoreCharacterEntity>
) : RecyclerView.Adapter<HistoryScoreAdapter.HistoryScoreViewHolder>() {
	class HistoryScoreViewHolder(val binding: ItemScoreBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(scoreCharacterEntity: ScoreCharacterEntity) {
			binding.apply {
				name.text = "${scoreCharacterEntity.name}:"
				score.text = "Điểm ${scoreCharacterEntity.score}đ"
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryScoreViewHolder {
		val binding = ItemScoreBinding.inflate(LayoutInflater.from(context), parent, false)
		return HistoryScoreViewHolder(binding)
	}

	override fun onBindViewHolder(holder: HistoryScoreViewHolder, position: Int) {
		val score = list[position]
		holder.bind(score)
	}

	override fun getItemCount(): Int {
		return list.size
	}
}