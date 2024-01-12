package kids.preschool.doantotnghiep.ui.guidekids

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.databinding.ActivityEditCharacterBinding
import kids.preschool.doantotnghiep.ui.guidekids.viewmodel.GuideCharacterViewModel

class EditCharacterAct : BaseActivity<ActivityEditCharacterBinding>() {
	private lateinit var characterEntity: CharacterEntity
	private lateinit var characterViewModel: GuideCharacterViewModel
	private var selectedImageUri: Uri? = null
	private var selectedAgeTextView: TextView? = null
	private var age: Int = 0
	private lateinit var dbRef : DatabaseReference
	private lateinit var auth: FirebaseAuth
	override fun initView() {
		auth = Firebase.auth
		dbRef = FirebaseDatabase.getInstance().getReference("Character")
		characterViewModel = ViewModelProvider(this)[GuideCharacterViewModel::class.java]
		characterEntity = intent.getSerializableExtra("character") as CharacterEntity
		binding.name.text = characterEntity.name.toEditable()
		age = characterEntity.age
		selectedImageUri = Uri.parse(characterEntity.image)
		loadAvatar(characterEntity)
		when (characterEntity.age) {
			6 -> {
				selectAge(binding.age6)
			}

			7 -> {
				selectAge(binding.age7)
			}

			8 -> {
				selectAge(binding.age8)
			}

			9 -> {
				selectAge(binding.age9)
			}

			10 -> {
				selectAge(binding.age10)
			}
		}
		val ageTextViews = listOf(
			binding.age6,
			binding.age7,
			binding.age8,
			binding.age9,
			binding.age10
		)

		ageTextViews.forEach { textView ->
			textView.setOnClickListener {
				resetAllAges()
				selectAge(textView)
				age = textView.text.toString().toInt()
				checkCharacterInfo(characterEntity)
				Log.i("hahahaha1", age.toString())
				Log.i("hahahaha2", characterEntity.age.toString())
			}
		}

		binding.name.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

			}

			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
				checkCharacterInfo(characterEntity)
			}

			override fun afterTextChanged(p0: Editable?) {

			}

		})
	}

	override fun initAction() {
		binding.btnChose.setOnClickListener {
			ImagePicker.with(this)
				.galleryOnly()
				.start()
		}
		binding.btnShot.setOnClickListener {
			ImagePicker.with(this)
				.cameraOnly()
				.start()
		}
		binding.btnBack.setOnClickListener {
			if (binding.btnSave.visibility == View.VISIBLE) {
				val builder = AlertDialog.Builder(this)
				builder.setMessage(resources.getText(R.string.dialog_title))

				builder.setPositiveButton(resources.getText(R.string.btn_yes)) { dialogInterface: DialogInterface, i: Int ->
					finish()
				}
				builder.setNegativeButton(resources.getText(R.string.btn_no)) { dialogInterface: DialogInterface, i: Int ->
					dialogInterface.dismiss()
				}

				builder.show()
			} else {
				finish()
			}
		}
		binding.btnSave.setOnClickListener {
			val characterUpdate =
				CharacterEntity(characterEntity.id, binding.name.text.toString(), age, selectedImageUri.toString(), characterEntity.idUser)
			dbRef.child(characterEntity.id.toString()).setValue(characterUpdate)
				.addOnCompleteListener {
					if (selectedImageUri.toString()!=characterEntity.image){
						uploadImage()
					}
					characterViewModel.updateCharacter(characterUpdate)
					Toast.makeText(this, resources.getText(R.string.update_character), Toast.LENGTH_SHORT).show()
				}
				.addOnFailureListener {
					Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
				}
			finish()
		}

		binding.btnDelete.setOnClickListener {
			val builder = AlertDialog.Builder(this)
			builder.setMessage(resources.getText(R.string.warning_delete))

			builder.setPositiveButton(resources.getText(R.string.btn_yes)) { dialogInterface: DialogInterface, i: Int ->
				deleteImage()
			}
			builder.setNegativeButton(resources.getText(R.string.btn_no)) { dialogInterface: DialogInterface, i: Int ->
				dialogInterface.dismiss()
			}
			builder.show()
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_edit_character
	}

	override fun bindViewModel() {

	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == RESULT_OK) {
			val uri: Uri = data?.data!!
			selectedImageUri = uri
			binding.avatar.setImageURI(uri)
			binding.btnSave.visibility = if (selectedImageUri.toString() != characterEntity.image) {
				View.VISIBLE
			} else {
				View.GONE
			}
		} else if (resultCode == ImagePicker.RESULT_ERROR) {
			Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
		} else {
			Toast.makeText(this, resources.getText(R.string.cancell), Toast.LENGTH_SHORT).show()
		}
	}

	fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
	fun loadAvatar(character: CharacterEntity) {
		Glide.with(this)
			.load(character.image)
			.into(binding.avatar)
	}

	private fun selectAge(ageTextView: TextView) {
		selectedAgeTextView?.post {
			selectedAgeTextView?.setBackgroundResource(R.drawable.circle_age_checked)
			selectedAgeTextView?.setTextColor(resources.getColor(R.color.white))
		}

		ageTextView.post {
			ageTextView.setBackgroundResource(R.drawable.circle_age_checked)
			ageTextView.setTextColor(resources.getColor(R.color.white))
		}

		selectedAgeTextView = ageTextView

	}

	private fun resetAllAges() {
		binding.age6.post {
			binding.age6.setBackgroundResource(R.drawable.circle_age)
			binding.age6.setTextColor(resources.getColor(R.color.black))
		}

		binding.age7.post {
			binding.age7.setBackgroundResource(R.drawable.circle_age)
			binding.age7.setTextColor(resources.getColor(R.color.black))
		}

		binding.age8.post {
			binding.age8.setBackgroundResource(R.drawable.circle_age)
			binding.age8.setTextColor(resources.getColor(R.color.black))
		}

		binding.age9.post {
			binding.age9.setBackgroundResource(R.drawable.circle_age)
			binding.age9.setTextColor(resources.getColor(R.color.black))
		}

		binding.age10.post {
			binding.age10.setBackgroundResource(R.drawable.circle_age)
			binding.age10.setTextColor(resources.getColor(R.color.black))
		}
	}
	fun uploadImage(){
		if (selectedImageUri!= null){
			val progressDialog = ProgressDialog(this)
			progressDialog.setTitle("")
			progressDialog.setMessage(resources.getText(R.string.upload_image))

			val ref : StorageReference = FirebaseStorage.getInstance().getReference()
				.child("${characterEntity.id}.png")
			ref.putFile(selectedImageUri!!)
				.addOnCompleteListener {
					Toast.makeText(this,resources.getText(R.string.update_user),Toast.LENGTH_LONG).show()
				}
				.addOnFailureListener {
					Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
				}
		}
	}
	fun deleteImage(){
		val ref = FirebaseStorage.getInstance().getReference().child("${characterEntity.id}.png")
			ref.delete().addOnCompleteListener {
				dbRef.child(characterEntity.id.toString()).removeValue()
			}.addOnFailureListener {
				Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
			}
		val intent = Intent()
		setResult(Activity.RESULT_OK,intent)
		finish()
		characterViewModel.deleteCharacter(characterEntity)
	}
	private fun checkCharacterInfo(character: CharacterEntity?) {
		if (character == null) {
			binding.btnSave.visibility = View.GONE
			return
		}
		val isInfoChanged = (
				binding.name.text.toString().equals(character.name) &&
						age == character.age
				)
		binding.btnSave.visibility = if (isInfoChanged) {
			View.GONE
		} else {
			View.VISIBLE
		}
	}


}