package com.example.jogodavelhamarvel

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class teladeentrada : AppCompatActivity(), View.OnClickListener {

    //configura o inicio do aplicativo e restaura depois, se necessario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teladeentrada)

        //permite a definição de ações específicas para cada botão
        findViewById<View>(R.id.jogar).setOnClickListener(this)
    }

    //permite que os botões apertados levem para as atividades na qual foram designados
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.jogar -> {
                //abre o código de jogar com uma pessoa
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("modoJogo", "pessoa")
                startActivity(intent)
            }
        }
    }
}