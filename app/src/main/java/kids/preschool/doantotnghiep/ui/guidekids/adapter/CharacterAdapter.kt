package kids.preschool.doantotnghiep.ui.guidekids.adapter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.databinding.ItemCharacterBinding
import kids.preschool.doantotnghiep.ui.home.HomeAct

class CharacterAdapter(
	val context : Context,
	val list : List<CharacterEntity>,
	val mode : String,
	private val listener : OnClickListener
	) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
	inner class CharacterViewHolder
		(val binding : ItemCharacterBinding): RecyclerView.ViewHolder(binding.root)
	{
		fun bind(character : CharacterEntity){
			binding.apply {
				Glide.with(context)
					.load(character.image)
					.into(avatar)
				name.text = character.name
				age.text = "${context.resources.getText(R.string.age)} ${character.age}"
				if (mode.equals("edit")){
					icEdit.visibility = ViewGroup.VISIBLE
				}
				else {
					icEdit.visibility = ViewGroup.GONE
				}
				itemView.setOnClickListener {
					saveCharacter(character)
					listener.onClick(character)
				}
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
		val binding = ItemCharacterBinding.inflate(LayoutInflater.from(context),parent,false)
		return CharacterViewHolder(binding)
	}

	override fun getItemCount(): Int {
		return list.size
	}

	override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
		val character = list[position]
		holder.bind(character)
	}
	private fun saveCharacter(character: CharacterEntity){
		val pref = context.getSharedPreferences("character", MODE_PRIVATE)
		val editor = pref.edit()
		editor.putString("character",CharacterEntity.toString(character))
		editor.commit()
	}
	interface OnClickListener {
		fun onClick(character: CharacterEntity)
	}
}