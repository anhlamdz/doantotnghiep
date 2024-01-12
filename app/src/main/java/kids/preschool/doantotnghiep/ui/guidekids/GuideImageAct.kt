package kids.preschool.doantotnghiep.ui.guidekids

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.databinding.ActivityImageAvatarBinding
import kids.preschool.doantotnghiep.ui.guidekids.viewmodel.GuideCharacterViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.random.Random


class GuideImageAct : BaseActivity<ActivityImageAvatarBinding>() {
	private lateinit var guideCharacterViewModel: GuideCharacterViewModel
	private var name: String = ""
	private var age: Int = 0
	private lateinit var user: UserEntity
	private var selectedImageUri: Uri? = null
	private lateinit var auth: FirebaseAuth
	private lateinit var dbRef : DatabaseReference
	override fun initView() {
		auth = Firebase.auth
		dbRef = FirebaseDatabase.getInstance().getReference("Character")
		guideCharacterViewModel = ViewModelProvider(this)[GuideCharacterViewModel::class.java]
		lifecycleScope.launch {
			user = guideCharacterViewModel.getInfo()
		}
		name = intent.getStringExtra("name") as String
		age = intent.getIntExtra("age", 0)
	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnSave.setOnClickListener {
			saveCharacter()
		}
		binding.btnChose.setOnClickListener {
			pickImageFromGallery()
		}
		binding.btnShot.setOnClickListener {
			captureImageWithCamera()
		}


	}

	override fun getContentView(): Int {
		return R.layout.activity_image_avatar
	}

	override fun bindViewModel() {

	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == RESULT_OK) {
			val uri: Uri = data?.data!!
			selectedImageUri = uri
			binding.avatar.setImageURI(uri)
		} else if (resultCode == ImagePicker.RESULT_ERROR) {
			Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
		} else {
			Toast.makeText(this, resources.getText(R.string.cancell), Toast.LENGTH_SHORT).show()
		}
	}

	private fun saveCharacter() {
		if (selectedImageUri == null) {
			Toast.makeText(this,resources.getText(R.string.warning_image),Toast.LENGTH_LONG).show()
		}
		else {
			val id = Random.nextLong()
			val imagePath = selectedImageUri.toString()
			var currentUser = auth.currentUser
			if (currentUser!= null){
				val newCharacter = CharacterEntity(id, name, age, imagePath, user.id)
				dbRef.child(id.toString()).setValue(newCharacter)
					.addOnCompleteListener {
						guideCharacterViewModel.insertCharacter(newCharacter)
						uploadImage(id)
					}
					.addOnFailureListener {
						Toast.makeText(this,"${it.message}",Toast.LENGTH_SHORT).show()
						Log.i("hahaha", "failded ${it.message}")
					}

			}
			startActivity(Intent(this, CharacterAct::class.java))
			finish()
		}
	}
	fun uploadImage(id : Long){
		if (selectedImageUri!= null){
			val progressDialog = ProgressDialog(this)
			progressDialog.setTitle("")
			progressDialog.setMessage(resources.getText(R.string.upload_image))

			val ref : StorageReference = FirebaseStorage.getInstance().getReference()
				.child("${id}.png")
			ref.putFile(selectedImageUri!!)
				.addOnCompleteListener {
					Toast.makeText(this,resources.getText(R.string.create_character_success),Toast.LENGTH_LONG).show()
				}
				.addOnFailureListener {
					Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
				}
		}
	}


	private fun pickImageFromGallery() {
		ImagePicker.with(this)
			.galleryOnly()
			.start()
	}

	private fun captureImageWithCamera() {
		ImagePicker.with(this)
			.cameraOnly()
			.start()
	}

}