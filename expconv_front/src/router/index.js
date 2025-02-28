import { createRouter, createWebHistory } from 'vue-router';
import HomeForm from '../components/HomeForm.vue';
import LoginForm from '../components/AuthForms/LoginForm.vue';
import RegisterForm from '../components/AuthForms/RegisterForm.vue';
import SurveyManager from "@/components/SurveyManager.vue";

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
    },
    {
        path: '/workspace',
        name: 'workspace',
        component: SurveyManager
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;