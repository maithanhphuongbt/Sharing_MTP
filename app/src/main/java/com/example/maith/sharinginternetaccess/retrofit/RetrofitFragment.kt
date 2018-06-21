package com.example.maith.sharinginternetaccess.retrofit

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maith.sharinginternetaccess.R
import com.example.maith.sharinginternetaccess.retrofit.adapter.ProductAdapter
import com.example.maith.sharinginternetaccess.retrofit.model.Product
import com.example.maith.sharinginternetaccess.retrofit.presenter.ProductPresenter
import kotlinx.android.synthetic.main.fragment_retrofit.*

class RetrofitFragment : Fragment() {

    private var productList: ArrayList<Product> = ArrayList()
    private var productAdapter: ProductAdapter = ProductAdapter(productList)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_retrofit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        btnGetData.setOnClickListener {
            this.context?.let { ProductPresenter(it, productList,productAdapter).fetchData() }
        }
    }



    private fun initView() {
        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = productAdapter
        }
    }

    companion object {
        val TAG: String = RetrofitFragment::class.java.simpleName
    }
}