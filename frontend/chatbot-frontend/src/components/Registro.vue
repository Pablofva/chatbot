<template>
  <div class="register-container">
    <div class="form-header">
      <img src="@/assets/hat.png" alt="Sombrero Seleccionador" class="hat-image" />
      <h2>Registro</h2>
    </div>
    <form @submit.prevent="registerUser" class="register-form">
      <div class="form-group">
        <input v-model="user.name" placeholder="Nombre" required />
      </div>
      <div class="form-group">
        <input v-model="user.email" placeholder="Correo electrónico" required />
      </div>
      <div class="form-group">
        <input v-model="user.username" placeholder="Usuario" required />
      </div>
      <div class="form-group">
        <select v-model="user.gender" required>
          <option value="" disabled>Selecciona tu género</option>
          <option value="male">Masculino</option>
          <option value="female">Femenino</option>
          <option value="other">Otro</option>
        </select>
      </div>
      <button type="submit" class="register-button">Registrarse</button>
    </form>
  </div>
</template>

<script>
import Vue from 'vue';
import { registerUser } from '../services/apiService';

export default {
  data() {
    return {
      user: {
        name: '',
        email: '',
        username: '',
        gender: ''
      }
    };
  },
  methods: {
    async registerUser() {
      try {
        const response = await registerUser(this.user);
        Vue.prototype.$globalUser = response;
        this.$emit('user-registered');
      } catch (error) {
        console.error('Error registrando usuario:', error);
        alert('Hubo un error al registrarte. Por favor, inténtalo de nuevo.');
      }
    }
  }
};
</script>

<style scoped>
.register-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 90%;
  max-width: 500px;
  margin: 20px auto;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: #f8f8f8;
}
.form-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}
.hat-image {
  width: 100px;
  height: auto;
  margin-bottom: 10px;
}
h2 {
  text-align: center;
  margin: 0;
  font-size: 1.5em;
  color: #333;
}
.register-form {
  width: 100%;
}
.form-group {
  margin-bottom: 15px;
}
input, select {
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
  box-sizing: border-box;
  font-size: 1em;
}
.register-button {
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: none;
  background-color: #4CAF50;
  color: white;
  font-size: 1em;
  cursor: pointer;
}
.register-button:hover {
  background-color: #45a049;
}
</style>
