package com.example.jogodavelhamarvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.jogodavelhamarvel.databinding.ActivityMainBinding

import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    // Variável de ligação para acesso às views
    private lateinit var binding: ActivityMainBinding

    // Vetor bidimensional que representará o tabuleiro de jogo
    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )

    var jogadorAtual = "X"

    // Método onCreate que é chamado quando a Activity é criada
    // Entrada: savedInstanceState - o estado salvo da Activity
    // Saída: Nenhuma
    override fun onCreate(savedInstanceState: Bundle?) {
        // Infla o layout usando o binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Método para ativar bordas
        setContentView(binding.root) // Define o layout da Activity como a raiz do binding
    }

    // Função que será chamada quando um botão for clicado
    // Entrada: view - a view que foi clicada
    // Saída: Nenhuma
    fun buttonClick(view: View){
        // Converte a view recebida para um botão
        val buttonSelecionado = view as Button

        // Define o texto do botão clicado como "X"
        buttonSelecionado.text = "X"


        // Desativa o botão para que não possa ser clicado novamente


        // Atualiza o tabuleiro com "X" na posição correspondente ao botão clicado
        when(buttonSelecionado.id){
            binding.buttonZero.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonUm.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][2] = jogadorAtual
        }

        buttonSelecionado.setBackgroundResource(R.drawable.imgcap)
        buttonSelecionado.isEnabled = false

        var vencedor = verificaVencedor(tabuleiro)

        if(!vencedor.isNullOrBlank()){
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }



        // Gera posições aleatórias para o próximo movimento do computador
        var rX = Random.nextInt(0, 3)
        var rY = Random.nextInt(0, 3)

        // Tenta encontrar uma posição vazia no tabuleiro
        var i = 0
        while (i < 9) {
            rX = Random.nextInt(0, 3)
            rY = Random.nextInt(0, 3)

            if (tabuleiro[rX][rY] != "X" && tabuleiro[rX][rY] != "O") {
                break // Sai do loop se encontrar uma posição vazia
            }

            i++
        }

        // Marca a posição encontrada com "O"
        tabuleiro[rX][rY] = "O"

        // Converte a posição bidimensional para uma posição linear
        val posicao = rX * 3 + rY

        // Atualiza o texto e estado do botão correspondente no layout
        when(posicao){
            0 -> {
                binding.buttonZero.setBackgroundResource(R.drawable.imghom)
                binding.buttonZero.isEnabled = false
            }
            1 -> {
                binding.buttonUm.setBackgroundResource(R.drawable.imghom)
                binding.buttonUm.isEnabled = false
            }
            2 -> {
                binding.buttonDois.setBackgroundResource(R.drawable.imghom)
                binding.buttonDois.isEnabled = false
            }
            3 -> {
                binding.buttonTres.setBackgroundResource(R.drawable.imghom)
                binding.buttonTres.isEnabled = false
            }
            4 -> {
                binding.buttonQuatro.setBackgroundResource(R.drawable.imghom)
                binding.buttonQuatro.isEnabled = false
            }
            5 -> {
                binding.buttonCinco.setBackgroundResource(R.drawable.imghom)
                binding.buttonCinco.isEnabled = false
            }
            6 -> {
                binding.buttonSeis.setBackgroundResource(R.drawable.imghom)
                binding.buttonSeis.isEnabled = false
            }
            7 -> {
                binding.buttonSete.setBackgroundResource(R.drawable.imghom)
                binding.buttonSete.isEnabled = false
            }
            8 -> {
                binding.buttonOito.setBackgroundResource(R.drawable.imghom)
                binding.buttonOito.isEnabled = false
            }
        }
    }
    //verifica se há um vencedor no tabuleiro do jogo da velha
    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {

        //verifica se existem 3 jogadas iguais nas linhas e colunas
        for (i in 0 until 3) {
            //verifica se existem 3 jogadas iguais numa linha
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
            //verifica se existem 3 jogadas iguais numa coluna
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }
        //verifica se existem jogadas iguais nas diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2]
        }
        //verifica quantos jogadores estão jogando
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if(valor.equals("X")||valor.equals("O")){
                    empate++
                }
            }
        }
        //verifica se existem nove jogadas iguais nas linhas, colunas e diagonais. Caso não exista, retorna a mensagem "Empate"
        if(empate == 9){
            return "Empate"
        }
        //não há jogada
        return null
    }
}
