package app.common.presentation.ui.view

import androidx.lifecycle.MutableLiveData

class ShowErrorHelper(private val errorValue: MutableLiveData<String>) {

    fun showError(error: String) {
        errorValue.postValue(error)
    }

}