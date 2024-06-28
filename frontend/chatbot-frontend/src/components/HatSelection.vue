<template>
  <div class="hat-selection-container" v-if="questions.length > 0">
    <div class="user-greeting" v-if="userName">
      <h2>¡Hola, {{ userName }}!</h2>
    </div>
    <div class="banner-container">
      <img src="@/assets/hatbot.png" alt="Sombrero Seleccionador" class="hat-image">
    </div>
    <form @submit.prevent="submitAnswers">
      <div v-for="(options, question) in questions" :key="question">
        <h3>{{ question }}</h3>
        <div v-for="option in options" :key="option">
          <label class="option-label">
            <input type="radio" class="option-input" :name="question" :value="option" v-model="answers[question]" required>
            {{ option }}
          </label>
        </div>
      </div>
      <button type="submit">Enviar</button>
    </form>
  </div>
  <div v-else>
    Cargando preguntas...
  </div>
</template>

<script>
import { getQuestions } from '../services/apiService';

export default {
  props: ['userName'],
  data() {
    return {
      questions: {},
      answers: {},
    };
  },
  mounted() {
    // Obtener el userId de la variable global
    const userId = this.$root.$globalUserId; // Accedemos a la variable global desde el componente raíz

    if (!userId) {
      console.error('userId no está definido en la variable global.');
      return;
    }

    // Obtener las preguntas del backend
    getQuestions()
        .then(questions => {
          this.questions = questions;

          // Inicializar answers con null para cada pregunta
          for (const question in questions) {
            this.answers[question] = null;
          }
        })
        .catch(error => {
          console.error('Error fetching questions:', error);
        });
  },
  methods: {
    async submitAnswers() {
      try {
        const house = await this.determineHouse(this.answers);
        this.$emit('house-selected', { house, answers: this.answers });
      } catch (error) {
        console.error('Error al enviar las respuestas:', error);
      }
    },
  }
};
</script>

<style scoped>
.hat-selection-container {
  display: flex;
  flex-direction: column;
  width: 90%;
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: #444;
  color: white;
}
.banner-container {
  width: 100%;
  text-align: center;
  margin-bottom: 20px;
}
.hat-image {
  max-width: 100%;
  height: auto;
}
@media (max-width: 768px) {
  .hat-selection-container {
    width: 95%;
    padding: 15px;
  }
  h3 {
    font-size: 1em;
  }
}
</style>
