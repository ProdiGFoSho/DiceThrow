package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {


    lateinit var dieTextView: TextView

    lateinit var dieViewModel: DiceViewModel

    var dieSides: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            dieSides = it.getInt(DIESIDE)
        }

        dieViewModel = ViewModelProvider(this)[DiceViewModel::class.java]
        dieViewModel.dieSides = dieSides
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dieViewModel.dieRoll.observe(viewLifecycleOwner) { roll ->
            dieTextView.text = roll.toString()
        }

        if (dieViewModel.dieRoll.value == null) {
            dieViewModel.throwDie()
        }
    }

    fun throwDie() {
        dieViewModel.throwDie()
    }

    companion object {
        private const val DIESIDE = "sidenumber"

        fun newInstance(sides: Int) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, sides)
            }
        }
    }
}