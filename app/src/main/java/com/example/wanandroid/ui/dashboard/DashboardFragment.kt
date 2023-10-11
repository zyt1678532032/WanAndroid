package com.example.wanandroid.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.wanandroid.util.ext.viewBinding
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val binding by viewBinding<FragmentDashboardBinding>()
    private val dashboardViewModel by activityViewModels<DashboardViewModel>()

    private lateinit var demoCollectionAdapter: DemoCollectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        demoCollectionAdapter = DemoCollectionAdapter(this)
        binding.viewPage.adapter = demoCollectionAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPage) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }

}

class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int).
        val fragment = DemoObjectFragment()
        fragment.arguments = Bundle().apply {
            // The object is just an integer.
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}

private const val ARG_OBJECT = "object"

// Instances of this class are fragments representing a single
// object in the collection.
class DemoObjectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_collection_object, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val textView: TextView = view.findViewById(R.id.text)
            textView.text = getInt(ARG_OBJECT).toString()
        }
    }
}
