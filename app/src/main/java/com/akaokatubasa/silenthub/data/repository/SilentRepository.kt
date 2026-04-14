package com.akaokatubasa.silenthub.data.repository

import com.akaokatubasa.silenthub.data.datasource.JsonDataSource
import com.akaokatubasa.silenthub.data.model.Contact
import com.akaokatubasa.silenthub.data.model.DataContainer
import com.akaokatubasa.silenthub.data.model.NotificationItem

class SilentRepository(private val dataSource: JsonDataSource) {

    fun getContacts(): List<Contact> {
        return dataSource.load().contacts
    }

    fun addContact(contact: Contact) {
        val current = dataSource.load()
        val updated = current.copy(contacts = current.contacts + contact)
        dataSource.save(updated)
    }

    fun getNotifications(): List<NotificationItem> {
        return dataSource.load().notifications
    }

    fun addNotification(item: NotificationItem) {
        val current = dataSource.load()
        val updated = current.copy(notifications = current.notifications + item)
        dataSource.save(updated)
    }

    fun deleteNotification(id: String) {
        val current = dataSource.load()
        val updated = current.copy(
            notifications = current.notifications.filterNot { it.id == id }
        )
        dataSource.save(updated)
    }

    fun updateAll(data: DataContainer) {
        dataSource.save(data)
    }
}
