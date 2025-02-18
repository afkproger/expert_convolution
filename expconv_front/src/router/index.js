import { createRouter, createWebHistory } from 'vue-router';
import HomeForm from '../components/HomeForm.vue';
import LoginForm from '../components/LoginForm.vue';
import RegisterForm from '../components/RegisterForm.vue';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomeForm,
    },
    {
        path: '/login',
        name: 'login',
        component: LoginForm,
    },
    {
        path: '/register',
        name: 'register',
        component: RegisterForm,
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;