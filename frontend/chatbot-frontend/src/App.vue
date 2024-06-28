<template>
  <div id="app">
    <Registro @user-registered="handleUserRegistered" v-if="!isRegistered && !isHatSelection"/>
    <HatSelection v-if="isHatSelection" :userName="userName" @house-selected="handleHouseSelected" />
    <div v-else-if="isRegistered && !isHatSelection">
      <Banner :house="selectedHouse" />
      <Chatbot v-if="showChatbot" :chatbotType="chatbotType" :selectedHouse="selectedHouse" :userId="userId" @send-message="sendMessageToBackend" />
    </div>
  </div>
</template>

<script>
import Registro from './components/Registro.vue';
import Chatbot from './components/Chatbot.vue';
import Banner from './components/Banner.vue';
import HatSelection from './components/HatSelection.vue';
import { analyzeText } from './services/apiService'; // Importa analyzeText

export default {
  name: 'App',
  components: {
    Registro,
    Chatbot,
    Banner,
    HatSelection
  },
  data() {
    return {
      isRegistered: false,
      isHatSelection: false,
      userId: null,
      userName: '',
      selectedHouse: '',
      showChatbot: false,
    };
  },
  methods: {
    handleUserRegistered() {
      this.userId = this.$globalUser.id;
      this.userName = this.$globalUser.name;
      this.isRegistered = true;
      this.$nextTick(() => {
        this.isHatSelection = true;
      });
    },
    handleHouseSelected(data) {
      this.selectedHouse = data.house;
      this.showChatbot = true;
      this.isHatSelection = false;

      // Enviar el mensaje inicial al chatbot del prefecto
      this.sendMessageToBackend({
        userId: this.userId,
        userInput: '', // Mensaje vacío para obtener el mensaje de bienvenida
        chatbotType: 'prefect',
        selectedHouse: this.selectedHouse
      });
    },
    async sendMessageToBackend(messageData) {
      try {
        const response = await analyzeText(messageData.userId, messageData);
        this.$refs.chatbot.addMessage(messageData.userInput, response.response); // Actualizar el historial del chatbot
      } catch (error) {
        console.error('Error sending message:', error);
      }
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: #333333;
  margin-top: 60px;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
