# Chess System with AI ♟️🤖

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17+"/>
  <img src="https://img.shields.io/badge/GUI-Java_Swing-blue?style=for-the-badge" alt="Java Swing"/>
  <img src="https://img.shields.io/badge/Bootcamp-Santander%20%7C%20DIO-red?style=for-the-badge" alt="Santander DIO"/>
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" alt="MIT License"/>
</p>

> Projeto de **Sistema de Xadrez com Inteligência Artificial e Interface Gráfica**, desenvolvido como consolidação prática do programa **Santander - Construa seu primeiro App com IA** em parceria com a **DIO (Digital Innovation One)**.

---

## 📌 Sumário
- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Diferenciais de Inteligência Artificial](#-diferenciais-de-inteligência-artificial)
- [Arquitetura e Boas Práticas](#-arquitetura-e-boas-práticas)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Como Executar](#-como-executar)
- [Autor](#-autor)

---

## 📖 Sobre o Projeto

O objetivo deste projeto foi elevar o tradicional desafio de xadrez em terminal para um **produto desktop completo e intuitivo**, aplicando princípios sólidos de **Engenharia de Software (Clean Code, SOLID, POO pura)** e integrando um motor de **Inteligência Artificial** com múltiplos níveis de rating para jogar contra o usuário.

---

## ✨ Funcionalidades

- ♟️ **Regras Completas das Peças**: Movimentações legítimas de Peão, Torre, Cavalo, Bispo, Rainha e Rei.
- 🎨 **Interface Gráfica Interativa (Swing)**: Tabuleiro com alternância clássica de cores, seleção por clique, realce visual da casa ativa e peças renderizadas com caracteres Unicode oficiais.
- 🔄 **Controle de Turnos e Capturas**: Alternância estrita entre jogador (Brancas) e computador (Pretas), com detecção de captura de Rei e fim de partida.
- 👑 **Promoção de Peão**: Promoção automática de peão para Rainha ao atingir o lado oposto do tabuleiro.
- ⚙️ **Seletor de Dificuldade em Tempo Real**: Alterne a inteligência da IA antes ou durante o andamento da partida.

---

## 🧠 Diferenciais de Inteligência Artificial

A engine de IA (`ChessAI`) implementa três níveis progressivos de habilidade:

| Nível | Rating Estimado | Algoritmo / Estratégia |
| :--- | :---: | :--- |
| **1 - Fácil** | `~800 ELO` | Escolha dinâmica de movimentos legais via amostragem aleatória. Ideal para partidas rápidas e casuais. |
| **2 - Médio** | `~1300 ELO` | Algoritmo heurístico guloso (*Greedy Search*) que prioriza capturas de maior ganho de material imediato. |
| **3 - Difícil** | `~1800 ELO` | Algoritmo **Minimax** com avaliação de estado de tabuleiro e **Poda Alfa-Beta** (*Alpha-Beta Pruning*) para antecipar jogadas adversárias. |

---

## 🏛️ Arquitetura e Boas Práticas

O projeto foi desacoplado em pacotes modulares segundo a responsabilidade única:

- **`com.chess.model`**: Entidades fundamentais (`Piece`, `Pawn`, `Knight`, `Position`, `Move`).
- **`com.chess.engine`**: Regras de negócio, manipulação do tabuleiro (`Board`), orquestração da partida (`ChessMatch`) e helpers de cálculo de trajetória.
- **`com.chess.ai`**: Motor heurístico de inteligência artificial desacoplado das regras visuais.
- **`com.chess.ui`**: Camada de apresentação gráfica com tratamento de eventos assíncronos e feedback ao usuário.

---

## 📁 Estrutura do Projeto

```text
chess-system-ai/
├── src/
│   └── com/
│       └── chess/
│           ├── Main.java              # Ponto de entrada da aplicação
│           ├── ai/
│           │   └── ChessAI.java       # Motor Minimax e heurísticas de IA
│           ├── engine/
│           │   ├── Board.java         # Gestão da matriz 8x8 do tabuleiro
│           │   ├── ChessMatch.java    # Controle de turnos e estado da partida
│           │   └── MoveHelper.java    # Trajetórias lineares e diagonais
│           ├── model/
│           │   ├── Piece.java         # Classe base abstrata com polimorfismo
│           │   ├── PieceColor.java    # Enumeração de cores
│           │   ├── Position.java      # Representação e conversão algébrica
│           │   ├── Move.java          # Encapsulamento de movimentos
│           │   └── [Pawn, Rook, Knight, Bishop, Queen, King].java
│           └── ui/
│               └── ChessGUI.java      # Interface gráfica em Java Swing
├── .gitignore
└── README.md
```

---

## 🚀 Como Executar

### Pré-requisitos
- **Java JDK 17** ou superior instalado e configurado no PATH.

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/TiagoSouzasoftw/chess-system-ai.git
   cd chess-system-ai
   ```

2. **Compile o projeto:**
   ```bash
   javac -d bin src/com/chess/model/*.java src/com/chess/engine/*.java src/com/chess/ai/*.java src/com/chess/ui/*.java src/com/chess/Main.java
   ```

3. **Execute a aplicação:**
   ```bash
   java -cp bin com.chess.Main
   ```

*(Alternativamente, basta abrir a pasta do projeto no IntelliJ IDEA, Eclipse ou VS Code e executar a classe `Main.java`)*.

---

## 👤 Autor

Desenvolvido por **Tiago Souza**  
- **GitHub**: [@TiagoSouzasoftw](https://github.com/TiagoSouzasoftw)
- **LinkedIn**: [Seu Perfil do LinkedIn](https://www.linkedin.com)

---
*Agradecimentos especiais à [Digital Innovation One (DIO)](https://dio.me) e ao [Santander Open Academy](https://www.santanderopenacademy.com) pela oportunidade de evolução na trilha de Inteligência Artificial.*
