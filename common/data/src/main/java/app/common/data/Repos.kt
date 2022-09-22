package app.common.data

import app.common.data.domain.contacts.ContactsRepo
import app.common.data.domain.contacts.ContactsRepoInterface

open class Repos(
    val contacts: ContactsRepoInterface
) {
    companion object {
        fun build(): Repos {
            return Repos(ContactsRepo.build())
        }
    }
}
