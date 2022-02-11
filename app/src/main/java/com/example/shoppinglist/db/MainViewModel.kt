package com.example.shoppinglist.db

import androidx.lifecycle.*
import com.example.shoppinglist.entities.NoteItem
import com.example.shoppinglist.entities.ShoppingListName
import kotlinx.coroutines.launch

class MainViewModel(dataBase: MainDataBase): ViewModel() {
    val dao = dataBase.getDao()
    val allNotes: LiveData<List<NoteItem>> = dao.getAllNotes().asLiveData()
    val allShopListNames: LiveData<List<ShoppingListName>> = dao.getAllShopListNames().asLiveData()
    fun insertNote(note: NoteItem) = viewModelScope.launch {
        dao.insertNote(note)
    }

    fun insertShopListName(listName: ShoppingListName) = viewModelScope.launch {
        dao.insertShopListName(listName)
    }

    fun updateNote(note: NoteItem) = viewModelScope.launch {
        dao.updateNote(note)
    }
    fun updateListName(shopListName: ShoppingListName) = viewModelScope.launch {
        dao.updateListName(shopListName)
    }
    fun deleteNote(id: Int) = viewModelScope.launch {
        dao.deleteNote(id)
    }

    fun deleteShoppingList(id: Int) = viewModelScope.launch {
        dao.deleteShoppingList(id)
    }

    class  MainViewModelFactory(val database: MainDataBase) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return  MainViewModel(database) as T
            }
            throw IllegalAccessException("Unknown ViewModelClass")
        }

    }
}