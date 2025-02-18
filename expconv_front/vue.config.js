const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  devServer: {
    watchFiles: ['src/**/*'], // Следит за изменениями в папке src
  },
});
