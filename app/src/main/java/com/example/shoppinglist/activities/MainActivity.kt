package com.example.shoppinglist.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.dialogs.NewListDialog
import com.example.shoppinglist.fragments.FragmentManager
import com.example.shoppinglist.fragments.NoteFragment
import com.example.shoppinglist.fragments.ShopListNamesFragment
import com.example.shoppinglist.settings.SettingsActivity

class MainActivity : AppCompatActivity(), NewListDialog.Listener {
    lateinit var binding: ActivityMainBinding
    private lateinit var defPref: SharedPreferences
    private var currentMenuItemId = R.id.share_list


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        defPref = PreferenceManager.getDefaultSharedPreferences(this)
        setTheme(getSelectedThem())
        setContentView(binding.root)
        FragmentManager.setFragment(ShopListNamesFragment.newInstance(),this)
        setBottomNavListener()
    }

    private fun setBottomNavListener(){
        binding.bNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.settings->{
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
                R.id.notes->{
                    currentMenuItemId = R.id.notes
                    FragmentManager.setFragment(NoteFragment.newInstance(),this)
                }
                R.id.shop_list->{
                    currentMenuItemId = R.id.share_list
                    FragmentManager.setFragment(ShopListNamesFragment.newInstance(),this)
                }
                R.id.new_item->{
                    FragmentManager.currentFrag?.onClickNew()

                }

            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bNav.selectedItemId = currentMenuItemId
    }

    private fun getSelectedThem(): Int{
        return if(defPref.getString("theme_key", "blue") == "blue"){
            R.style.Theme_ShoppingList
        } else{
            R.style.Theme_EditRed
        }
    }

    override fun onClick(name: String) {
        Log.d("MyLog", "Name: $name")
    }
}