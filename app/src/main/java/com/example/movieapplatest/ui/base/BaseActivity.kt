package com.example.movieapplatest.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.movieapplatest.utils.UiUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel>(private val vmClass: KClass<VM>? = null) :
    AppCompatActivity() {

    val uiUtils: UiUtils by lazy { UiUtils(this) }

    private val disposable = CompositeDisposable()

    lateinit var binding: DB
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId()) as DB
        setContentView(binding.root)

        vmClass?.let { viewModel = viewModel(it).value }
        initActivity()
        initLiveData()
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    abstract fun initActivity()

    fun addToDisposable(disposable: Disposable) {
        this.disposable.remove(disposable)
        this.disposable.add(disposable)
    }

    fun setViewModelNotifications() {

        viewModel.errorMsgLiveData.observe(this) {
            showErrorMsg(it)
        }

        viewModel.successSnakeBarLiveData.observe(this) {
            showSuccessSnakeBar(it)
        }

        viewModel.successMsgLiveData.observe(this) {
            showSuccessMsg(it)
        }

        viewModel.loadingLiveData.observe(this) {
            loading(it)
        }
    }

    private fun showSuccessSnakeBar(msg: String) {
        uiUtils.showSuccessSnakeBar(msg)
    }

    open fun initLiveData() {}

    open fun loading(isLoading: Boolean) {
        if (isLoading) uiUtils.showLoading() else uiUtils.hideLoading()
    }

    fun showSuccessMsg(msg: String) {
        uiUtils.showSuccessMsg(msg)
    }

    fun showErrorMsg(msg: String) {
        uiUtils.showErrorMsg(msg)
    }

    fun showWarningMsg(msg: String) {
        uiUtils.showWarningMsg(msg)
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}