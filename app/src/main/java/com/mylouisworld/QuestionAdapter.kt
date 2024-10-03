package com.mylouisworld

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mylouisworld.databinding.QuestionCardBinding

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
        holder.binding.imgQuestion.setImageResource(QuestionData.questions[position].imageId)
        holder.binding.txtQuestionTitle.text = QuestionData.questions[position].question

    }
}
