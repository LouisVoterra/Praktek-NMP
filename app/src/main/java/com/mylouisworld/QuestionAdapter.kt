package com.mylouisworld

import android.app.AlertDialog
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mylouisworld.databinding.QuestionCardBinding
import com.squareup.picasso.Picasso

class QuestionAdapter()
    :RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {
    class QuestionViewHolder(val binding: QuestionCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = QuestionCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return QuestionViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return QuestionData.questions.size

    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {

        if (QuestionData.questions[position].url != "") {
            val builder = Picasso.Builder(holder.itemView.context)
            val url = QuestionData.questions[position].url
            builder.listener { picasso, uri, exception -> exception.printStackTrace() }
            builder.build().load(url).into(holder.binding.imgQuestion)
        } else {
            holder.binding.imgQuestion.setImageResource(QuestionData.questions[position].imageId)
        }
        if (!QuestionData.questions[position].isAvailable) {
            val colorMatrix = ColorMatrix().apply {
                setSaturation(0f)
            }
            val colorFilter = ColorMatrixColorFilter(colorMatrix)
            holder.binding.imgQuestion.colorFilter = colorFilter
        }
        holder.binding.txtQuestionTitle.text = QuestionData.questions[position].question

        holder.binding.btnEdit.setOnClickListener {
            val intent = Intent(holder.itemView.context, EditQuestionActivity::class.java)
            intent.putExtra("question_index", position)
            holder.itemView.context.startActivity(intent)
        }

        holder.binding.btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Alert")
            builder.setMessage("Are you sure you want to delete this item?")
            builder.setPositiveButton("Yes") { dialog, _ ->
                val mutableList = QuestionData.questions.toMutableList()
                mutableList.removeAt(position)
                QuestionData.questions = mutableList.toTypedArray()
                notifyDataSetChanged()
                dialog.dismiss()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()
        }
    }
}
