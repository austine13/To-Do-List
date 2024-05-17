package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.Todo
import com.example.to_do_list.TodoAdapter
import com.example.to_do_list.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: ActivityMainBinding // declare the binding object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // generate the binding object
        setContentView(binding.root) // set the content view to the root of the binding object
        todoAdapter = TodoAdapter(mutableListOf())

        binding.rv.adapter = todoAdapter // use the binding object to access the rvTodoItems view
        binding.rv.layoutManager = LinearLayoutManager(this)

        binding.addbutton.setOnClickListener {
            val todoTitle = binding.ettodolist.text.toString() // use the binding object to access the etTodoTitle view
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.ettodolist.text.clear() // use the binding object to access the etTodoTitle view
            }
        }
        binding.deletebutton.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}
