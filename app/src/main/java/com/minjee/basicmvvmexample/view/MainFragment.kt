package com.minjee.basicmvvmexample.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.minjee.basicmvvmexample.R
import com.minjee.basicmvvmexample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*


/*
 *      MainFragment
 *      - shows the UI
 *      - listens to viewModel for updates on UI
 */
class MainFragment: Fragment() {

    // Create a viewModel
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        fragmentTextUpdateObserver()
    }

    // Setup the button in our fragment to call getUpdatedText method in viewModel
    private fun setupClickListeners() {
        fragmentButton.setOnClickListener { viewModel.getUpdatedText() }
    }

    // Observer is waiting for viewModel to update our UI
    private fun fragmentTextUpdateObserver() {
        viewModel.uiTextLiveData.observe(viewLifecycleOwner, Observer { updatedText ->
            fragmentTextView.text = updatedText
        })
    }
}