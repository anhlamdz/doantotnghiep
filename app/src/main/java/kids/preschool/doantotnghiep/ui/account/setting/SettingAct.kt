package kids.preschool.doantotnghiep.ui.account.setting

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.view.Change
import io.strongapp.gymworkout.base.BaseActivity
import kids.preschool.doantotnghiep.R
import kids.preschool.doantotnghiep.data.database.entities.UserEntity
import kids.preschool.doantotnghiep.databinding.ActivityDetailProfileBinding
import kids.preschool.doantotnghiep.ui.account.setting.changepass.ChangePassAct
import kids.preschool.doantotnghiep.ui.account.viewmodel.AccountViewModel
import kids.preschool.doantotnghiep.ui.guidekids.CharacterAct
import kids.preschool.doantotnghiep.ui.multiLang.MultiLangAct
import kotlinx.coroutines.launch

class SettingAct : BaseActivity<ActivityDetailProfileBinding>() {
	private lateinit var userEntity: UserEntity
	private lateinit var accountViewModel: AccountViewModel
	private lateinit var dbRef : DatabaseReference
	private lateinit var auth: FirebaseAuth
	override fun initView() {
		auth = Firebase.auth
		dbRef = FirebaseDatabase.getInstance().getReference("User")
		accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
		lifecycleScope.launch {
			userEntity = accountViewModel.getInfo()
			binding.nameParent.text = userEntity.nameParent.toEditable()
			binding.numberPhoneParent.text = userEntity.numberPhone
			binding.emailDescriptoin.text = userEntity.email

		}

	}

	override fun initAction() {
		binding.btnUpdate.setOnClickListener {
			if (binding.nameParent.text.toString().isNullOrEmpty()){
				Toast.makeText(this,resources.getText(R.string.warning_name_parent),Toast.LENGTH_LONG).show()
			}
			else {
				val newUser = UserEntity(userEntity.id,userEntity.email,userEntity.pass,"",binding.nameParent.text.toString())
				dbRef.child(userEntity.id).setValue(newUser)
					.addOnCompleteListener {
						accountViewModel.updateAccount(newUser)
						Toast.makeText(this,resources.getText(R.string.update_user),Toast.LENGTH_LONG).show()
					}
					.addOnFailureListener {
						Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
					}
			}
		}
		binding.btnBack.setOnClickListener {
			finish()
		}
		binding.btnUpdatePass.setOnClickListener {
			val intent = Intent(this, ChangePassAct::class.java)
			intent.putExtra("user",userEntity)
			startActivityForResult(intent, REQUEST_CHANGE_PASS)
		}
		binding.btnDetail.setOnClickListener {
			val  intent = Intent(this, CharacterAct::class.java)
			intent.putExtra("mode","edit")
			startActivity(intent)
		}
		binding.btnLogout.setOnClickListener {
			accountViewModel.clearData()
			val prefLang = this.getSharedPreferences("lang", MODE_PRIVATE)
			val prefCharacter = this.getSharedPreferences("character", MODE_PRIVATE)
			val editorLang = prefLang.edit()
			val editCharacter = prefCharacter.edit()
			editorLang.clear()
			editCharacter.clear()
			editorLang.apply()
			editCharacter.apply()
			auth.signOut()
			val intent = Intent(this,MultiLangAct::class.java)
			intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
			startActivity(intent)
			finish()
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == REQUEST_CHANGE_PASS && resultCode == Activity.RESULT_OK) {
			lifecycleScope.launch {
				userEntity = accountViewModel.getInfo()
				binding.nameParent.text = userEntity.nameParent.toEditable()
				binding.numberPhoneParent.text = userEntity.numberPhone
			}
		}
	}

	override fun getContentView(): Int {
		return R.layout.activity_detail_profile
	}

	override fun bindViewModel() {

	}
	fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

	companion object {
		const val REQUEST_CHANGE_PASS = 1
	}
}