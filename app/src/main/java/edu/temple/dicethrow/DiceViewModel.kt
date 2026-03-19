package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class DiceViewModel : ViewModel() {
    val dieRoll = MutableLiveData<Int>()

    var dieSides: Int = 6

    fun throwDie() {
        dieRoll.value = Random.nextInt(1, dieSides + 1)
    }
}