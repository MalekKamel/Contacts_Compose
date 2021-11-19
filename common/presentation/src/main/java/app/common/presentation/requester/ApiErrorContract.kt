package app.common.presentation.requester

import com.sha.coroutinerequester.exception.ErrorMessage


data class ApiErrorContract(private val message: String): ErrorMessage {
    override fun errorMessage(): String {
        return message
    }
}