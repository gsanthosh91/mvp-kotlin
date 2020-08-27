package com.gsanthosh91.mvpkotlin.ui.main

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gsanthosh91.mvpkotlin.R
import com.gsanthosh91.mvpkotlin.base.BaseActivity
import com.gsanthosh91.mvpkotlin.data.TodoItem
import com.gsanthosh91.mvpkotlin.databinding.ActivityMainBinding
import com.gsanthosh91.mvpkotlin.ui.adapter.TodoAdapter

class MainActivity : BaseActivity(), MainIPresenter.TicketIView, TodoAdapter.ClickListener {

    private var presenter: MainPresenter<MainActivity> = MainPresenter()
    private lateinit var binding: ActivityMainBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_main;
    }

    override fun initView() {
        presenter.attachView(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.todos()
    }

    override fun todos(todoList: List<TodoItem>) {

        binding.todoRv.apply {
            layoutManager = LinearLayoutManager(activity(), RecyclerView.VERTICAL, false)
            adapter = TodoAdapter(todoList, this@MainActivity)
        }
    }

    override fun click(item: TodoItem) {

        Log.d("click", "click: " + item.title)
    }
}