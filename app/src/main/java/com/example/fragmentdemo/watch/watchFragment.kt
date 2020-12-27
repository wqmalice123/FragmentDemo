package com.example.fragmentdemo.watch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.fragmentdemo.R
import org.w3c.dom.Text

class watchFragment : Fragment() {

    companion object {
        fun newInstance() = watchFragment()
    }

    private lateinit var viewModel: WatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.watch_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WatchViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.seconds.observe(viewLifecycleOwner, Observer {
            val textView_time = view?.findViewById<TextView>(R.id.textView_timer)

            val hours = it/3600
            val minutes = (it%3600)/60
            val secs = it % 60
            if (textView_time != null) {
                textView_time.text = String.format("%02d:%02d:%02d",hours,minutes,secs)
            }
        })
        val button_start = view?.findViewById<Button>(R.id.button_start)
        if (button_start != null) {
            button_start.setOnClickListener{
                viewModel.start()
            }
        }
        val button_stop = view?.findViewById<Button>(R.id.button_stop)
        if (button_stop != null) {
            button_stop.setOnClickListener{
                viewModel.stop()
            }
        }
        val button_restart = view?.findViewById<Button>(R.id.button_restart)
        if (button_restart != null) {
            button_restart.setOnClickListener{
                viewModel.restart()
            }
        }
    }

}