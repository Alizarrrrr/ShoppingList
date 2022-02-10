package com.example.shoppinglist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglist.entities.NoteItem
import com.example.shoppinglist.entities.ShoppingListName
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM note_list")
    fun getAllNotes(): Flow<List<NoteItem>>
    @Query("SELECT * FROM shopping_list_names")
    fun getAllShopListNames(): Flow<List<ShoppingListName>>

    @Query("DELETE FROM note_list WHERE id IS :id")
    suspend fun deleteNote(id: Int)
    @Query("DELETE FROM shopping_list_names WHERE id IS :id")
    suspend fun deleteShoppingList(id: Int)

    @Insert
    suspend fun insertNote(note: NoteItem)
    @Insert
    suspend fun insertShopListName(name: ShoppingListName)
    @Update
    suspend fun updateNote(note: NoteItem)


}