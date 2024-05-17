package com.example.to_do_list

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list.databinding.ItemTodoBinding // import the binding class for the item_todo layout

class TodoAdapter (private val todos: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder (val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) // pass the binding object to the view holder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false) // generate the binding object
        return TodoViewHolder(binding)
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }

    fun deleteDoneTodos(){
        todos.removeAll { todo ->
            todo.ischecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodo: TextView, ischecked: Boolean){
        if(ischecked){
            tvTodo.paintFlags = tvTodo.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodo.paintFlags = tvTodo.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.binding.apply { // use the binding object to access the views
            tvTodo.text = curTodo.title
            cbDone.isChecked = curTodo.ischecked
            toggleStrikeThrough(tvTodo, curTodo.ischecked)
            cbDone.setOnCheckedChangeListener { buttonView, ischecked ->
                toggleStrikeThrough(tvTodo, ischecked)
                curTodo.ischecked = !curTodo.ischecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
