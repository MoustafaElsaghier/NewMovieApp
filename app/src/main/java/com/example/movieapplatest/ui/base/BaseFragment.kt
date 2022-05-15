package com.example.movieapplatest.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.example.movieapplatest.utils.UiUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel>(private val vmClass: KClass<VM>? = null) :
    Fragment(), LifecycleObserver {

    val uiUtils: UiUtils by lazy { UiUtils(requireContext()) }
    private val disposable = CompositeDisposable()

    lateinit var binding: DB
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmClass?.let { viewModel = sharedViewModel(it).value }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            getLayoutId(), container, false
        ) as DB
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
        initLiveData()
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    abstract fun initFragment()

    open fun initLiveData() {}

    fun addToDisposable(disposable: Disposable) {
        this.disposable.remove(disposable)
        this.disposable.add(disposable)
    }

    override fun onDestroy() {
        this.disposable.dispose()
        super.onDestroy()
    }

    open fun onFragmentRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

    }

    fun setViewModelNotifications() {

        viewModel.errorMsgLiveData.observe(this) {
            showErrorMsg(it)
        }

        viewModel.successMsgLiveData.observe(this) {
            showSuccessMsg(it)
        }

        viewModel.loadingLiveData.observe(this) {
            loading(it)
        }
    }

    fun loading(isLoading: Boolean) {
        if (isLoading) uiUtils.showLoading() else uiUtils.hideLoading()
    }

    fun showSuccessMsg(msg: String) {
        uiUtils.showSuccessMsg(msg)
    }

    fun showWarningMsg(msg: String) {
        uiUtils.showWarningMsg(msg)
    }

    fun showErrorMsg(msg: String) {
        uiUtils.showErrorMsg(msg)
    }

}