package kids.preschool.doantotnghiep.ui.guidekids

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.CharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.databinding.ActivityImageAvatarBinding
import kids.preschool.doantotnghiep.ui.guidekids.viewmodel.GuideCharacterViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random


class GuideImageAct : BaseActivity<ActivityImageAvatarBinding>() {
	private lateinit var guideCharacterViewModel: GuideCharacterViewModel
	private var name : String = ""
	private var age : Int = 0
	private lateinit var user : UserEntity
	private var selectedImageUri: Uri? = null
	private var capturedImageBitmap: Bitmap? = null
	override fun initView() {
		guideCharacterViewModel = ViewModelProvider(this)[GuideCharacterViewModel::class.java]
		lifecycleScope.launch {
			user = guideCharacterViewModel.getInfo()
		}
		name = intent.getStringExtra("name") as String
		age = intent.getIntExtra("age",0)
	}

	override fun initAction() {
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnSave.setOnClickListener {
			val id = Random.nextLong()
			val imagePath = selectedImageUri?.toString() ?: ""
			val bitmapPath = capturedImageBitmap?.toString() ?: ""
			val defaultImageResource = R.drawable.avatar
			val defaultImagePath = "android.resource://${packageName}/${defaultImageResource}"

			val finalImagePath = if (imagePath.isNotEmpty() || bitmapPath.isNotEmpty()) {
				if (selectedImageUri != null) imagePath else bitmapPath
			} else {
				defaultImagePath
			}


			val newCharacter = CharacterEntity(id, name, age, finalImagePath, user.id)
			Log.i("hahahaha",newCharacter.toString())
			guideCharacterViewModel.insertCharacter(newCharacter)


			val intent = Intent(this, CharaterAct::class.java)
			startActivity(intent)
			finish()
		}
		binding.btnChose.setOnClickListener {
			capturedImageBitmap = null
			val intent = Intent(Intent.ACTION_PICK)
			intent.type = "image/*"
			startActivityForResult(intent,1)
		}
		binding.btnShot.setOnClickListener {
			selectedImageUri = null
			Dexter.withContext(this)
				.withPermission(android.Manifest.permission.CAMERA)
				.withListener(object : PermissionListener {
					override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
						val cameraing = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
						startActivityForResult(cameraing, 2)
					}

					override fun onPermissionDenied(p0: PermissionDeniedResponse?) {

					}

					override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
						p1?.continuePermissionRequest()
					}
				}).check()

		}


	}

	override fun getContentView(): Int {
		return R.layout.activity_image_avatar
	}

	override fun bindViewModel() {

	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (requestCode ==1 && resultCode == RESULT_OK){
			selectedImageUri = data?.data
			binding.avatar.setImageURI(selectedImageUri)
		}
		if (requestCode ==2 && resultCode == RESULT_OK) {
			capturedImageBitmap = data?.extras?.get("data") as Bitmap

			binding.avatar.setImageBitmap(capturedImageBitmap)
		}
		super.onActivityResult(requestCode, resultCode, data)
	}
}