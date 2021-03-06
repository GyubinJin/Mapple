package com.kohyw.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kohyw.todolist.databinding.ActivityMainBinding
import com.kohyw.todolist.databinding.ItemTodoBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val data = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        data.add(Todo("숙제"))
        data.add(Todo("청소"))

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = TodoAdapter(data)

        binding.addButton.setOnClickListener {
            addTodo()
        }
    }

    private fun addTodo() {
        val todo = Todo(binding.editText.text.toString())
        data.add(todo)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}

data class Todo(val text: String, var isDone: Boolean = false)

class TodoAdapter(private val myDataset: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(ItemTodoBinding.bind(view))
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.todoText.text = myDataset[position].text
    }
    override fun getItemCount() = myDataset.size
}
