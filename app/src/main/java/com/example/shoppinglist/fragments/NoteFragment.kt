package com.example.shoppinglist.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.shoppinglist.R
import com.example.shoppinglist.activities.MainApp
import com.example.shoppinglist.activities.NewNoteActivity
import com.example.shoppinglist.databinding.FragmentNoteBinding
import com.example.shoppinglist.db.MainViewModel


class NoteFragment : BaseFragment() {
    private  lateinit var binding: FragmentNoteBinding
    private val mainViewModel: MainViewModel by activityViewModels{
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }

    override fun onClickNew() {
        startActivity(Intent(activity, NewNoteActivity::class.java))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {


        @JvmStatic
        fun newInstance() =    NoteFragment()
            }
    }
