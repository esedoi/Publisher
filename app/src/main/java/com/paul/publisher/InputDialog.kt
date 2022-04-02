package com.paul.publisher

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.paul.publisher.databinding.DialogInputBinding
import java.util.*

class InputDialog : DialogFragment() {

    //binding
    private var _binding: DialogInputBinding? = null
    private val binding get() = _binding!!

    //database
    private val db = FirebaseFirestore.getInstance()
    var myId = db.collection("article").document().id

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DialogInputBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.inputBtn.setOnClickListener {
            val title = binding.inputTitle.text.toString()
            val content = binding.inputContent.text.toString()
            val category = binding.inputCategory.text.toString()

            if(title!=""&&content!=""&&category!=""){
                addData(title, content, category)
                binding.inputTitle.text.clear()
                binding.inputCategory.text.clear()
                binding.inputContent.text.clear()
                binding.inputCategory.text.clear()
                findNavController().navigateUp()
            }else{
                Toast.makeText(this.context, "有欄位沒有填寫完成", Toast.LENGTH_SHORT).show()
            }

        }



        return root

    }

    fun addData(title: String, content: String, category: String) {
        val articles = FirebaseFirestore.getInstance()
            .collection("articles")
        val document = articles.document()
        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to "paul@school.appworks.tw",
                "id" to "paul3029",
                "name" to "Paul"
            ),
            "title" to title,
            "content" to content,
            "createdTime" to Calendar.getInstance()
                .timeInMillis,
            "id" to document.id,
            "category" to category
        )
        document.set(data)
            .addOnSuccessListener { documentReference ->
                Log.d("click", "DocumentSnapshot added with ID: $documentReference")
//                Toast.makeText(this.context, "送出成功", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w("click", "Error adding document", e)
            }
    }


}