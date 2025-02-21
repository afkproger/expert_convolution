<template>
  <router-view></router-view>  <!-- Показывает текущий компонент -->
</template>

<script>
import { onMounted, onUnmounted } from "vue";

export default {
  name: "App",
  setup() {
    let intervalId = null;

    const refreshAccessToken = async () => {
      try {
        const response = await fetch(`${this.$apiBaseUrl}auth/refresh`, {
          method: "POST",
          credentials: "include",
        });

        if (!response.ok) throw new Error("Ошибка обновления токена");
        console.log("Токен обновлён успешно");
      } catch (error) {
        console.error(`Ошибка обновления токена`, error);
      }
    };

    const startTokenRefresh = () => {
      intervalId = setInterval(async () => {
        await refreshAccessToken();
      }, 14 * 60 * 1000);
    };

    onMounted(() => startTokenRefresh());
    onUnmounted(() => clearInterval(intervalId));

    return {};
  },
};
</script>