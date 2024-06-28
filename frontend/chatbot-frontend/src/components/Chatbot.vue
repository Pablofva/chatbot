<template>
  <div>
    <div :style="{ backgroundColor: backgroundColor, opacity: 0.9 }" class="chat-container">
      <h1 class="chat-title">{{ chatbotTitle }}</h1>
      <div class="input-container">
        <textarea v-model="userInput" placeholder="Escribe tu mensaje aquí..." @keyup.enter="sendMessage"></textarea>
        <button @click="sendMessage">Enviar</button>
      </div>
      <div class="message-history">
        <div v-for="(message, index) in messages" :key="index" class="message">
          <p><strong>Usuario:</strong> {{ message.user }}</p>
          <p><strong>Chatbot:</strong> {{ message.bot }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    chatbotType: {
      type: String,
      required: true
    },
    selectedHouse: String,
    userId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      userInput: '',
      backgroundColor: this.chatbotType === 'prefect' ? this.getHouseColor(this.selectedHouse) : 'rgba(255, 255, 255, 0.7)',
      messages: []
    };
  },
  computed: {
    chatbotTitle() {
      return this.chatbotType === 'prefect' ? `Prefecto de ${this.selectedHouse}` : 'Sombrero Seleccionador';
    }
  },
  methods: {
    sendMessage() {
      if (this.userInput.trim() === '') return;
      this.$emit('send-message', {
        userId: this.userId,
        userInput: this.userInput,
        chatbotType: this.chatbotType, // Usar chatbotType en lugar de house
        selectedHouse: this.selectedHouse
      });
      this.addMessage(this.userInput, '...'); // Mostrar mensaje de espera
      this.userInput = '';
    },
    addMessage(userMessage, botResponse) {
      this.messages.push({ user: userMessage, bot: botResponse });
    },
    getHouseColor(house) {
      switch (house) {
        case 'Gryffindor':
          return 'rgba(255, 0, 0, 0.7)'; // Rojo medio transparente para Gryffindor
        case 'Slytherin':
          return 'rgba(0, 128, 0,0.7 )'; // Verde medio transparente para Slytherin
        case 'Ravenclaw':
          return 'rgba(0, 0, 255, 0.7)'; // Azul medio transparente para Ravenclaw
        case 'Hufflepuff':
          return 'rgba(255, 255, 0, 0.7)'; // Amarillo medio transparente para Hufflepuff
        default:
          return 'rgba(255, 255, 255, 0.7)'; // Blanco medio transparente por defecto
      }
    },
    updateBackgroundColor(newHouse) {
      this.backgroundColor = this.getHouseColor(newHouse); // Usar selectedHouse en lugar de house
    }
  }
};
</script>

<style scoped>
.chat-container {
  width: 90%;
  max-width: 900px;
  margin: 20px auto; /* Centra horizontalmente con márgenes automáticos */
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  opacity: 0.94; /* Hace el contenedor medio transparente */
  background-color: #333; /* Fondo gris oscuro */
  color: white; /* Texto blanco para mejor contraste */
}

.chat-title {
  text-align: center;
  margin-bottom: 20px;
  font-size: 2em; /* Ajusta el tamaño de la fuente según sea necesario */
}

.input-container {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

textarea {
  width: 100%;
  height: 100px;
  resize: none;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

.message-history {
  max-height: 400px;
  overflow-y: auto;
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 5px;
  background-color: #444; /* Fondo gris oscuro para los mensajes */
  color: white; /* Texto blanco para mejor contraste */
}

.message {
  margin-bottom: 10px;
  padding: 10px;
  background-color: #555; /* Fondo gris oscuro para los mensajes */
  border-radius: 5px;
  color: white; /* Texto blanco para mejor contraste */
}

@media (max-width: 768px) {
  .chat-container {
    width: 100%;
    margin: 20px auto;
    padding: 10px;
  }

  .chat-title {
    font-size: 1.5em; /* Ajusta el tamaño de la fuente para pantallas más pequeñas */
  }
}
</style>
