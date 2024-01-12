package kids.preschool.doantotnghiep.ui.home.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kids.preschool.doantotnghiep.databinding.ItemTestBinding

class TestAdapter(
	val context: Context,
	val list : List<String>,
	val listener : OnClickListener
) : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {
	inner class TestViewHolder (val binding : ItemTestBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(test : String){
			binding.nameTest.text = test
			binding.btnStart.setOnClickListener {
				listener.onClick(test)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.TestViewHolder {
		val binding = ItemTestBinding.inflate(LayoutInflater.from(context),parent,false)
		return TestViewHolder(binding)
	}

	override fun onBindViewHolder(holder: TestAdapter.TestViewHolder, position: Int) {
		val test = list[position]
		holder.bind(test)
	}

	override fun getItemCount(): Int {
		return list.size
	}
	interface OnClickListener {
		fun onClick(test : String)
	}
}