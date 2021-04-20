package com.chinlung.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.*

class foodcalendar : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val foodlist = mutableListOf<Foods>()
    lateinit var spinnerchoice: String
    lateinit var infoshow: TextView
    lateinit var account: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodcalendar)

        val bundle = intent.getBundleExtra("bundle")

        account = bundle?.getString("Account") ?: "noName"
        val password = bundle?.getString("Password")

        Toast.makeText(this, "$account $password", Toast.LENGTH_SHORT).show()
        var time = ""
        val datainput = findViewById<EditText>(R.id.EditPicker)
        val radgroup = findViewById<RadioGroup>(R.id.radGroup)
        val spinner = findViewById<Spinner>(R.id.ChoiceFood)
        val spendmoney = findViewById<EditText>(R.id.SpendMoney)
        val btninput = findViewById<Button>(R.id.btnInput)
        val btnshow = findViewById<Button>(R.id.btnShow)

        infoshow = findViewById<TextView>(R.id.InfoShow).also {
            it.movementMethod = ScrollingMovementMethod.getInstance()
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.foods,
            R.layout.spinner_simple
        ).also {
            it.setDropDownViewResource(R.layout.spinner_item)
            spinner.adapter = it
//            spinner.setSelection(0,false)
        }
        spinner.onItemSelectedListener = this

        radgroup.setOnCheckedChangeListener { group, checkedId ->
            time = when (checkedId) {
                R.id.BreakFast -> findViewById<RadioButton>(R.id.BreakFast).text.toString()
                R.id.Lunch -> findViewById<RadioButton>(R.id.Lunch).text.toString()
                R.id.Dinner -> findViewById<RadioButton>(R.id.Dinner).text.toString()
                else -> ""
            }
        }

        btninput.setOnClickListener { savetolist(datainput, time, spinner, spendmoney) }
        btnshow.setOnClickListener { infoshowtext() }
    }

    private fun infoshowtext() {
        var str = ""
        if (foodlist.isEmpty())
            Toast.makeText(this, "清單是空的", Toast.LENGTH_SHORT).show()
        else {
            repeat(foodlist.size) {
                str =
                    "$account\n${foodlist[it].date}:${foodlist[it].time} 花$${foodlist[it].money} 吃${foodlist[it].spinner}\n" + str
            }
        }
        infoshow.text = str
    }

    private fun savetolist(date: EditText, time: String, spinner: Spinner, money: EditText) {

        if (date.text.toString() == "" || time == "" || money.text.toString() == "")
            when {
                date.text.toString() == "" ->
                    Toast.makeText(this, "請輸入日期", Toast.LENGTH_SHORT).show()
                time == "" ->
                    Toast.makeText(this, "請輸入時間", Toast.LENGTH_SHORT).show()
                money.text.toString() == "" ->
                    Toast.makeText(this, "請輸入金額", Toast.LENGTH_SHORT).show()
            } else {
            foodlist.add(
                Foods(
                    date.text.toString(),
                    time,
                    spinnerchoice,
                    money.text.toString(),
                    account
                )
            )
            Toast.makeText(this, "${foodlist.last()}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        spinnerchoice = parent!!.selectedItem.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun pickerdate() {

    }
}