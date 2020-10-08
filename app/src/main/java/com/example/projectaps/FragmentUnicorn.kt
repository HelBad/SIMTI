package com.example.projectaps

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_unicorn.*
import java.util.*

class FragmentUnicorn : Fragment() {

    //Image Kantor
    lateinit var viewpagerKantor : ViewPager
    var path : IntArray = intArrayOf(R.drawable.lini_bukalapak, R.drawable.lini_gojek, R.drawable.lini_ovo, R.drawable.lini_tokopedia, R.drawable.lini_traveloka)
    lateinit var adapter : ViewImg
    var currentPage : Int = 0
    lateinit var timer : Timer
    val DELAY : Long = 3000
    val PERIOD : Long = 3000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_unicorn, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pilihUnicorn1.setOnClickListener {
            val intent = Intent(activity, ActivityBukalapak::class.java)
            startActivity(intent)
        }
        pilihUnicorn2.setOnClickListener {
            val intent = Intent(activity, ActivityGojek::class.java)
            startActivity(intent)
        }
        pilihUnicorn3.setOnClickListener {
            val intent = Intent(activity, ActivityOvo::class.java)
            startActivity(intent)
        }
        pilihUnicorn4.setOnClickListener {
            val intent = Intent(activity, ActivityTokopedia::class.java)
            startActivity(intent)
        }
        pilihUnicorn5.setOnClickListener {
            val intent = Intent(activity, ActivityTraveloka::class.java)
            startActivity(intent)
        }

        //Image Kantor
        viewpagerKantor = view!!.findViewById(R.id.viewpagerKantor) as ViewPager
        adapter = ViewImg(this.requireContext(), path)
        viewpagerKantor.adapter = adapter
        updatePage()
        viewpagerKantor.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
    }

    //Image Kantor Duration
    fun updatePage() {
        var handler = Handler()
        val Update : Runnable = Runnable {
            if(currentPage == path.size) {
                currentPage = 0
            }
            viewpagerKantor.setCurrentItem(currentPage++, true)
        }
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, DELAY, PERIOD)
    }
}