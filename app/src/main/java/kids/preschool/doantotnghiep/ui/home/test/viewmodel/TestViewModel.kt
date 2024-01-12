package kids.preschool.doantotnghiep.ui.home.test.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kids.preschool.doantotnghiep.data.database.entities.ScoreCharacterEntity
import kids.preschool.doantotnghiep.data.database.entities.UserAndCharacter
import kids.preschool.doantotnghiep.data.models.AppDatabase
import kids.preschool.doantotnghiep.data.repository.ScoreTestRepository
import kotlinx.coroutines.launch

class TestViewModel(application: Application) : AndroidViewModel(application) {
	val scoreTestRepository : ScoreTestRepository

	init {
		val scoreTestDao = AppDatabase.getDatabase(application).scoreTestDao()
		scoreTestRepository = ScoreTestRepository(scoreTestDao)
	}

	fun saveScore(scoreCharacterEntity: ScoreCharacterEntity) = viewModelScope.launch {
		scoreTestRepository.saveScoreTest(scoreCharacterEntity)
	}
	fun deleteScore(scoreCharacterEntity: ScoreCharacterEntity) = viewModelScope.launch {
		scoreTestRepository.deleteScore(scoreCharacterEntity)
	}
	fun getListScore(idCharacter : Long) : LiveData<List<ScoreCharacterEntity>> {
		return scoreTestRepository.getListScore(idCharacter)
	}

}