package kids.preschool.doantotnghiep.ui.guidekids.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.databinding.ItemCharacterBinding

class CharacterAdapter(
	val context : Context,
	val list : List<CharacterEntity>
	) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
	inner class CharacterViewHolder
		(val binding : ItemCharacterBinding): RecyclerView.ViewHolder(binding.root)
	{
		fun bind(character : CharacterEntity){
			binding.apply {
				when {
					character.image.startsWith("android.resource://") -> {
						// Case 2: Image from resource
						val resourceId = character.image.substringAfterLast("/").toInt()
						avatar.setImageResource(resourceId)
					}
					character.image.isNotBlank() -> {
						// Case 3: Image from file or external source
						avatar.setImageURI(Uri.parse(character.image))
					}
					else -> {
						// Case 1: Default case (Bitmap)
						val defaultBitmap = BitmapFactory.decodeFile(character.image)
						avatar.setImageBitmap(defaultBitmap)
					}
				}
				avatar.setImageURI(Uri.parse(character.image))
				name.text = character.name
				age.text = "${context.resources.getText(R.string.age)} ${character.age}"
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
}