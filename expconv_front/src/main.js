import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/global_css/main.css'

const app = createApp(App);

app.config.globalProperties.$apiBaseUrl = "http://localhost:5000/api/v1/";

app.use(router);
app.mount('#app');