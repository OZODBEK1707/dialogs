package com.example.dialogs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.dialogs.databinding.ActivityMainBinding
import com.example.dialogs.databinding.ItemDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("ShowToast", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAlertDialog.setOnClickListener {
                val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Diqqat!")
            dialog.setMessage("Haqiqatdan ham yuklab olmoqchimisiz")

            dialog.setNeutralButton("Qaytish"
            ) { _, _ ->
                Toast.makeText(this, "Qaytish", Toast.LENGTH_SHORT).show()
            }
            dialog.setPositiveButton("Roziman"){ _, _ ->
                Toast.makeText(this, "Yuklab olindi", Toast.LENGTH_SHORT).show()
            }
            dialog.setNegativeButton("Rozi emasman"
            ) { _, _ ->
                Toast.makeText(this@MainActivity, "yuklab olinmadi", Toast.LENGTH_SHORT)
                    .show()
            }
            dialog.show()
        }

        binding.tvCustomDialog.setOnClickListener {
            val customDialog = AlertDialog.Builder(this).create()
            val itemDialog = ItemDialogBinding.inflate(layoutInflater)

            itemDialog.btnYes.setOnClickListener {
                Toast.makeText(this, "Ha", Toast.LENGTH_SHORT).show()
                customDialog.cancel()
            }
            itemDialog.btnNo.setOnClickListener {
                Toast.makeText(this, "yo'q", Toast.LENGTH_SHORT).show()
                customDialog.cancel()
            }

            customDialog.setView(itemDialog.root)
            customDialog.show()
        }

        binding.tvFragmentDialog.setOnClickListener {
            val myDialogFragment = DialogFragment()
            myDialogFragment.show(supportFragmentManager, myDialogFragment.toString())
        }

        binding.tvTimePicker.setOnClickListener {
            val date = Date()
            TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Toast.makeText(this@MainActivity,
                        "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
                }
            }, date.hours, date.minutes, true)
            .show()
        }
        binding.tvDatePicker.setOnClickListener {
            val date = Date()
            val dateDialog = DatePickerDialog(this,
                { view, year, month, dayOfMonth -> Toast.makeText(this@MainActivity,
                    "$year/$month/$dayOfMonth", Toast.LENGTH_SHORT).show() },
                date.year,date.month-1,date.day)
                dateDialog.show()
        }

        binding.tvSnackbar.setOnClickListener {
          val snackbar = Snackbar.make(binding.root, "snackbar ishladi!!!", Snackbar.LENGTH_LONG)
            snackbar.show()
        }


        binding.idBtnShowBottomSheet.setOnClickListener {

            val dialog = BottomSheetDialog(this)

            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

            val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)

            btnClose.setOnClickListener {
                dialog.dismiss()
            }


            dialog.setContentView(view)

            dialog.show()
        }

    }
}