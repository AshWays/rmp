package com.example.emapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emapp.R
import com.example.emapp.classes.BdzhilsAdapter
import com.example.emapp.classes.User
import com.example.emapp.classes.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_news.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NewsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private val database = Firebase.database
    private val myRef = database.getReference("Bdzils")
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
    private var bdzhilsList: HashMap<String,String> = HashMap()
    private var adapter: BdzhilsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        onChangeListener(myRef)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = BdzhilsAdapter()
        newsRecycler.adapter = adapter
        adapter?.setOnClickShowItem {
            showMarker(it)
        }

    }

    private fun showMarker(item: User){

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun onChangeListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var child = snapshot.children
                child.forEach {
                    var user = it.child("user").value
                    var name = it.child("name").value
                    var com = it.child("comment").value
                    var lat = it.child("lat").value
                    var lng = it.child("lng").value
                    val item = User(user as String?, name as String?, com as String?,
                        lat as Long?, lng as Long?)
                    adapter?.addItem(item)
                }


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}