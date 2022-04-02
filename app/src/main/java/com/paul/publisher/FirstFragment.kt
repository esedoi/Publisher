package com.paul.publisher

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.paul.publisher.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val db = FirebaseFirestore.getInstance()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val gson =Gson()

    lateinit var homeAdapter: HomeAdapter
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //sizeRecyclerView
        homeAdapter = HomeAdapter()
        layoutManager = LinearLayoutManager(this.context)
        binding.homeRecycler.layoutManager = layoutManager
        binding.homeRecycler.adapter = homeAdapter

        (activity as MainActivity).live.observe(viewLifecycleOwner){
            homeAdapter.submitList(it)
            homeAdapter.notifyDataSetChanged()
        }



        db.collection("articles")
            .get()
            .addOnSuccessListener { result ->

               Log.d("firstfragment", "$result")
            }
            .addOnFailureListener { exception ->
                Log.w("request", "Error getting documents.", exception)
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}