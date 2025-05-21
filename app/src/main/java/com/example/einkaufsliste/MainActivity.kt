package com.example.einkaufsliste

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //  Liste vom Typ String, die die einzelnen Einträge der Einkaufsliste enthalten wird
    private val items = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Layout der Activity laden (unter res/layout)
        setContentView(R.layout.activity_main)

        // Die vorhandenen Elemente der view finden und Konstanten zuweisen
        val inputField = findViewById<EditText>(R.id.input_item)
        val addButton = findViewById<Button>(R.id.add_button)
        val listView = findViewById<ListView>(R.id.item_list)

        //  Adapter dient zur Verbindung des Codes zur View (definiert in layout).
        // Dieser Arrayadapter nimmt eine Liste von Strings auf und
        // erzeugt für die jeweiligen Elemente eine TextView die in der ListView angezeigt werden kann.
        // Initialisiert wird der Adapter mit den Paramtern: Kontext (this; Hier die activity), dem
        // Layout der ListView (list_item), der ID der TextView (einzelner Listeneintrag; definiert in list_item als "item_text"),
        // die in der ListView angezeigt werden soll und der Ressource im Quellcode (die Liste namens "items").
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.list_item, R.id.item_text, items)

        // Dann wird dieser Adapter als Adapter der listView gesetzt.
        listView.adapter = adapter

        // Ein Eventlistener für den Hinzufügebutton
        addButton.setOnClickListener {
            val text = inputField.text.toString().trim()
            if (text.isNotEmpty()) {
                items.add(text)

                // Adapter muss benachrichtig werden, um Liste zu updaten
                adapter.notifyDataSetChanged()
                inputField.text.clear()
            }
        }

        // Evenlistener für die Liste der Gegenstände.
        // Diesem Listener wird eine Callbackfunktion  mithilfe einer Lambda (Arrow)-Funktion übergeben.
        // Diese bekommt 4 Parameter mitgegeben, allerdings wird für den Anwendungsfall nur die Position benötigt.
        //  Unbenötigte Parameter können per "_" ausgespart werden.
        // Wird nun ein einzelner Gegenstand getappt, kann dieser über den Positionsparameter ermittelt und entfernt werden.
        // Dann wird der Adapter wieder benachrichtigt.
        listView.setOnItemClickListener { _, _, position, _ ->
            items.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }
}
