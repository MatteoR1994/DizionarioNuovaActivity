package com.example.dizionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val codaRichieste = Volley.newRequestQueue(this)
        buttonCerca.setOnClickListener {
            val url = getUrl()
            val richiesta = StringRequest(Request.Method.GET,url,
                    { risposta ->
                        try {
                            eseguiRicercaLista(risposta)
                            //eseguiRicercaStringa(risposta)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    },
                    { errore ->
                        stringaErroreRicerca.text = "Errore durante la ricerca."
                    })
            codaRichieste.add(richiesta)
        }
    }
    private fun getUrl() : String{
        val word = inputDaCercare.text
        val apiKey = "79abcd0f-0503-41e0-8e17-eee7cbb16be5"
        val url = "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"
        return url
    }
    private fun eseguiRicercaLista(s: String) { // Questo metodo seleziona le varie DRP in una lista e le passa nell'intent. Funziona.
        val arrayJson = JSONArray(s)
        val primoElemento = arrayJson.getJSONObject(0)
        val primoLivello = primoElemento.getJSONArray("dros")
        var listaDrpDros = Array(primoLivello.length()) { i -> ""}
        for(e in 0 until primoLivello.length()) {
            val oggetto = primoLivello.getJSONObject(e)
            val stringaOggetto = oggetto.getString("drp")
            listaDrpDros[e] = stringaOggetto.toString()
        }
        val intent = Intent(this,ActivityListaDrp::class.java)
        intent.putExtra("listaDRP",listaDrpDros)
        startActivity(intent)
    }
    private fun eseguiRicercaStringa(s: String) { // Questo metodo seleziona le varie DRP in una stringa e le passa nell'intent. Funziona.
        val arrayJson = JSONArray(s)
        val primoElemento = arrayJson.getJSONObject(0)
        val primoLivello = primoElemento.getJSONArray("dros")
        var stringaDrpDros = ""
        for(e in 0 until primoLivello.length()) {
            val oggetto = primoLivello.getJSONObject(e)
            val stringaOggetto = oggetto.getString("drp")
            stringaDrpDros += "$stringaOggetto&"
        }
        val intent = Intent(this,ActivityListaDrp::class.java)
        intent.putExtra("listaDRP",stringaDrpDros)
        startActivity(intent)
    }
}