import Vue from 'vue';
import App from './App.vue';
import axios from 'axios';

Vue.config.productionTip = false;

Vue.prototype.$http = axios;
axios.defaults.baseURL = 'http://localhost:8080'; // Cambia esto a la URL de tu backend si es diferente

new Vue({
    render: h => h(App),
}).$mount('#app');