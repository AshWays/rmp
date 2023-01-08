package com.example.emapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emapp.R
import com.example.emapp.classes.BdzhilsAdapter
import com.example.emapp.classes.User
import com.example.emapp.view.AccountUser
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_account_user.*
import kotlinx.android.synthetic.main.fragment_news.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NewsFragment : BaseFragment() {

    private var param1: String? = null
    private var param2: String? = null
    private val database = Firebase.database
    private val myRef = database.getReference("Bdzils")
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
        adapter!!.onClickShowItem = {
            (this.requireActivity() as AccountUser).replaceNav()
            showMarker(it)
        }

    }

    private fun showMarker(item: User){
        val fragment = LocationFragment()
        val bundle = Bundle()
        bundle.putDouble("lat", item.lat.toDouble())
        bundle.putDouble("lng", item.lng.toDouble())
        fragment.arguments = bundle
        fragmentManager?.beginTransaction()?.replace(R.id.fragment_container,fragment)?.commit()
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

                val child = snapshot.children
                child.forEach {
                    val user = it.child("user").value
                    val name = it.child("name").value
                    val com = it.child("comment").value
                    val lat = it.child("lat").value
                    val lng = it.child("lng").value
                    val item = User(user as String, name as String, com as String,
                        lat as String, lng as String)
                    adapter?.addItem(item)
                }


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}